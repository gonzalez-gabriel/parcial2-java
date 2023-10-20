/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidos.modelos;

/**
 *
 * @author gabri
 */
public enum Estado {
    CREADO,
    SOLICITADO,
    PROCESANDO,
    ENTREGADO;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
