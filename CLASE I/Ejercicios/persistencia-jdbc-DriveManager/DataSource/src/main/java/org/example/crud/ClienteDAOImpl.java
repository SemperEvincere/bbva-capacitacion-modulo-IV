package org.example.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ClienteDAOImpl implements ClienteDAO {

  private DataSource dataSource;

  public ClienteDAOImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<Cliente> listarClientes() throws SQLException {
    List<Cliente> clientes = new ArrayList<>();

    try (Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement()) {
        String query = "SELECT * FROM cliente";
        ResultSet rs = stmt.executeQuery(query);

      while (rs.next()) {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String email = rs.getString("email");
        String tipoCuentaStr = rs.getString("tipo_cuenta");
        Cliente.TipoCuenta tipoCuenta = Cliente.TipoCuenta.valueOf(tipoCuentaStr.toUpperCase());

        Cliente cliente = new Cliente(id, nombre, apellido, email, tipoCuenta);
        clientes.add(cliente);
      }
    }

    return clientes;
  }

  @Override
  public Cliente buscarCliente(int id) throws SQLException {
    return null;
  }

  @Override
  public void agregarCliente(Cliente cliente) throws SQLException {
    String query = "INSERT INTO clientes (nombre, apellido, email, tipo_cuenta) VALUES (?, ?, ?, ?)";

    try (Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query)) {

      pstmt.setString(1, cliente.getNombre());
      pstmt.setString(2, cliente.getApellido());
      pstmt.setString(3, cliente.getEmail());
      pstmt.setString(4, cliente.getTipoCuenta().toString());

      pstmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void actualizarCliente(Cliente cliente) throws SQLException {

  }

  @Override
  public void eliminarCliente(int id) throws SQLException {

  }
}
