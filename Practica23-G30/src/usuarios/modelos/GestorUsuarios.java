/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class GestorUsuarios implements IGestorUsuarios {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private static GestorUsuarios gestor;

    private GestorUsuarios() {
    }

    public static GestorUsuarios crear() {
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
    public String borrarUsuario(Usuario usuario) {
        if ((usuario.verPerfil().equals(Perfil.CLIENTE)) && (!usuario.verPedidos().isEmpty())) {
            return ERROR_PERMISOS;
        }
        this.usuarios.remove(usuario);
        return EXITO_BORRADO;
    }

    @Override
    public ArrayList<Usuario> verUsuarios() {
        return this.usuarios;
    }

    @Override
    public ArrayList<Usuario> buscarUsuarios(String apellido) {
        if (apellido.isBlank() || apellido.isEmpty()) {
            return null;
        }

        ArrayList<Usuario> usuariosBuscados = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.verApellido().equals(apellido) || u.verApellido().startsWith(apellido)) {
                usuariosBuscados.add(u);
            }
        }

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
