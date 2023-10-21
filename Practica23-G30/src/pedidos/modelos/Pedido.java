/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidos.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import usuarios.modelos.Cliente;

/**
 *
 * @author gabri
 */
public class Pedido {
    private int numero;
    private LocalDateTime fechaYHora;
    private Cliente cliente;
    private Estado estado;
    private ArrayList<ProductoDelPedido> productosDelPedido;
    
    public Pedido(int numero, LocalDateTime fechaYHora, Cliente cliente, Estado estado) {
        this.cliente = cliente;
        this.estado = estado;
        this.fechaYHora = fechaYHora;
        this.numero = numero;
        this.productosDelPedido = new ArrayList<ProductoDelPedido>();
    }
    
    public void agregarProductoDelPedido(ProductoDelPedido pdp) {
        this.productosDelPedido.add(pdp);
    }
    
    public int verNumero() {
        return this.numero;
    }

    public LocalDateTime verFechaYHora() {
        return this.fechaYHora;
    }
    
    public LocalDate verFecha() {
        return this.fechaYHora.toLocalDate();
    }

    public LocalTime verHora() {
        return this.fechaYHora.toLocalTime();
    }

    public void cambiarFecha(LocalDate fecha) {
        this.fechaYHora = this.fechaYHora.with(fecha);
    }

    public void cambiarHora(LocalTime hora) {
        this.fechaYHora = this.fechaYHora.with(hora);
    }


    public Cliente verCliente() {
        return this.cliente;
    }

    public Estado verEstado() {
        return this.estado;
    }

    public void cambiarEstado(Estado estado) {
        this.estado = estado;
    }

    public void cambiarFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public void cambiarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void cambiarNumero(int numero) {
        this.numero = numero;
    }
    
    public void mostrar() {
        System.out.println("Nro: " + this.numero);
        System.out.println("Fecha: " + this.verFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Hora: " + this.verHora().format(DateTimeFormatter.ofPattern("HH:mm")));
        System.out.println("Cliente: " + this.cliente.verApellido() + ", " + this.cliente.verNombre());
        System.out.println("Estado: " + this.verEstado());
        System.out.printf("%-20s %s%n", "Producto", "Cantidad");
        System.out.println("=============================");
        this.productosDelPedido.forEach(pdp -> {
            System.out.printf("%-20s %d %n", pdp.verProducto(), pdp.verCantidad());
        });
        System.out.println("");
    }
}
