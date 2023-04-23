package org.example;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {


  void agregarCliente(Cliente cliente);

  void actualizarCliente(Cliente cliente);

  void eliminarCliente(int id);

  Cliente obtenerCliente(int id);

  List<Cliente> obtenerTodosClientes();
}
