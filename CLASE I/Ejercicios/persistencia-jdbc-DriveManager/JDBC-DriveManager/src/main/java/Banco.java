import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {

  // Definimos los datos de conexión a la base de datos
  static final String DB_URL = "jdbc:mysql://localhost:3306/banco";
  static final String USER = "root";
  static final String PASS = "root";

    public static void main(String[] args) {
      try {
        // Paso 1: Registrar el controlador JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Paso 2: Establecer la conexión
        System.out.println("Conectando a la base de datos...");
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

          // Paso 3: Crear la tabla en la bd si no existe
          String sqlCreateTable = "CREATE TABLE IF NOT EXISTS clientes (" + "id INT(11) NOT NULL AUTO_INCREMENT, " + "nombre VARCHAR(255) NOT NULL, " + "apellido VARCHAR(255) NOT NULL, " + "dni VARCHAR(255) NOT NULL, " + "PRIMARY KEY (id))";
          statement.executeUpdate(sqlCreateTable);
          System.out.println("Tabla clientes creada correctamente.");

          // Paso 4: Crear la sentencia para insertar un cliente
          String sqlInsert = "INSERT INTO clientes (nombre, apellido, dni) VALUES (?, ?, ?)";
          try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, "Juan");
            preparedStatement.setString(2, "Pérez");
            preparedStatement.setString(3, "12345678");

            // Paso 5: Ejecutar la sentencia y procesar el resultado
            System.out.println("Insertando cliente...");
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
              System.out.println("El cliente fue insertado correctamente.");
            }
          }

        } catch (SQLException e) {
          e.printStackTrace();
        }

      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } finally {
        System.out.println("Conexión cerrada.");
      }
    }
  }
