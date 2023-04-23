package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class ConexionBD {

  private static ConexionBD instancia = null;
  private final DataSource dataSource;

  private ConexionBD() {
    MysqlDataSource mySqlDataSource = new MysqlDataSource();
    mySqlDataSource.setUrl("jdbc:mysql://localhost:3306/banco");
    mySqlDataSource.setUser("root");
    mySqlDataSource.setPassword("root");
    dataSource = mySqlDataSource;
  }

  public static ConexionBD getInstance() {
    if(instancia == null) {
      instancia = new ConexionBD();
    }
    return instancia;
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

}
