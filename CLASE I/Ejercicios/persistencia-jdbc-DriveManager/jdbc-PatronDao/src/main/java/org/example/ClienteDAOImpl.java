package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDao {


  private final Connection connection;

  public ClienteDAOImpl()  {
    try {
      this.connection = Conexion.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void agregarCliente(Cliente cliente) {
    String query = "INSERT INTO clientes (nombre, apellido, dni) VALUES (?, ?, ?)";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, cliente.getNombre());
      statement.setString(2, cliente.getApellido());
      statement.setString(3, cliente.getDni());

      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error al agregar cliente: " + e.getMessage());
    }
  }

  @Override
  public void actualizarCliente(Cliente cliente) {
    String query = "UPDATE clientes SET nombre=?, apellido=?, dni=? WHERE id=?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setString(1, cliente.getNombre());
      statement.setString(2, cliente.getApellido());
      statement.setString(3, cliente.getDni());
      statement.setInt(4, cliente.getId());

      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error al actualizar cliente: " + e.getMessage());
    }
  }

  @Override
  public void eliminarCliente(int id) {
    String query = "DELETE FROM clientes WHERE id=?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, id);

      statement.executeUpdate();
    } catch (SQLException e) {
      System.out.println("Error al eliminar cliente: " + e.getMessage());
    }
  }

  @Override
  public Cliente obtenerCliente(int id) {
    String query = "SELECT id, nombre, apellido, dni FROM clientes WHERE id=?";
    Cliente cliente = null;

    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.setInt(1, id);

      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next()) {
        cliente = new Cliente();
        cliente.setId(resultSet.getInt("id"));
        cliente.setNombre(resultSet.getString("nombre"));
        cliente.setApellido(resultSet.getString("apellido"));
        cliente.setDni(resultSet.getString("dni"));
      }

    } catch (SQLException e) {
      System.out.println("Error al obtener cliente: " + e.getMessage());
    }

    return cliente;
  }

  @Override
  public List<Cliente> obtenerTodosClientes() {
    String query = "SELECT id, nombre, apellido, dni FROM clientes";
    List<Cliente> clientes = new ArrayList<>();

    try (PreparedStatement statement = connection.prepareStatement(query)) {

      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        Cliente cliente = new Cliente();
        cliente.setId(resultSet.getInt("id"));
        cliente.setNombre(resultSet.getString("nombre"));
        cliente.setApellido(resultSet.getString("apellido"));
        cliente.setDni(resultSet.getString("dni"));
        clientes.add(cliente);
      }

    } catch (SQLException e) {
      System.out.println("Error al obtener clientes: " + e.getMessage());
    }

    return clientes;
  }
}
