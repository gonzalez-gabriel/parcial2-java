/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import java.util.ArrayList;
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

        Producto p1 = new Producto(1, "Milanesa con puré", "PLATO PRINCIPAL", "DISPONIBLE", 1250.0f);
        Producto p2 = new Producto(2, "Empanadas", "PLATO PRINCIPAL", "DISPONIBLE", 150.70f);
        Producto p3 = new Producto(3, "Flan con dulce", "POSTRE", "NO DISPONIBLE", 850.0f);

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

        System.out.println("\nLISTA DE CLIENTES");
        for (Cliente cliente : listaClientes) {
            cliente.mostrar();
        }

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
        p1.asignarCategoria("PLATO PRINCIPAL MODIFICADO");
        System.out.println(p1.verCategoria());
        
    }
}
