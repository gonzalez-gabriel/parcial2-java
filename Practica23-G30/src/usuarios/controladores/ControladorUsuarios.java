/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.controladores;

import interfaces.IControladorUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import principal.vistas.VentanaPrincipal;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.ModeloTablaUsuarios;
import usuarios.vistas.VentanaUsuarios;

/**
 *
 * @author gabri
 */
public class ControladorUsuarios implements IControladorUsuarios{
    
    private VentanaUsuarios ventana;
    private GestorUsuarios gu = GestorUsuarios.instanciar();
    private ModeloTablaUsuarios mtu = new ModeloTablaUsuarios();
    
    public ControladorUsuarios(VentanaPrincipal ventanaPadre) {
        this.ventana = new VentanaUsuarios(this, ventanaPadre, true);
        this.ventana.setTitle(TITULO);
        this.ventana.verTablaUsuarios().setModel(mtu);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    private void limpiarCampos() {
        this.ventana.verTxtApellido().setText(null);
        this.ventana.verTxtApellido().setRequestFocusEnabled(true);
        this.ventana.verBtnModificar().setEnabled(false);
        this.ventana.verBtnBorrar().setEnabled(false);
    }

    @Override
    public void btnNuevoClic(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void btnModificarClic(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void btnBorrarClic(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        this.limpiarCampos();
        mtu.actualizar();
        if(mtu.getRowCount() > 0) {
            this.ventana.verTablaUsuarios().setRowSelectionInterval(0, 0);
            this.ventana.verBtnBorrar().setEnabled(true);
            this.ventana.verBtnModificar().setEnabled(true);
        } else {
            this.ventana.verBtnBorrar().setEnabled(false);
            this.ventana.verBtnModificar().setEnabled(false);
        }
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.setVisible(false);
        this.ventana.dispose();
    }

    @Override
    public void txtApellidoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if(c == KeyEvent.VK_ENTER) {
            this.btnBuscarClic(null);
        }
    }

    @Override
    public void btnBuscarClic(ActionEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
