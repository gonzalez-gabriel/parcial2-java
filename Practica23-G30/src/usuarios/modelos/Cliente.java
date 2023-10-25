/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import java.util.ArrayList;
import pedidos.modelos.Pedido;

/**
 *
 * @author gabri
 */
public class Cliente extends Usuario {
    private final ArrayList<Pedido> pedidos;
    
    public Cliente(String correo, String clave, String apellido, String nombre) {
        super(correo, clave, apellido, nombre);
        this.pedidos = new ArrayList<>();
    }

    @Override
    public ArrayList<Pedido> verPedidos() {
        return this.pedidos;
    }
    
    public void agregarPedido(Pedido pedido) {
        if(!this.pedidos.contains(pedido)) {
            this.pedidos.add(pedido);
        } else {
            int indice = this.pedidos.indexOf(pedido);
            this.pedidos.set(indice, pedido);
        }
    }

    public void cancelarPedido(Pedido pedido) {
        this.pedidos.remove(pedido);
    }
}
