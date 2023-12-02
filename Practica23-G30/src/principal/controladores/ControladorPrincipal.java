/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.ProductoDelPedido;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.GestorProductos;
import usuarios.modelos.Cliente;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Perfil;

/**
 *
 * @author gabri
 */
public class ControladorPrincipal {

    public static void main(String[] args) {

        System.out.println("\n\nProductos");
        GestorProductos gp = GestorProductos.crear();
        System.out.println(gp.crearProducto(1, "", 10, Categoria.ENTRADA, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(2, "Asado", 20, Categoria.PLATOPRINCIPAL, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(3, "Flan", 30, Categoria.POSTRE, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(4, "Matambre", 40, Categoria.PLATOPRINCIPAL, Estado.DISPONIBLE));
        System.out.println(gp.menu());
        
        System.out.println("\nUsuarios");
        GestorUsuarios gu = GestorUsuarios.crear();
        System.out.println(gu.crearUsuario("usuario1@gmail.com", "Apellido1", "nombre1", Perfil.CLIENTE, "clave1", "clave1"));
        System.out.println(gu.crearUsuario("usuario1@gmail.com", "Apellido2", "nombre2", Perfil.CLIENTE, "clave2", "clave2"));
        System.out.println(gu.crearUsuario("usuario3@gmail.com", "Apellido3", "nombre3", Perfil.CLIENTE, "clave3", "clave3"));
        System.out.println(gu.crearUsuario("usuario4@gmail.com", "Apellido4", "nombre4", Perfil.CLIENTE, "clave4", "clave4"));
        System.out.println(gu.verUsuarios());
        System.out.println(gu.existeEsteUsuario(gu.verUsuarios().get(0)));
        System.out.println(gu.borrarUsuario(gu.verUsuarios().get(1)));
        System.out.println(gu.buscarUsuarios(""));
        System.out.println(gu.buscarUsuarios("Apellido3"));
        System.out.println(gu.buscarUsuarios("Apellido4"));

        System.out.println("\nPedidos");
        GestorPedidos gped = GestorPedidos.crear();
        ArrayList<ProductoDelPedido> pdps = new ArrayList<>();
        pdps.add(new ProductoDelPedido(gp.menu().get(0),1));
        System.out.println(gped.crearPedido(1, LocalDate.now(), LocalTime.now(),pdps, (Cliente)gu.verUsuarios().get(0)));
        System.out.println(gped.verPedidos());
    }
}
