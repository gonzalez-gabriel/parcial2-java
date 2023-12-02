/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos.modelos;

import interfaces.IGestorProductos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;

/**
 *
 * @author gabri
 */
public class GestorProductos implements IGestorProductos {

    private static GestorProductos gestor;
    private ArrayList<Producto> productos = new ArrayList<>();

    private GestorProductos() {
    }

    public static GestorProductos instanciar() {
        if (gestor == null) {
            gestor = new GestorProductos();
        }

        return gestor;
    }

    @Override
    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        String validez = validarDatos(codigo, descripcion, precio, categoria, estado);
        if (validez.equals(VALIDACION_EXITO)) {
            Producto p = new Producto(codigo, descripcion, categoria, estado, precio);
            if (this.productos.contains(p)) {
                p = null;
                return PRODUCTOS_DUPLICADOS;
            } else {
                this.productos.add(p);
                return EXITO_CREADO;
            }
        } else {
            return validez;
        }
    }

    @Override
    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (this.productos.isEmpty() || !this.productos.contains(productoAModificar)) {
            return PRODUCTO_INEXISTENTE;
        }

        String validez = validarDatos(codigo, descripcion, precio, categoria, estado);

        if (validez.equals(VALIDACION_EXITO)) {
            return validez;
        }

        int i = this.productos.indexOf(productoAModificar);
        productoAModificar.asignarCodigo(codigo);
        productoAModificar.asignarDescripcion(descripcion);
        productoAModificar.asignarPrecio(precio);
        productoAModificar.asignarCategoria(categoria);
        productoAModificar.asignarEstado(estado);
        this.productos.set(i, productoAModificar);

        return EXITO_MODIFICADO;

    }

    @Override
    public List<Producto> menu() {
        Comparator<Producto> comparatorCategoriaYDescripcion = (Producto p1, Producto p2) -> {
            if (!p1.verCategoria().equals(p2.verCategoria())) {
                return p1.verCategoria().compareTo(p2.verCategoria());
            } else {
                return p1.verDescripcion().compareTo(p2.verDescripcion());
            }
        };
        Collections.sort(productos, comparatorCategoriaYDescripcion);
        return this.productos;
    }

    @Override
    public List<Producto> buscarProductos(String descripcion) {
        if (descripcion.isBlank() || descripcion.isEmpty()) {
            return null;
        }
        ArrayList<Producto> productosEncontrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.verDescripcion().equals(descripcion) || p.verDescripcion().startsWith(descripcion)) {
                productosEncontrados.add(p);
            }
        }

        Comparator<Producto> comparatorCategoriaYDescripcion = (Producto p1, Producto p2) -> {
            if (!p1.verCategoria().equals(p2.verCategoria())) {
                return p1.verCategoria().compareTo(p2.verCategoria());
            } else {
                return p1.verDescripcion().compareTo(p2.verDescripcion());
            }
        };
        Collections.sort(productosEncontrados, comparatorCategoriaYDescripcion);

        return productosEncontrados;
    }

    @Override
    public String borrarProducto(Producto producto) {
        GestorProductos gp = GestorProductos.instanciar();
        if (gp.menu().isEmpty()) {
            return ERROR_BORRADO;
        }
        GestorPedidos gped = GestorPedidos.instanciar();
        for (Pedido p : gped.verPedidos()) {
            for (ProductoDelPedido pdp : p.verPdp()) {
                if (producto.equals(pdp.verProducto())) {
                    return ERROR_BORRADO;
                }
            }
        }
        this.productos.remove(producto);
        return EXITO_BORRADO;
    }

    @Override
    public boolean existeEsteProducto(Producto producto) {
        return this.productos.contains(producto);
    }

    @Override
    public ArrayList<Producto> verProductosPorCategoria(Categoria categoria) {
        ArrayList<Producto> productoPorCategoria = new ArrayList<>();
        for (Producto p : productos) {
            if (p.verCategoria() == categoria) {
                productoPorCategoria.add(p);
            }
        }
        
        Comparator <Producto> comparatorDescripcion = (Producto p1, Producto p2) -> p1.verDescripcion().compareTo(p2.verDescripcion());
        Collections.sort(productoPorCategoria,comparatorDescripcion);
        return productoPorCategoria;
    }

    @Override
    public Producto obtenerProducto(Integer codigo) {
        if (this.productos.isEmpty()) {
            return null;
        }

        for (Producto p : productos) {
            if (p.verCodigo() == codigo) {
                return p;
            }
        }

        return null;
    }

    @Override
    public String validarDatos(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        if (codigo < 0) {
            return ERROR_CODIGO;
        }

        if (descripcion == null || descripcion.isBlank()) {
            return ERROR_DESCRIPCION;
        }

        if (precio <= 0) {
            return ERROR_PRECIO;
        }

        if (categoria == null) {
            return ERROR_CATEGORIA;
        }

        if (estado == null) {
            return ERROR_ESTADO;
        }

        return VALIDACION_EXITO;
    }

}
