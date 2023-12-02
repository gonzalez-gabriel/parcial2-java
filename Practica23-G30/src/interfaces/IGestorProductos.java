/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.ArrayList;
import java.util.List;
import productos.modelos.*;

/**
 *
 * @author gabri
 */
public interface IGestorProductos {
    public static final String EXITO_CREADO = "Producto creado exitosamente";
    public static final String EXITO_MODIFICADO = "Producto modificado exitosamente";
    public static final String EXITO_BORRADO = "Producto borrado exitosamente";
    public static final String ERROR_CODIGO = "El código del producto es incorrecto";
    public static final String ERROR_DESCRIPCION = "La descripción del producto es incorrecta";
    public static final String ERROR_PRECIO = "El precio del producto es incorrecto";
    public static final String ERROR_CATEGORIA = "La categoría del producto es incorrecta";
    public static final String ERROR_ESTADO = "El estado es incorrecto";
    public static final String ERROR_BORRADO = "Este producto no puede borrarse porque esta en un pedido";
    public static final String PRODUCTO_INEXISTENTE = "No existe el producto especificado";
    public static final String PRODUCTOS_DUPLICADOS = "Ya existe un producto con ese código";
    public static final String VALIDACION_EXITO = "Los datos del producto son correctos";
    
    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado);
    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado);   
    public List<Producto> menu();
    public List<Producto> buscarProductos(String descripcion);
    public String borrarProducto(Producto producto);
    public boolean existeEsteProducto(Producto producto);
    public List<Producto> verProductosPorCategoria(Categoria categoria);
    public Producto obtenerProducto(Integer codigo);
    public String validarDatos (int codigo, String descripcion, float precio, Categoria categoria, Estado estado);
}
