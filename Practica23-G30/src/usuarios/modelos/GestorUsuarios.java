/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public static final String NOMBRE_ARCHIVO = "./Usuarios.txt";

    private GestorUsuarios() {
        leerArchivo();
    }

    public static GestorUsuarios instanciar() {
        if (gestor == null) {
            gestor = new GestorUsuarios();
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
                    String correo = vector[0];
                    String apellido = vector[1];
                    String nombre = vector[2];
                    Perfil perfil = Perfil.fromString(vector[3]);
                    String clave = vector[4];
                    Usuario usuario = null;
                    if (perfil == Perfil.CLIENTE) {
                        usuario = new Cliente(correo, clave, apellido, nombre, perfil);
                    }
                    if (perfil == Perfil.EMPLEADO) {
                        usuario = new Empleado(correo, clave, apellido, nombre, perfil);
                    }
                    if (perfil == Perfil.ENCARGADO) {
                        usuario = new Encargado(correo, clave, apellido, nombre, perfil);
                    }
                    
                    this.usuarios.add(usuario);
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
    
    private String guardarEnArchivo() {
        BufferedWriter bw = null;
        File file = this.obtenerArchivo();
        if(file != null) {
            try {
                bw = new BufferedWriter(new FileWriter(file));
                for(int i = 0; i < this.usuarios.size(); i++) {
                    Usuario usuario = this.usuarios.get(i);
                    String cadena = usuario.verCorreo() + ";";
                    cadena += usuario.verApellido() + ";";
                    cadena += usuario.verNombre() + ";";
                    cadena += usuario.verPerfil() + ";";
                    cadena += usuario.verClave()+ ";";
                    bw.write(cadena);
                    if(i < this.usuarios.size() - 1) {
                        bw.newLine();
                    }
                }
                return ARCHIVO_EXITO_GUARDAR;
            } catch(IOException e) {
                return ARCHIVO_ERROR_GUARDAR;
            } finally {
                if (bw != null){
                    try {
                        bw.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        } else {
            return ARCHIVO_ERROR_GUARDAR;
        }
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
        String msj = this.guardarEnArchivo();
        if(!msj.equals(ARCHIVO_EXITO_GUARDAR)){
            return msj;
        }
        
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

        String msj = this.guardarEnArchivo();
        if(!msj.equals(ARCHIVO_EXITO_GUARDAR)){
            return msj;
        }
        
        return EXITO_MODIFICADO;

    }

    @Override
    public String borrarUsuario(Usuario usuario) {
        if ((usuario.verPerfil().equals(Perfil.CLIENTE)) && (!usuario.verPedidos().isEmpty())) {
            return ERROR_PERMISOS;
        }
        this.usuarios.remove(usuario);
        
        String msj = this.guardarEnArchivo();
        if(!msj.equals(ARCHIVO_EXITO_GUARDAR)){
            return msj;
        }
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
