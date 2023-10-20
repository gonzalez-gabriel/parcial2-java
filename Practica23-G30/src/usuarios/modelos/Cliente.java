/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import pedidos.modelos.Pedido;

/**
 *
 * @author gabri
 */
public class Cliente {
    private String correo;
    private String clave;
    private String apellido;
    private String nombre;
    private ArrayList<Pedido> pedidos;
    
    public Cliente(String correo, String clave, String apellido, String nombre) {
        this.correo = correo;
        this.clave = clave;
        this.apellido = apellido;
        this.nombre = nombre;
        this.pedidos = new ArrayList<Pedido>();
    }

    public String verCorreo() {
        return correo;
    }

    public void asignarCorreo(String correo) {
        this.correo = correo;
    }

    public String verClave() {
        return clave;
    }

    public void asignarClave(String clave) {
        this.clave = clave;
    }

    public String verApellido() {
        return apellido;
    }

    public void asignarApellido(String apellido) {
        this.apellido = apellido;
    }

    public String verNombre() {
        return nombre;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void quitarPedido(Pedido pedido) {
        this.pedidos.remove(pedido);
    }
    
    public void mostrar () {
        Collections.sort(pedidos, new Comparator<Pedido>() {
            @Override
            public int compare(Pedido p1, Pedido p2) {
//                return Integer.valueOf(p1.verNumero()).compareTo(Integer.valueOf(p2.verNumero()));
                return p2.verNumero() - p1.verNumero();
            }
        });
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Apellido: " + this.apellido);
        System.out.println("Correo: " + this.correo);
        System.out.println("Clave: " + this.clave + "\n");
        System.out.println("Pedidos:");
        System.out.println("------------");
        for(Pedido pedido : pedidos ){
            pedido.mostrar();
            System.out.println("|||||||||");
        }
        System.out.println("------------");
    }
}
