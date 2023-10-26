/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos.modelos;

/**
 *
 * @author gabri
 */
public enum Categoria {
  ENTRADA("Entrada"),
  PLATO_PRINCIPAL("Plato Principal"),
  POSTRE("Postre");

  private String nombre;

  Categoria(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return this.nombre;
  }
}
