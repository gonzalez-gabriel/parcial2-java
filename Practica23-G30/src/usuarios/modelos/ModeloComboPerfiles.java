/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author gabri
 */
public class ModeloComboPerfiles extends DefaultComboBoxModel {

    public ModeloComboPerfiles() {
        for (Perfil perfil : Perfil.values()) {
            this.addElement(perfil);
        }
    }

    public Perfil obtenerPerfil() {
        return (Perfil) this.getSelectedItem();
    }

    public void seleccionarPerfil(Perfil perfil) {
        this.setSelectedItem(perfil);
    }
}
