/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.controladores;

import interfaces.IControladorAMUsuario;
import interfaces.IGestorUsuarios;
import static interfaces.IGestorUsuarios.EXITO_CREADO;
import static interfaces.IGestorUsuarios.EXITO_MODIFICADO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.ModeloComboPerfiles;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;
import usuarios.vistas.VentanaAMUsuario;
import usuarios.vistas.VentanaUsuarios;

/**
 *
 * @author gabri
 */
public class ControladorAMUsuario implements IControladorAMUsuario {

    private VentanaAMUsuario ventana;
    private IGestorUsuarios gu = GestorUsuarios.instanciar();

    public ControladorAMUsuario(VentanaUsuarios ventanaPadre) {
        this.ventana = new VentanaAMUsuario(this, ventanaPadre, true);
        this.ventana.setTitle(TITULO_NUEVO);
        this.ventana.verTxtCorreo().requestFocus();
        this.ventana.verPerfil().setModel(new ModeloComboPerfiles());
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    public ControladorAMUsuario(VentanaUsuarios ventanaPadre, Usuario usuario) {
        this.ventana = new VentanaAMUsuario(this, ventanaPadre, true);
        this.ventana.setTitle(TITULO_MODIFICAR);
        String correo = usuario.verCorreo();
        String apellido = usuario.verApellido();
        String nombre = usuario.verNombre();
        Perfil perfil = usuario.verPerfil();
        String clave = usuario.verClave();
        this.ventana.verTxtCorreo().setText(correo);
        this.ventana.verTxtApellido().setText(apellido);
        this.ventana.verTxtNombre().setText(nombre);
        this.ventana.verPerfil().setModel(new ModeloComboPerfiles(perfil));
        this.ventana.verPerfil().setEnabled(false);
        this.ventana.verPassClave().setText(clave);
        this.ventana.verPassClaveRepetida().setText(clave);
        this.ventana.verTxtCorreo().setEnabled(false);
        this.ventana.verTxtApellido().requestFocus();
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    public void guardar() {
        String correo = this.ventana.verTxtCorreo().getText().trim();
        String apellido = this.ventana.verTxtApellido().getText().trim();
        String nombre = this.ventana.verTxtNombre().getText().trim();
        String clave = new String(this.ventana.verPassClave().getPassword());
        String claveRepetida = new String(this.ventana.verPassClaveRepetida().getPassword());
        Perfil perfil = ((ModeloComboPerfiles) this.ventana.verPerfil().getModel()).obtenerPerfil();

        String resultado;
        if (this.ventana.verTxtCorreo().isEnabled()) {
            resultado = gu.crearUsuario(correo, apellido, nombre, perfil, clave, claveRepetida);
        } else {
            resultado = gu.modificarUsuario(gu.obtenerUsuario(correo), correo, apellido, nombre, perfil, clave, claveRepetida);
        }

        if (resultado == EXITO_CREADO || resultado == EXITO_MODIFICADO) {
            JOptionPane.showMessageDialog(null, resultado, "Usuario Guardado", JOptionPane.INFORMATION_MESSAGE);
            this.ventana.setVisible(false);
            this.ventana.dispose();
        } else {
            JOptionPane.showMessageDialog(this.ventana, resultado, "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void btnGuardarClic(ActionEvent evt) {
        this.guardar();
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {

        // Agregar msj de confirmación
        this.ventana.setVisible(false);
        this.ventana.dispose();
    }

    @Override
    public void txtApellidoPresionarTecla(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.guardar();
        }
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.guardar();
        }
    }

    @Override
    public void txtCorreoPresionarTecla(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.guardar();
        }
    }

    @Override
    public void passClavePresionarTecla(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.guardar();
        }
    }

    @Override
    public void passClaveRepetidaPresionarTecla(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.guardar();
        }
    }

}
