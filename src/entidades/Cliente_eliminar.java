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
public class Cliente_eliminar {
    
    private int id;
    private String ruc_cedula,nombre,responsable,Ubicacion;

    public Cliente_eliminar(int id, String ruc_cedula, String nombre, String responsable, String Ubicacion) {
        this.id = id;
        this.ruc_cedula = ruc_cedula;
        this.nombre = nombre;
        this.responsable = responsable;
        this.Ubicacion = Ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuc_cedula() {
        return ruc_cedula;
    }

    public void setRuc_cedula(String ruc_cedula) {
        this.ruc_cedula = ruc_cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }
    
    
    
}
