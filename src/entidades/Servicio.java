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
public class Servicio {

    private String id,nombre;
    private double valorHora,valorCantidad;
    

    public Servicio(String id, String nombre, double valorHora, double valorCantidad) {
        this.id = id;
        this.nombre = nombre;
        this.valorHora = valorHora;
        this.valorCantidad = valorCantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getValorCantidad() {
        return valorCantidad;
    }

    public void setValorCantidad(double valorCantidad) {
        this.valorCantidad = valorCantidad;
    }
    
    
    
}
