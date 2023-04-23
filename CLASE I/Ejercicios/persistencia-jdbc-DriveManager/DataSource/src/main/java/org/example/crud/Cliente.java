package org.example.crud;

public class Cliente {

  private int id;
  private String nombre;
  private String apellido;
  private String email;
  private TipoCuenta tipoCuenta;

  public enum TipoCuenta {
    CAJA_DE_AHORRO,
    CUENTA_CORRIENTE
  }

  public Cliente() {}

  public Cliente(int id, String nombre, String apellido, String email, TipoCuenta tipoCuenta) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.tipoCuenta = tipoCuenta;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public TipoCuenta getTipoCuenta() {
    return tipoCuenta;
  }

  public void setTipoCuenta(TipoCuenta tipoCuenta) {
    this.tipoCuenta = tipoCuenta;
  }
}
