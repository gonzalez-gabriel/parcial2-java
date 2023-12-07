/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gabri
 */
public class ModeloTablaUsuarios extends AbstractTableModel{
    private IGestorUsuarios gu = GestorUsuarios.instanciar();
    private List<String> nombresColumnas = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    
    public ModeloTablaUsuarios() {
        this.nombresColumnas.add("Apellido");
        this.nombresColumnas.add("Nombre");
        this.nombresColumnas.add("Perfil");
        this.usuarios = gu.verUsuarios();
    }

    @Override
    public int getRowCount() {
        return this.usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombresColumnas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = this.usuarios.get(rowIndex);
        switch(columnIndex) {
            case 0: return usuario.verApellido();
            case 1: return usuario.verNombre();
            default: return usuario.verPerfil();
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.nombresColumnas.get(column);
    }
    
    public void actualizar() {
        this.usuarios = gu.verUsuarios();
        this.fireTableDataChanged();
    }
    
    public void buscarUsuario(String apellido) {
        this.usuarios = gu.buscarUsuarios(apellido);
        this.fireTableDataChanged();
    }
    
}
