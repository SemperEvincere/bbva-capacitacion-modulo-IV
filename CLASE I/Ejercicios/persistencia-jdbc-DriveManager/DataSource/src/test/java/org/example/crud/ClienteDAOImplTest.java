package org.example.crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.example.crud.Cliente.TipoCuenta;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ClienteDAOImplTest {

  private ClienteDAO clienteDAO;
  private Connection conexion;

  @Before
  public void setUp() throws Exception {
    // Configurar la conexión y crear la tabla de clientes
    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/banco");
    dataSource.setUser("root");
    dataSource.setPassword("root");
    conexion = dataSource.getConnection();
    Statement stmt = conexion.createStatement();
    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS clientes (id INT NOT NULL AUTO_INCREMENT, nombre VARCHAR(255), apellido VARCHAR(255), email VARCHAR(255), tipo_cuenta VARCHAR(50), PRIMARY KEY (id))");
    stmt.close();

    // Crear el DAO
    clienteDAO = new ClienteDAOImpl(dataSource);
  }

//  @After
//  public void tearDown() throws Exception {
//    // Eliminar la tabla de clientes y cerrar la conexión
//    Statement stmt = conexion.createStatement();
//    stmt.executeUpdate("DROP TABLE clientes");
//    stmt.close();
//    conexion.close();
//  }

  @Test
  public void testCrearCliente() throws Exception {
    // Crear un nuevo cliente y guardarlo en la base de datos
    Cliente cliente = new Cliente();
    cliente.setNombre("Juan");
    cliente.setApellido("Pérez");
    cliente.setEmail("juan.perez@ejemplo.com");
    cliente.setTipoCuenta(TipoCuenta.CAJA_DE_AHORRO);
    clienteDAO.agregarCliente(cliente);

    // Verificar que el cliente se haya creado correctamente
    Statement stmt = conexion.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM clientes");
    rs.next();
    assertEquals(1, rs.getInt(1));
    rs.close();
    stmt.close();

    // Obtener el cliente recién creado de la base de datos
    stmt = conexion.createStatement();
    rs = stmt.executeQuery("SELECT * FROM clientes");
    rs.next();
    Cliente clienteRecuperado = new Cliente();
    clienteRecuperado.setId(rs.getInt("id"));
    clienteRecuperado.setNombre(rs.getString("nombre"));
    clienteRecuperado.setApellido(rs.getString("apellido"));
    clienteRecuperado.setEmail(rs.getString("email"));
    clienteRecuperado.setTipoCuenta(TipoCuenta.valueOf(rs.getString("tipo_cuenta")));
    rs.close();
    stmt.close();

    // Verificar que el cliente recuperado sea igual al cliente original
    assertNotNull(clienteRecuperado.getId());
    assertEquals(cliente.getNombre(), clienteRecuperado.getNombre());
    assertEquals(cliente.getApellido(), clienteRecuperado.getApellido());
    assertEquals(cliente.getEmail(), clienteRecuperado.getEmail());
    assertEquals(cliente.getTipoCuenta(), clienteRecuperado.getTipoCuenta());
  }

  @Test
  public void listarClientes() {
  }
}
