/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pedidos.modelos.Pedido;

/**
 *
 * @author gabri
 */
public abstract class Usuario {

    private String correo;
    private String clave;
    private String apellido;
    private String nombre;
    private Perfil perfil;

    public Usuario(String correo, String clave, String apellido, String nombre, Perfil perfil) {
        this.correo = correo;
        this.clave = clave;
        this.apellido = apellido;
        this.nombre = nombre;
        this.perfil = perfil;
    }

    public Perfil verPerfil() {
        return perfil;
    }
    
    public void asignarPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String verCorreo() {
        return correo;
    }

    public void asignarCorreo(String correo) {
        this.correo = correo;
    }

    public String verClave() {
        return clave;
    }

    public void asignarClave(String clave) {
        this.clave = clave;
    }

    public String verApellido() {
        return apellido;
    }

    public void asignarApellido(String apellido) {
        this.apellido = apellido;
    }

    public String verNombre() {
        return nombre;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract List<Pedido> verPedidos();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass().getSuperclass() != getClass().getSuperclass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.correo.toLowerCase(), other.correo.toLowerCase());
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre + " Apellido: " + apellido + " Correo: " + correo + " Perfil: " + perfil;
    }

    public void mostrar() {
        System.out.println(this.apellido + ", " + this.nombre);
        System.out.println("Correo:" + this.correo);
        System.out.println("Perfil: " + this.perfil);
    }

}
