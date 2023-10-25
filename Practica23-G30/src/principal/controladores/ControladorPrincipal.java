/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import java.time.LocalDateTime;
import java.util.ArrayList;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;
import usuarios.modelos.Empleado;
import usuarios.modelos.Encargado;
import usuarios.modelos.Usuario;

/**
 *
 * @author gabri
 */
public class ControladorPrincipal {

    public static void main(String[] args) {
        /*
        INICIO Primera parte - comparación de objetos
         */
//        ArrayList<Producto> listaProductos = new ArrayList<>();
//        ArrayList<Pedido> listaPedidos = new ArrayList<>();
//        ArrayList<Cliente> listaClientes = new ArrayList<>();
//
//        System.out.println("#################### ");
//        System.out.println("PRODUCTOS");
//        Producto p1 = new Producto(1, "Producto 1", Categoria.ENTRADA, Estado.DISPONIBLE, 200.0f);
//        Producto p2 = new Producto(2, "Producto 2", Categoria.PLATOPRINCIPAL, Estado.DISPONIBLE, 1950.0f);
//        Producto p3 = new Producto(3, "Producto 3", Categoria.POSTRE, Estado.NO_DISPONIBLE, 580.0f);
//        Producto p4 = new Producto(4, "Producto 4", Categoria.POSTRE, Estado.NO_DISPONIBLE, 580.0f);
//        Producto p5 = new Producto(3, "Producto 5", Categoria.POSTRE, Estado.NO_DISPONIBLE, 7580.0f);
//        //no debe agregar a p5
//
//        if (!listaProductos.contains(p1)) {
//            listaProductos.add(p1);
//        }
//        if (!listaProductos.contains(p2)) {
//            listaProductos.add(p2);
//        }
//        if (!listaProductos.contains(p3)) {
//            listaProductos.add(p3);
//        }
//        if (!listaProductos.contains(p4)) {
//            listaProductos.add(p4);
//        }
//        if (!listaProductos.contains(p5)) {
//            listaProductos.add(p5);
//        }
//
//        for (Producto p : listaProductos) {
//            p.mostrar();
//        }
//
//        System.out.println("#################### ");
//        System.out.println("CLIENTES");
//        Cliente cliente1 = new Cliente("cliente1@bar.com", "claveCliente1", "ApellidoCliente1", "NombreCliente1");
//        Cliente cliente2 = new Cliente("cliente2@bar.com", "claveCliente2", "ApellidoCliente2", "NombreCliente2");
//        Cliente cliente3 = new Cliente("cliente3@bar.com", "claveCliente3", "ApellidoCliente3", "NombreCliente3");
//
//        listaClientes.add(cliente1);
//        listaClientes.add(cliente2);
//        listaClientes.add(cliente3);
//
//        for (Cliente e : listaClientes) {
//            e.mostrar();
//        }
//
//        System.out.println("#################### ");
//        System.out.println("PEDIDOS");
//
//        ArrayList<ProductoDelPedido> listapdp1 = new ArrayList<>();
//        ProductoDelPedido pdp1 = new ProductoDelPedido(listaProductos.get(0), 1);
//        ProductoDelPedido pdp2 = new ProductoDelPedido(listaProductos.get(1), 2);
//        if (!listapdp1.contains(pdp1)) {
//            listapdp1.add(pdp1);
//        }
//        if (!listapdp1.contains(pdp2)) {
//            listapdp1.add(pdp2);
//        }
//        Pedido unPedido1 = new Pedido(1, LocalDateTime.now(), listapdp1, cliente1);
//
//        ArrayList<ProductoDelPedido> productosDelPedido2 = new ArrayList<>();
//        ProductoDelPedido pdp3 = new ProductoDelPedido(listaProductos.get(2), 10);
//        ProductoDelPedido pdp4 = new ProductoDelPedido(listaProductos.get(0), 20);
//        ProductoDelPedido pdp5 = new ProductoDelPedido(listaProductos.get(2), 30);
//        //producto repetido        
//        if (!productosDelPedido2.contains(pdp3)) {
//            productosDelPedido2.add(pdp3);
//        }
//        if (!productosDelPedido2.contains(pdp4)) {
//            productosDelPedido2.add(pdp4);
//        }
//        if (!productosDelPedido2.contains(pdp5)) {
//            productosDelPedido2.add(pdp5);
//        }
//        Pedido unPedido2 = new Pedido(2, LocalDateTime.now(), productosDelPedido2, cliente2);
//
//        ArrayList<ProductoDelPedido> productosDelPedido3 = new ArrayList<>();
//        ProductoDelPedido pdp6 = new ProductoDelPedido(listaProductos.get(1), 100);
//        ProductoDelPedido pdp7 = new ProductoDelPedido(listaProductos.get(2), 200);
//        if (!productosDelPedido3.contains(pdp6)) {
//            productosDelPedido3.add(pdp6);
//        }
//        if (!productosDelPedido3.contains(pdp7)) {
//            productosDelPedido3.add(pdp7);
//        }
//        Pedido unPedido3 = new Pedido(2, LocalDateTime.now(), productosDelPedido3, cliente3);
//        //pedido repetido
//
//        if (!listaPedidos.contains(unPedido1)) {
//            listaPedidos.add(unPedido1);
//        }
//        if (!listaPedidos.contains(unPedido2)) {
//            listaPedidos.add(unPedido2);
//        }
//        if (!listaPedidos.contains(unPedido3)) {
//            listaPedidos.add(unPedido3);
//        }
//
//        System.out.println("Pedidos");
//        System.out.println("=======");
//        for (Pedido p : listaPedidos) {
//            p.mostrar();
//            System.out.println();
//        }
//        System.out.println();

        /*
     FIN Primera parte - comparación de objetos
         */
        
    /*
    INICIO Segunda parte - herencia
    */
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        Usuario unCliente1 = new Cliente("cliente1@bar.com", "claveCliente1", "ApellidoCliente1", "NombreCliente1");        
        Usuario unCliente2 = new Cliente("cliente2@bar.com", "claveCliente2", "ApellidoCliente2", "NombreCliente2");       
        Usuario unCliente3 = new Cliente("cliente3@bar.com", "claveCliente3", "ApellidoCliente3", "NombreCliente3");
        Usuario unCliente4 = new Cliente("cliente3@bar.com", "claveCliente4", "ApellidoCliente4", "NombreCliente4"); 
        //unCliente4 repetido con unCliente3
        
        if (!usuarios.contains(unCliente1))
            usuarios.add(unCliente1);
        if (!usuarios.contains(unCliente2))
            usuarios.add(unCliente2);
        if (!usuarios.contains(unCliente3))
            usuarios.add(unCliente3);
        if (!usuarios.contains(unCliente4))
            usuarios.add(unCliente4);
        
        System.out.println("Clientes");
        System.out.println("========");
        for(Usuario c : usuarios) {
            if (c instanceof Cliente) {
                c.mostrar();
                System.out.println();
            }
        }
        System.out.println();        
                
        Usuario unEmpleado1 = new Empleado("empleado1@bar.com", "claveEmpleado1", "ApellidoEmpleado1", "NombreEmpleado1");        
        Usuario unEmpleado2 = new Empleado("empleado2@bar.com", "claveEmpleado2", "ApellidoEmpleado2", "NombreEmpleado2");        
        Usuario unEmpleado3 = new Empleado("empleado3@bar.com", "claveEmpleado3", "ApellidoEmpleado3", "NombreEmpleado3");
        Usuario unEmpleado4 = new Empleado("empleado3@bar.com", "claveEmpleado4", "ApellidoEmpleado4", "NombreEmpleado4");
        //empleado repetido
        
        if (!usuarios.contains(unEmpleado1))
            usuarios.add(unEmpleado1);
        if (!usuarios.contains(unEmpleado2))
            usuarios.add(unEmpleado2);
        if (!usuarios.contains(unEmpleado3))
            usuarios.add(unEmpleado3);
        if (!usuarios.contains(unEmpleado4))
            usuarios.add(unEmpleado4);
        
        System.out.println("Empleados");
        System.out.println("=========");
        for(Usuario e : usuarios) {
            if (e instanceof Empleado) {
                e.mostrar();
                System.out.println();
            }
        }
        System.out.println();

        Usuario unEncargado1 = new Encargado("encargado1@bar.com", "claveEncargado1", "ApellidoEncargado1", "NombreEncargado1");
        Usuario unEncargado2 = new Encargado("encargado2@bar.com", "claveEncargado2", "ApellidoEncargado2", "NombreEncargado2");
        Usuario unEncargado3 = new Encargado("encargado3@bar.com", "claveEncargado3", "ApellidoEncargado3", "NombreEncargado3");
        Usuario unEncargado4 = new Encargado("encargado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4");
        //encargado repetido

        if(!usuarios.contains(unEncargado1))
            usuarios.add(unEncargado1);
        if(!usuarios.contains(unEncargado2))
            usuarios.add(unEncargado2);
        if(!usuarios.contains(unEncargado3))
            usuarios.add(unEncargado3);
        if(!usuarios.contains(unEncargado4))
            usuarios.add(unEncargado4);
        
        System.out.println("Encargados");
        System.out.println("==========");
        for(Usuario e : usuarios) {
            if (e instanceof Encargado) {
                e.mostrar();
                System.out.println();
            }
        }
        System.out.println();   
        
        Usuario unEncargado5 = new Encargado("cliente1@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4");
        if(!usuarios.contains(unEncargado5))
            usuarios.add(unEncargado5);
        //mismo correo que un cliente, no debe agregarse
        
        Usuario unCliente5 = new Encargado("empleado3@bar.com", "claveEncargado4", "ApellidoEncargado4", "NombreEncargado4");
        if(!usuarios.contains(unCliente5))
            usuarios.add(unCliente5);
        //mismo correo que un empleado, no debe agregarse
        
        System.out.println("Todos");
        System.out.println("=====");
        for(Usuario u : usuarios) {
            u.mostrar();
            System.out.println();
        }
        System.out.println();
    
    /*
    FIN Segunda parte - herencia
    */
    }
}
