/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package usuarios.modelos;

/**
 *
 * @author gabri
 */
public enum Perfil {
    CLIENTE("CLIENTE"),
    EMPLEADO("EMPLEADO"),
    ENCARGADO("ENCARGADO");

    private String perfil;

    private Perfil(String otraCadena) {
        this.perfil = otraCadena;
    }

    @Override
    public String toString() {
        return perfil;
    }
}
