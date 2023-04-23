package org.example;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ConexionDb4o conexion = new ConexionDb4o();
    conexion.abrirConexion();

    Persona persona1 = new Persona("Semper", 44);
    Persona persona2 = new Persona("Evincere", 20);
    conexion.agregarPersona(persona1);
    conexion.agregarPersona(persona2);
    conexion.cerrarConexion();

  }
}