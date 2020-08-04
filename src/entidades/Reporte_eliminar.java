/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Municipio de Gye
 */
public class Reporte_eliminar {

    private String descripcion,observaciones,motivo;
    private int NroReporte;
    private Cliente_eliminar cliente;
    private Empleado empleado;
    private Servicio servicio;
    private Date fecha;
    private Time HoraInicio,HoraFinal;

    public Reporte_eliminar(String descripcion, String observaciones, int NroReporte, Cliente_eliminar cliente, Empleado empleado, Servicio servicio, Date fecha,Time HoraInicio,Time HoraFinal,String motivo) {
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.NroReporte = NroReporte;
        this.cliente = cliente;
        this.empleado = empleado;
        this.servicio = servicio;
        this.fecha = fecha;
        this.HoraInicio = HoraInicio;
        this.HoraFinal = HoraFinal;
        this.motivo=motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
    public Time getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(Time HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public Time getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(Time HoraFinal) {
        this.HoraFinal = HoraFinal;
    }

    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public int getNroReporte() {
        return NroReporte;
    }

    public void setNroReporte(int NroReporte) {
        this.NroReporte = NroReporte;
    }

    public Cliente_eliminar getCliente() {
        return cliente;
    }

    public void setCliente(Cliente_eliminar cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

  
}
