package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
  static final String DB_URL = "jdbc:mysql://localhost:3306/banco";
  static final String USER = "root";
  static final String PASS = "root";

  public static Connection getConnection() throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return DriverManager.getConnection(DB_URL, USER, PASS);
  }
}

