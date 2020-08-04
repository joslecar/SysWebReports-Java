/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.sql.Time;
import java.util.Calendar;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Jose Leonardo
 */
public class ReporteDetalle {
    Rubro rubro;
    String descripcion;
    String tiempo;
    
    public ReporteDetalle(Rubro rubro, String descripcion, String tiempo){
        this.descripcion = descripcion;
        this.rubro = rubro;
        this.tiempo = tiempo;
    }

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.rubro);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReporteDetalle other = (ReporteDetalle) obj;
        return this.rubro.equals(other.rubro);
    }

    @Override
    public String toString() {
        return "ReporteDetalle{" + "rubro=" + rubro + '}';
    }
    
    
}
