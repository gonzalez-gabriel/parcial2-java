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

/**
 *
 * @author gabri
 */
public class ControladorPrincipal {

    public static void main(String[] args) {
        Cliente c1 = new Cliente("juan1_c@gmail.com", "secreto1", "Juan 1", "Martinez 1");
        Cliente c2 = new Cliente("juan2_c@gmail.com", "secreto2", "Juan 2", "Martinez 2");
        Cliente c3 = new Cliente("juan3_c@gmail.com", "secreto3", "Juan 3", "Martinez 3");

        Empleado em1 = new Empleado("Miguell_em@gmail.com", "secreto1", "Miguel 1", "Gonzalez 1");
        Empleado em2 = new Empleado("Miguel2_em@gmail.com", "secreto2", "Miguel 2", "Gonzalez 2");
        Empleado em3 = new Empleado("Miguel3_em@gmail.com", "secreto3", "Miguel 3", "Gonzalez 3");

        Encargado en1 = new Encargado("Martin1_en@gmail.com", "secreto1", "Martin 1", "Juarez 1");
        Encargado en2 = new Encargado("Martin2_en@gmail.com", "secreto2", "Martin 2", "Juarez 2");
        Encargado en3 = new Encargado("Martin3_en@gmail.com", "secreto3", "Martin 3", "Juarez 3");

        Producto p1 = new Producto(1, "Milanesa con pure", Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE, 1250.0f);
        Producto p2 = new Producto(2, "Empanadas", Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE, 150.70f);
        Producto p3 = new Producto(3, "Flan con dulce", Categoria.POSTRE, Estado.NO_DISPONIBLE, 850.0f);

        Pedido pedido1 = new Pedido(1, LocalDateTime.now(), c1, pedidos.modelos.Estado.CREADO);
        Pedido pedido2 = new Pedido(2, LocalDateTime.now(), c1, pedidos.modelos.Estado.SOLICITADO);
        Pedido pedido3 = new Pedido(3, LocalDateTime.now(), c2, pedidos.modelos.Estado.ENTREGADO);
        
        Pedido pedido4 = new Pedido(4, LocalDateTime.now(), c3, pedidos.modelos.Estado.CREADO);
        Pedido pedido5 = new Pedido(5, LocalDateTime.now(), c3, pedidos.modelos.Estado.SOLICITADO);
        Pedido pedido6 = new Pedido(6, LocalDateTime.now(), c3, pedidos.modelos.Estado.ENTREGADO);
        
        ProductoDelPedido pdp1 = new ProductoDelPedido(12, p3);
        ProductoDelPedido pdp2 = new ProductoDelPedido(23, p2);
        ProductoDelPedido pdp3 = new ProductoDelPedido(2, p1);
       
        
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        ArrayList<Encargado> listaEncargados = new ArrayList<>();
        ArrayList<Producto> listaProductos = new ArrayList<>();

        listaClientes.add(c1);
        listaClientes.add(c2);
        listaClientes.add(c3);

        listaEmpleados.add(em1);
        listaEmpleados.add(em2);
        listaEmpleados.add(em3);

        listaEncargados.add(en1);
        listaEncargados.add(en2);
        listaEncargados.add(en3);

        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);

        System.out.println("\nLISTA DE EMPLEADOS");
        for (Empleado empleado : listaEmpleados) {
            empleado.mostrar();
        }

        System.out.println("\nLISTA DE ENCARGADOS");
        for (Encargado encargado : listaEncargados) {
            encargado.mostrar();
        }

        System.out.println("\nLISTA DE PRODUCTOS");
        for (Producto producto : listaProductos) {
            producto.mostrar();
        }
        
        System.out.println(p1.verCategoria());
        p1.asignarCategoria(Categoria.ENTRADA);
        System.out.println(p1.verCategoria());
        
        
        System.out.println("\nLISTA DE CLIENTES");
        for (Cliente cliente : listaClientes) {
            cliente.mostrar();
        }
        
        c1.agregarPedido(pedido2);
        c1.agregarPedido(pedido1);
        
        c2.agregarPedido(pedido3);
        
        c3.agregarPedido(pedido6);
        c3.agregarPedido(pedido4);
        c3.agregarPedido(pedido5);
        
        pedido2.agregarProductoDelPedido(pdp1);
        pedido2.agregarProductoDelPedido(pdp2);
        
        pedido3.agregarProductoDelPedido(pdp3);
        pedido3.agregarProductoDelPedido(pdp1);
        pedido3.agregarProductoDelPedido(pdp2);
        
        pedido4.agregarProductoDelPedido(pdp3);
        
        pedido6.agregarProductoDelPedido(pdp1);
        pedido6.agregarProductoDelPedido(pdp2);
        pedido5.agregarProductoDelPedido(pdp3);
        
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        listaPedidos.add(pedido6);
        listaPedidos.add(pedido5);

        listaPedidos.add(pedido4);
        listaPedidos.add(pedido3);
        listaPedidos.add(pedido2);
        listaPedidos.add(pedido1);


        System.out.println("\nLISTA DE CLIENTES");
        for (Cliente cliente : listaClientes) {
            cliente.mostrar();
        }
        
        System.out.println("\nLISTA DE PEDIDOS");
        for (Pedido pedido : listaPedidos) {
            pedido.mostrar();
        }
        
    }
}
