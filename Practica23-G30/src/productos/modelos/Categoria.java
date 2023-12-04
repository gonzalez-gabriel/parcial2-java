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

    public static Categoria fromString(String nombre) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.nombre.equalsIgnoreCase(nombre)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("No se encontró una categoría con el nombre: " + nombre);
    }
}
