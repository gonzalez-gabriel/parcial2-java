/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author gabri
 */
public class GestorUsuarios implements IGestorUsuarios {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private static GestorUsuarios gestor;

    private GestorUsuarios() {
    }

    public static GestorUsuarios instanciar() {
        if (gestor == null) {
            gestor = new GestorUsuarios();
        }

        return gestor;
    }

    @Override
    public String crearUsuario(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        String validez = validarDatos(correo, apellido, nombre, clave, claveRepetida, perfil);
        if (!validez.equals(VALIDACION_EXITO)) {
            return validez;
        }

        Usuario u = null;
        if (perfil == Perfil.CLIENTE) {
            u = new Cliente(correo, clave, apellido, nombre, perfil);
        }
        if (perfil == Perfil.EMPLEADO) {
            u = new Empleado(correo, clave, apellido, nombre, perfil);
        }
        if (perfil == Perfil.ENCARGADO) {
            u = new Encargado(correo, clave, apellido, nombre, perfil);
        }

        if (this.usuarios.contains(u)) {
            return USUARIOS_DUPLICADOS;
        }

        this.usuarios.add(u);
        return EXITO_CREADO;

    }

    @Override
    public String modificarUsuario(Usuario usuarioAModificar, String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        if (this.usuarios.isEmpty() || !this.usuarios.contains(usuarioAModificar)) {
            return USUARIO_INEXISTENTE;
        }
        String validez = validarDatos(correo, apellido, nombre, clave, claveRepetida, perfil);
        
        if (!validez.equals(VALIDACION_EXITO)) {
            return validez;
        }
        
        int i = this.usuarios.indexOf(usuarioAModificar);
        usuarioAModificar.asignarApellido(apellido);
        usuarioAModificar.asignarNombre(nombre);
        usuarioAModificar.asignarPerfil(perfil);
        usuarioAModificar.asignarClave(clave);
        this.usuarios.set(i, usuarioAModificar);
        
        return EXITO_MODIFICADO;

    }

    @Override
    public String borrarUsuario(Usuario usuario) {
        if ((usuario.verPerfil().equals(Perfil.CLIENTE)) && (!usuario.verPedidos().isEmpty())) {
            return ERROR_PERMISOS;
        }
        this.usuarios.remove(usuario);
        return EXITO_BORRADO;
    }

    @Override
    public List<Usuario> verUsuarios() {
        Comparator<Usuario> comparatorApellidoYNombre = (Usuario u1, Usuario u2) -> {
            if (!u1.verApellido().equals(u2.verApellido())) {
                return u1.verApellido().compareTo(u2.verApellido());
            } else {

                return u1.verNombre().compareTo(u2.verNombre());
            }
        };
        Collections.sort(usuarios, comparatorApellidoYNombre);
        return this.usuarios;
    }

    @Override
    public List<Usuario> buscarUsuarios(String apellido) {
        if (apellido.isBlank() || apellido.isEmpty()) {
            return null;
        }

        ArrayList<Usuario> usuariosBuscados = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.verApellido().equals(apellido) || u.verApellido().startsWith(apellido)) {
                usuariosBuscados.add(u);
            }
        }

        Comparator<Usuario> comparatorApellidoYNombre = (Usuario u1, Usuario u2) -> {
            if (!u1.verApellido().equals(u2.verApellido())) {
                return u1.verApellido().compareTo(u2.verApellido());
            } else {

                return u1.verNombre().compareTo(u2.verNombre());
            }

        };

        Collections.sort(usuariosBuscados, comparatorApellidoYNombre);
        return usuariosBuscados;
    }

    @Override
    public boolean existeEsteUsuario(Usuario usuario) {
        return this.usuarios.contains(usuario);
    }

    @Override
    public Usuario obtenerUsuario(String correo) {
        if (this.usuarios.isEmpty()) {
            return null;
        }

        for (Usuario u : usuarios) {
            if (u.verCorreo().equals(correo)) {
                return u;
            }
        }

        return null;
    }

    @Override
    public String validarDatos(String correo, String apellido, String nombre, String clave, String claveRepetida, Perfil perfil) {
        if (correo == null || !correo.contains("@")) {
            return ERROR_CORREO;
        }

        if (apellido == null || apellido.isBlank()) {
            return ERROR_APELLIDO;
        }

        if (nombre == null || nombre.isBlank()) {
            return ERROR_NOMBRE;
        }

        if (clave == null || clave.isBlank()) {
            return ERROR_CLAVES;
        }

        if (claveRepetida == null || claveRepetida.isBlank() || !claveRepetida.equals(clave)) {
            return ERROR_CLAVES;
        }

        if (perfil == null) {
            return ERROR_PERFIL;
        }

        return VALIDACION_EXITO;
    }

}
