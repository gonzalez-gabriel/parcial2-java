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
    ENTRADA,
    PLATO_PRINCIPAL,
    POSTRE;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
