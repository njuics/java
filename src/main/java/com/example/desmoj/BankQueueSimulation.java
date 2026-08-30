package com.example.desmoj;

import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import co.paralleluniverse.fibers.SuspendExecution;
import desmoj.core.dist.ContDistNormal;
import desmoj.core.dist.ContDistUniform;
import desmoj.core.exception.DelayedInterruptException;
import desmoj.core.exception.InterruptException;
import desmoj.core.simulator.CoroutineModel;
import desmoj.core.simulator.Experiment;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimProcess;
import desmoj.core.simulator.TimeInstant;
import desmoj.core.simulator.TimeSpan;
import desmoj.extensions.visualization2d.animation.CmdGeneration;
import desmoj.extensions.visualization2d.animation.FormExt;
import desmoj.extensions.visualization2d.animation.Position;
import desmoj.extensions.visualization2d.animation.core.simulator.ModelAnimation;
import desmoj.extensions.visualization2d.animation.core.simulator.ProcessQueueAnimation;
import desmoj.extensions.visualization2d.animation.core.simulator.SimProcessAnimation;
import desmoj.extensions.visualization2d.animation.internalTools.EntityTypeAnimation;
import desmoj.extensions.visualization2d.animation.processStation.ProcessStationAnimation;
import desmoj.extensions.visualization2d.animation.transport.TransportRouteAnimation;
import desmoj.extensions.visualization2d.animation.transport.TransportStationAnimation;
import desmoj.extensions.visualization2d.engine.Constants;
import desmoj.extensions.visualization2d.engine.model.EntityType;
import desmoj.extensions.visualization2d.engine.viewer.ViewerFrame;

/**
 * A process-oriented bank queue model based on DESMO-J's 2D animation API.
 *
 * <p>The model follows the structure of {@code src/NS1.SIM}: customers first
 * visit a teller and then a cashier. The simulation clock is virtual; the
 * generated command file is replayed by DESMO-J's 2D viewer.</p>
 */
public final class BankQueueSimulation {

    private static final TimeUnit SIMULATION_UNIT = TimeUnit.MINUTES;
    private static final double SIMULATION_MINUTES = 480.0;
    private static final double CUSTOMER_TRAVEL_MINUTES = 0.25;
    private static final double ARRIVAL_BUSY_MIN = 1.0;
    private static final double ARRIVAL_BUSY_MAX = 4.0;
    private static final double ARRIVAL_IDLE_MIN = 2.0;
    private static final double ARRIVAL_IDLE_MAX = 9.0;
    private static final double TELLER_SERVICE_MEAN = 6.0;
    private static final double TELLER_SERVICE_STDDEV = 1.0;
    private static final double CASHIER_SERVICE_MEAN = 8.0;
    private static final double CASHIER_SERVICE_STDDEV = 2.0;

    private BankQueueSimulation() {
    }

    public static void main(String[] args) throws IOException {
        Experiment.setCoroutineModel(CoroutineModel.THREADS);

        Path runDirectory = Path.of("target", "desmoj-bank-queue");
        Files.createDirectories(runDirectory);
        Path reportDirectory = Files.createDirectories(runDirectory.resolve("report"));
        Path commandFile = runDirectory.resolve("bank-queue" + Constants.FILE_EXTENSION_CMD);
        Path commandLog = runDirectory.resolve("bank-queue" + Constants.FILE_EXTENSION_LOG_0);
        createAnimationIcons(runDirectory);
        URL iconDirectory = runDirectory.toUri().toURL();

        CmdGeneration cmdGeneration = new CmdGeneration(
                commandFile.toString(), commandLog.toString(), iconDirectory);
        TimeInstant begin = new TimeInstant(0.0, SIMULATION_UNIT);
        TimeInstant end = new TimeInstant(SIMULATION_MINUTES, SIMULATION_UNIT);
        cmdGeneration.setStartStopTime(begin, end, TimeZone.getDefault());

        BankModel model = new BankModel(cmdGeneration, 2, 2, true, false, true);
        Experiment experiment = new Experiment("BankQueue", reportDirectory.toString());
        // The DESMO-J progress window is a Swing component; disable it for
        // command-line and headless runs. The 2D viewer is opened separately.
        experiment.setShowProgressBar(false);
        model.connectToExperiment(experiment);

        // DESMO-J writes the 2D command stream while the experiment runs.
        cmdGeneration.experimentStart(experiment, 5.0);
        cmdGeneration.close();
        experiment.report();
        experiment.finish();

        System.out.printf("Served customers: %d%n", model.getServedCustomers());
        System.out.printf("Average time in bank: %.2f minutes%n", model.getAverageTimeInBank());
        System.out.printf("Maximum time in bank: %.2f minutes%n", model.getMaximumTimeInBank());
        System.out.println("Animation commands: " + commandFile.toAbsolutePath());

        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Headless environment: open the command file with the DESMO-J 2D viewer.");
            return;
        }

        ViewerFrame viewer = new ViewerFrame(
                commandFile.toUri().toURL(), iconDirectory, Locale.ENGLISH);
        viewer.setTitle("DESMO-J Bank Queue");
        viewer.setLocationByPlatform(true);
        viewer.setVisible(true);
    }

    /** The DESMO-J model and its animated components. */
    private static final class BankModel extends ModelAnimation {

        private final int tellerCount;
        private final int cashierCount;

        private int nextCustomerId;
        private int nextTellerId;
        private int nextCashierId;

        private ContDistUniform busyArrival;
        private ContDistUniform idleArrival;
        private ContDistNormal tellerService;
        private ContDistNormal cashierService;

        private ProcessQueueAnimation<Customer> tellerQueue;
        private ProcessQueueAnimation<Customer> cashierQueue;
        private ProcessQueueAnimation<Teller> idleTellers;
        private ProcessQueueAnimation<Cashier> idleCashiers;

        private ProcessStationAnimation<Customer, Teller> tellerStation;
        private ProcessStationAnimation<Customer, Cashier> cashierStation;

        private TransportRouteAnimation<Customer> arrivalRoute;
        private TransportRouteAnimation<Customer> tellerToCashierRoute;
        private TransportRouteAnimation<Customer> departureRoute;

        private long servedCustomers;
        private double totalTimeInBank;
        private double maximumTimeInBank;

        private BankModel(CmdGeneration cmdGeneration, int tellerCount, int cashierCount,
                          boolean showInReport, boolean showInTrace, boolean showInAnimation) {
            super(null, "BankQueueModel", cmdGeneration,
                    showInReport, showInTrace, showInAnimation);
            if (tellerCount < 1 || cashierCount < 1) {
                throw new IllegalArgumentException("At least one teller and cashier are required");
            }
            this.tellerCount = tellerCount;
            this.cashierCount = cashierCount;

            setModelProjectName("Java DESMO-J examples");
            setModelProjectURL("https://desmoj.sourceforge.net/");
            setModelAuthor("Java Development examples");
            setModelDate("2026");
            setModelDescription(description());
            setModelLicense("Apache License 2.0");
            addEntityTypeAnimation(Customer.ENTITY_TYPE);
            addEntityTypeAnimation(Teller.ENTITY_TYPE);
            addEntityTypeAnimation(Cashier.ENTITY_TYPE);
            addIcon("CustomerIcon", "customer.png");
            addIcon("TellerIcon", "teller.png");
            addIcon("CashierIcon", "cashier.png");
            setGeneratedBy(BankModel.class.getName());
        }

        @Override
        public String description() {
            return "Customers pass through a teller queue and a cashier queue.";
        }

        @Override
        public void initAnimation() {
            busyArrival = new ContDistUniform(this, "Busy arrival interval",
                    ARRIVAL_BUSY_MIN, ARRIVAL_BUSY_MAX, reportIsOn(), traceIsOn());
            idleArrival = new ContDistUniform(this, "Idle arrival interval",
                    ARRIVAL_IDLE_MIN, ARRIVAL_IDLE_MAX, reportIsOn(), traceIsOn());
            tellerService = new ContDistNormal(this, "Teller service time",
                    TELLER_SERVICE_MEAN, TELLER_SERVICE_STDDEV, reportIsOn(), traceIsOn());
            tellerService.setNonNegative(true);
            cashierService = new ContDistNormal(this, "Cashier service time",
                    CASHIER_SERVICE_MEAN, CASHIER_SERVICE_STDDEV, reportIsOn(), traceIsOn());
            cashierService.setNonNegative(true);

            tellerQueue = new ProcessQueueAnimation<>(this, "Teller queue",
                    reportIsOn(), traceIsOn());
            tellerQueue.createAnimation(new Position(260, 190),
                    new FormExt(true, 8, Customer.ENTITY_TYPE.getId()), animationIsOn());
            cashierQueue = new ProcessQueueAnimation<>(this, "Cashier queue",
                    reportIsOn(), traceIsOn());
            cashierQueue.createAnimation(new Position(740, 430),
                    new FormExt(true, 8, Customer.ENTITY_TYPE.getId()), animationIsOn());

            idleTellers = new ProcessQueueAnimation<>(this, "Idle tellers",
                    reportIsOn(), traceIsOn());
            idleTellers.createAnimation(new Position(500, 90),
                    new FormExt(true, tellerCount, Teller.ENTITY_TYPE.getId()), animationIsOn());
            idleCashiers = new ProcessQueueAnimation<>(this, "Idle cashiers",
                    reportIsOn(), traceIsOn());
            idleCashiers.createAnimation(new Position(980, 330),
                    new FormExt(true, cashierCount, Cashier.ENTITY_TYPE.getId()), animationIsOn());

            tellerStation = new ProcessStationAnimation<>(this, "Teller service",
                    new Position(500, 190),
                    new FormExt(true, tellerCount, Teller.ENTITY_TYPE.getId()),
                    tellerQueue, animationIsOn());
            cashierStation = new ProcessStationAnimation<>(this, "Cashier service",
                    new Position(980, 430),
                    new FormExt(true, cashierCount, Cashier.ENTITY_TYPE.getId()),
                    cashierQueue, animationIsOn());

            TransportStationAnimation entrance = new TransportStationAnimation(
                    this, "Entrance", new Position(70, 190), animationIsOn());
            TransportStationAnimation tellerQueueEntry = new TransportStationAnimation(
                    this, "Teller queue entry", new Position(180, 190), animationIsOn());
            arrivalRoute = new TransportRouteAnimation<>(this, "Arrival route", 1.0,
                    entrance, tellerQueueEntry,
                    new Position[]{new Position(70, 190), new Position(180, 190)}, animationIsOn());

            TransportStationAnimation tellerExit = new TransportStationAnimation(
                    this, "Teller exit", new Position(590, 190), animationIsOn());
            TransportStationAnimation cashierQueueEntry = new TransportStationAnimation(
                    this, "Cashier queue entry", new Position(660, 430), animationIsOn());
            tellerToCashierRoute = new TransportRouteAnimation<>(this, "Teller to cashier route", 1.0,
                    tellerExit, cashierQueueEntry,
                    new Position[]{new Position(590, 190), new Position(660, 430)}, animationIsOn());

            TransportStationAnimation cashierExit = new TransportStationAnimation(
                    this, "Cashier exit", new Position(1070, 430), animationIsOn());
            TransportStationAnimation bankExit = new TransportStationAnimation(
                    this, "Bank exit", new Position(1160, 430), animationIsOn());
            departureRoute = new TransportRouteAnimation<>(this, "Departure route", 1.0,
                    cashierExit, bankExit,
                    new Position[]{new Position(1070, 430), new Position(1160, 430)}, animationIsOn());
        }

        @Override
        public void doInitialSchedules() {
            for (int i = 0; i < tellerCount; i++) {
                new Teller(this).activate();
            }
            for (int i = 0; i < cashierCount; i++) {
                new Cashier(this).activate();
            }
            new CustomerGenerator(this).activate();
        }

        private double arrivalInterval() {
            double elapsed = presentTime().getTimeAsDouble(SIMULATION_UNIT);
            return (elapsed < 120.0 || elapsed >= 360.0)
                    ? busyArrival.sample()
                    : idleArrival.sample();
        }

        private TimeSpan sampleService(ContDistNormal distribution) {
            return new TimeSpan(Math.max(0.01, distribution.sample()), SIMULATION_UNIT);
        }

        private String nextCustomerName() {
            return "Customer-" + (++nextCustomerId);
        }

        private String nextTellerName() {
            return "Teller-" + (++nextTellerId);
        }

        private String nextCashierName() {
            return "Cashier-" + (++nextCashierId);
        }

        private void recordCompletion(Customer customer) {
            double spent = presentTime().getTimeAsDouble(SIMULATION_UNIT)
                    - customer.getArrivalTime().getTimeAsDouble(SIMULATION_UNIT);
            servedCustomers++;
            totalTimeInBank += spent;
            maximumTimeInBank = Math.max(maximumTimeInBank, spent);
        }

        private long getServedCustomers() {
            return servedCustomers;
        }

        private double getAverageTimeInBank() {
            return servedCustomers == 0 ? 0.0 : totalTimeInBank / servedCustomers;
        }

        private double getMaximumTimeInBank() {
            return maximumTimeInBank;
        }
    }

    private static final class Customer extends SimProcessAnimation {

        private static final EntityTypeAnimation ENTITY_TYPE = createEntityType("Customer");

        private final TimeInstant arrivalTime;

        private Customer(BankModel owner) {
            super(owner, owner.nextCustomerName(), owner.traceIsOn());
            this.arrivalTime = owner.presentTime();
            createAnimation(ENTITY_TYPE.getId(), "default", owner.animationIsOn());
        }

        private TimeInstant getArrivalTime() {
            return arrivalTime;
        }

        @Override
        public void lifeCycle()
                throws DelayedInterruptException, InterruptException, SuspendExecution {
            BankModel model = (BankModel) getModel();

            model.arrivalRoute.insert(this,
                    new TimeSpan(CUSTOMER_TRAVEL_MINUTES, SIMULATION_UNIT));
            hold(new TimeSpan(CUSTOMER_TRAVEL_MINUTES, SIMULATION_UNIT));
            model.arrivalRoute.remove(this);

            model.tellerQueue.insert(this);
            wakeOneTeller(model);
            passivate();

            model.tellerToCashierRoute.insert(this,
                    new TimeSpan(CUSTOMER_TRAVEL_MINUTES, SIMULATION_UNIT));
            hold(new TimeSpan(CUSTOMER_TRAVEL_MINUTES, SIMULATION_UNIT));
            model.tellerToCashierRoute.remove(this);

            model.cashierQueue.insert(this);
            wakeOneCashier(model);
            passivate();

            model.departureRoute.insert(this,
                    new TimeSpan(CUSTOMER_TRAVEL_MINUTES, SIMULATION_UNIT));
            hold(new TimeSpan(CUSTOMER_TRAVEL_MINUTES, SIMULATION_UNIT));
            model.departureRoute.remove(this);
            model.recordCompletion(this);
            disposeAnimation();
        }

        private void wakeOneTeller(BankModel model) {
            if (!model.idleTellers.isEmpty()) {
                Teller teller = model.idleTellers.removeFirst();
                teller.activate();
            }
        }

        private void wakeOneCashier(BankModel model) {
            if (!model.idleCashiers.isEmpty()) {
                Cashier cashier = model.idleCashiers.removeFirst();
                cashier.activate();
            }
        }
    }

    private static final class Teller extends SimProcessAnimation {

        private static final EntityTypeAnimation ENTITY_TYPE = createEntityType("Teller");

        private Teller(BankModel owner) {
            super(owner, owner.nextTellerName(), owner.traceIsOn());
            createAnimation(ENTITY_TYPE.getId(), "default", owner.animationIsOn());
        }

        @Override
        public void lifeCycle()
                throws DelayedInterruptException, InterruptException, SuspendExecution {
            BankModel model = (BankModel) getModel();
            while (true) {
                if (model.tellerQueue.isEmpty()) {
                    model.idleTellers.insert(this);
                    passivate();
                    continue;
                }

                Customer customer = model.tellerQueue.removeFirst();
                model.tellerStation.insert(List.of(customer), List.of(this));
                hold(model.sampleService(model.tellerService));
                model.tellerStation.remove(customer);
                customer.activate();
            }
        }
    }

    private static final class Cashier extends SimProcessAnimation {

        private static final EntityTypeAnimation ENTITY_TYPE = createEntityType("Cashier");

        private Cashier(BankModel owner) {
            super(owner, owner.nextCashierName(), owner.traceIsOn());
            createAnimation(ENTITY_TYPE.getId(), "default", owner.animationIsOn());
        }

        @Override
        public void lifeCycle()
                throws DelayedInterruptException, InterruptException, SuspendExecution {
            BankModel model = (BankModel) getModel();
            while (true) {
                if (model.cashierQueue.isEmpty()) {
                    model.idleCashiers.insert(this);
                    passivate();
                    continue;
                }

                Customer customer = model.cashierQueue.removeFirst();
                model.cashierStation.insert(List.of(customer), List.of(this));
                hold(model.sampleService(model.cashierService));
                model.cashierStation.remove(customer);
                customer.activate();
            }
        }
    }

    private static final class CustomerGenerator extends SimProcess {

        private CustomerGenerator(BankModel owner) {
            super(owner, "Customer generator", owner.traceIsOn());
        }

        @Override
        public void lifeCycle() throws SuspendExecution {
            BankModel model = (BankModel) getModel();
            while (true) {
                hold(new TimeSpan(model.arrivalInterval(), SIMULATION_UNIT));
                new Customer(model).activate();
            }
        }
    }

    private static EntityTypeAnimation createEntityType(String id) {
        EntityTypeAnimation entityType = new EntityTypeAnimation();
        entityType.setId(id);
        entityType.setGenereratedBy(BankQueueSimulation.class.getName());
        // The 2D command format requires at least one state and image mapping.
        entityType.addPossibleState("default", id + "Icon");
        // Names keep the animation readable without requiring external icon files.
        entityType.setShow(EntityType.SHOW_NAME);
        return entityType;
    }

    private static void createAnimationIcons(Path runDirectory) throws IOException {
        writeAnimationIcon(runDirectory.resolve("customer.png"), new Color(42, 111, 180));
        writeAnimationIcon(runDirectory.resolve("teller.png"), new Color(44, 140, 92));
        writeAnimationIcon(runDirectory.resolve("cashier.png"), new Color(195, 123, 37));
    }

    private static void writeAnimationIcon(Path path, Color color) throws IOException {
        BufferedImage image = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        try {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(color);
            graphics.fillRoundRect(1, 1, 28, 28, 8, 8);
            graphics.setColor(Color.WHITE);
            graphics.fillOval(10, 5, 10, 10);
            graphics.fillRoundRect(7, 16, 16, 9, 5, 5);
        } finally {
            graphics.dispose();
        }
        ImageIO.write(image, "png", path.toFile());
    }
}
