/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Municipio de Gye
 */
public class Departamento {
    
    String CodDepartamento,tipo,ubicacion;

    public Departamento(String CodDepartamento, String tipo, String ubicacion) {
        this.CodDepartamento = CodDepartamento;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
    }

    public String getCodDepartamento() {
        return CodDepartamento;
    }

    public void setCodDepartamento(String CodDepartamento) {
        this.CodDepartamento = CodDepartamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Departamento{" + "CodDepartamento=" + CodDepartamento + ", tipo=" + tipo + ", ubicacion=" + ubicacion + '}';
    }
    
    
}
