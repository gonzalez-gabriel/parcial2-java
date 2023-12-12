/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import interfaces.IControladorPrincipal;
import interfaces.IControladorUsuarios;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import principal.vistas.VentanaPrincipal;
import usuarios.controladores.ControladorUsuarios;

/**
 *
 * @author gabri
 */
public class ControladorPrincipal implements IControladorPrincipal {

    private VentanaPrincipal ventana;

    public ControladorPrincipal() {
        this.ventana = new VentanaPrincipal(this);
        this.ventana.setTitle(TITULO);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    public static void main(String[] args) {
        establecerLookAndFeel("Nimbus");
        IControladorPrincipal cp = new ControladorPrincipal();
    }

    @Override
    public void btnUsuariosClic(ActionEvent evt) {
        IControladorUsuarios cu = new ControladorUsuarios(ventana);
    }

    @Override
    public void btnProductosClic(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Aviso: esta funcionalidad aún no fue implementada", "Productos", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void btnPedidosClic(ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Aviso: esta funcionalidad aún no fue implementada", "Pedidos", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void btnSalirClic(ActionEvent evt) {
        int eleccion = JOptionPane.showOptionDialog(
                this.ventana,
                "¿Desea finalizar la sesión?",
                TITULO, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Si", "No"},
                this
        );

        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void establecerLookAndFeel(String laf) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (laf.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e2) {
            }
        }
    }
}
