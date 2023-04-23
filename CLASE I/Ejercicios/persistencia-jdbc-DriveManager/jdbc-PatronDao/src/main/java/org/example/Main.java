package org.example;

import java.util.List;

public class Main {

  public static void main(String[] args) {
    ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
//    clienteDAO.agregarCliente(new Cliente(1, "Semper", "Evincere", "2524268"));

    Cliente cliente = clienteDAO.obtenerCliente(3);
    System.out.println(cliente);

    List<Cliente> clientes = clienteDAO.obtenerTodosClientes();
    clientes.forEach(System.out::println);

    clienteDAO.actualizarCliente(new Cliente(3, "Semper", "García", "2524268"));
    cliente = clienteDAO.obtenerCliente(3);
    System.out.println(cliente);

    clienteDAO.eliminarCliente(3);
    clientes = clienteDAO.obtenerTodosClientes();
    clientes.forEach(System.out::println);


  }
}