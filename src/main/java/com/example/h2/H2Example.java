package com.example.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Example {
    public static void main(String[] args) {

        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"; // JVM 存活期间一直保留
        try (
            Connection conn = DriverManager.getConnection(url, "sa", "");
            Statement stmt = conn.createStatement()
        ) {
            // 1. 创建表
            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(50))");

            // 2. 插入数据
            stmt.executeUpdate("INSERT INTO users VALUES (1, 'Alice')");
            stmt.executeUpdate("INSERT INTO users VALUES (2, 'Bob')");

            // 3. 查询数据
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

