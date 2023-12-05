/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package productos.modelos;

import interfaces.IGestorProductos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
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

    // Comparadores
    Comparator<Producto> comparatorCategoriaYDescripcion = (Producto p1, Producto p2) -> {
        if (!p1.verCategoria().equals(p2.verCategoria())) {
            return p1.verCategoria().compareTo(p2.verCategoria());
        } else {
            return p1.verDescripcion().compareTo(p2.verDescripcion());
        }
    };
    Comparator<Producto> comparatorDescripcion = (Producto p1, Producto p2) -> p1.verDescripcion().compareTo(p2.verDescripcion());

    // Constante para archivos
    public static final String NOMBRE_ARCHIVO = "./Productos.txt";

    private GestorProductos() {
        leerArchivo();
    }

    public static GestorProductos instanciar() {
        if (gestor == null) {
            gestor = new GestorProductos();
        }

        return gestor;
    }

    private String leerArchivo() {
        File file = this.obtenerArchivo();
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String cadena;
                while ((cadena = br.readLine()) != null) {
                    String[] vector = cadena.split(";");
                    int codigo = parseInt(vector[0]);
                    String descripcion = vector[1];
                    float precio = parseFloat(vector[2]);
                    Categoria categoria = Categoria.fromString(vector[3]);
                    Estado estado = Estado.fromString(vector[4]);
                    Producto producto = new Producto(codigo, descripcion, categoria, estado, precio);
                    this.productos.add(producto);
                }
                br.close();

                return ARCHIVO_EXITO_LECTURA;
            } catch (NullPointerException | IOException ioe) {
                return ARCHIVO_ERROR_LECTURA;
            }
        } else {
            return ARCHIVO_ERROR_LECTURA;
        }
    }

    private String guardarEnArchivo() {
        File file = this.obtenerArchivo();
        if (file != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < this.productos.size(); i++) {
                    Producto producto = this.productos.get(i);
                    String cadena = producto.verCodigo() + ";";
                    cadena += producto.verDescripcion() + ";";
                    cadena += producto.verPrecio() + ";";
                    cadena += producto.verCategoria() + ";";
                    cadena += producto.verEstado() + ";";
                    bw.write(cadena);
                    if (i < this.productos.size() - 1) {
                        bw.newLine();
                    }
                }

                return ARCHIVO_EXITO_GUARDAR;
            } catch (IOException e) {
                return ARCHIVO_ERROR_GUARDAR;
            }
        } else {
            return ARCHIVO_ERROR_GUARDAR;
        }
    }

    private File obtenerArchivo() {
        File file = new File(NOMBRE_ARCHIVO);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            return file;
        } catch (IOException e) {
            return null;
        }
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
                String msj = this.guardarEnArchivo();
                if (!msj.equals(ARCHIVO_EXITO_GUARDAR)) {
                    return msj;
                }
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

        if (!validez.equals(VALIDACION_EXITO)) {
            return validez;
        }

        int i = this.productos.indexOf(productoAModificar);
//        productoAModificar.asignarCodigo(codigo);
        productoAModificar.asignarDescripcion(descripcion);
        productoAModificar.asignarPrecio(precio);
        productoAModificar.asignarCategoria(categoria);
        productoAModificar.asignarEstado(estado);
        this.productos.set(i, productoAModificar);

        String msj = this.guardarEnArchivo();
        if (!msj.equals(ARCHIVO_EXITO_GUARDAR)) {
            return msj;
        }

        return EXITO_MODIFICADO;

    }

    @Override
    public List<Producto> menu() {
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

        String msj = this.guardarEnArchivo();
        if (!msj.equals(ARCHIVO_EXITO_GUARDAR)) {
            return msj;
        }

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

        Collections.sort(productoPorCategoria, comparatorDescripcion);
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
        if (codigo <= 0) {
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
