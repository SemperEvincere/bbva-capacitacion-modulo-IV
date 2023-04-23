package org.example.crud;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

  List<Cliente> listarClientes() throws SQLException;

  Cliente buscarCliente(int id) throws SQLException;

  void agregarCliente(Cliente cliente) throws SQLException;

  void actualizarCliente(Cliente cliente) throws SQLException;

  void eliminarCliente(int id) throws SQLException;

}
