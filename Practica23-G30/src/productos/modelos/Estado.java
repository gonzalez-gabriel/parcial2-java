/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos.modelos;

/**
 *
 * @author gabri
 */
public enum Estado {
    DISPONIBLE,
    NO_DISPONIBLE;
    
//    private String valor;
//    
//    private Estado(String valor) {
//        this.valor = valor;
//    }

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
