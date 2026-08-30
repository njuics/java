package com.example.csp;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

/**
 * 使用 Choco Solver 求解数独问题
 * 
 * 数独规则：
 * 1. 每行包含 1-9 的数字，且不重复
 * 2. 每列包含 1-9 的数字，且不重复
 * 3. 每个 3x3 宫格包含 1-9 的数字，且不重复
 */
public class SudokuSolver {
    public static void main(String[] args) {
        // 定义一个数独谜题（0 表示空白）
        int[][] puzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("原始数独谜题：");
        printSudoku(puzzle);
        System.out.println();

        // 求解数独
        int[][] solution = solveSudoku(puzzle);
        
        if (solution != null) {
            System.out.println("数独解：");
            printSudoku(solution);
        } else {
            System.out.println("无解！");
        }
    }

    /**
     * 使用 Choco Solver 求解数独
     */
    public static int[][] solveSudoku(int[][] puzzle) {
        // 1. 创建模型
        Model model = new Model("Sudoku");

        // 2. 创建变量：9x9 的网格，每个单元格的值范围是 1-9
        IntVar[][] grid = new IntVar[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = model.intVar("cell[" + i + "," + j + "]", 1, 9);
            }
        }

        // 3. 添加约束：已给定的数字
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (puzzle[i][j] != 0) {
                    model.arithm(grid[i][j], "=", puzzle[i][j]).post();
                }
            }
        }

        // 4. 添加约束：每行的数字都不相同
        for (int i = 0; i < 9; i++) {
            model.allDifferent(grid[i]).post();
        }

        // 5. 添加约束：每列的数字都不相同
        for (int j = 0; j < 9; j++) {
            IntVar[] column = new IntVar[9];
            for (int i = 0; i < 9; i++) {
                column[i] = grid[i][j];
            }
            model.allDifferent(column).post();
        }

        // 6. 添加约束：每个 3x3 宫格的数字都不相同
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                IntVar[] box = new IntVar[9];
                int index = 0;
                for (int i = boxRow * 3; i < boxRow * 3 + 3; i++) {
                    for (int j = boxCol * 3; j < boxCol * 3 + 3; j++) {
                        box[index++] = grid[i][j];
                    }
                }
                model.allDifferent(box).post();
            }
        }

        // 7. 求解
        Solver solver = model.getSolver();
        if (solver.solve()) {
            // 提取解
            int[][] solution = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    solution[i][j] = grid[i][j].getValue();
                }
            }
            return solution;
        } else {
            return null;
        }
    }

    /**
     * 打印数独网格
     */
    private static void printSudoku(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (grid[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

