package org.example;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.query.Query;
import java.util.List;

public class ConexionDb4o {

  private static final String DB4O_FILENAME = "./src/main/resources/personas.db4o";
  private ObjectContainer db;

  public void abrirConexion() {
    db = Db4o.openFile(DB4O_FILENAME);
  }

  public void cerrarConexion() {
    db.close();
  }

  public void agregarPersona(Persona persona) {
    db.set(persona);
  }

  public void actualizarPersona(Persona persona) {
    Query query = db.query();
    query.constrain(Persona.class);
    query.descend("nombre").constrain(persona.getNombre()).and(query.descend("edad").constrain(persona.getEdad()));
    List<Persona> result = query.execute();
    if (!result.isEmpty()) {
      Persona personaGuardada = result.get(0);
      personaGuardada.setEdad(persona.getEdad());
      db.set(personaGuardada);
    }
  }

  public Persona buscarPersona(String nombre, int edad) {
    Query query = db.query();
    query.constrain(Persona.class);
    query.descend("nombre").constrain(nombre).and(query.descend("edad").constrain(edad));
    List<Persona> result = query.execute();
    if (result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }
  }


  public void eliminarPersona(Persona persona) {
    db.delete(persona);
  }

  public List<Persona> obtenerPersonas() {
    Query query = db.query();
    query.constrain(Persona.class);
    return query.execute();
  }
}
