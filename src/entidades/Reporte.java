/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Jose Leonardo
 */
public class Reporte {
    private String tipo;
    private String numero_rs;
    private Cliente empresa_rs;
    private Vendedor cod_asesor;
    private Date fecha_rs;
    private Date fechaIngreso;
    private String solucion;
    private boolean facturar;
    private String ordenTrabajo;
    private boolean solucionado;
    private boolean contrato;
    private String dpto;
    private String solicitadoPor;
    private String hora_ini;
    private String hora_fin;
    private List<ReporteDetalle> detalle;
   // private String descripcion;
    private String observaciones;
    
    public Reporte(String tipo, String numero_rs, Cliente empresa_rs, Vendedor cod_asesor,Date fecha_rs,Date fechaIngreso,
            String solucion, boolean facturar, String ordenTrabajo, boolean solucionado, boolean contrato, String dpto,String solicitadoPor,
            String hora_ini, String hora_fin,String observaciones){
        this.tipo = tipo;
        this.cod_asesor = cod_asesor;
        this.numero_rs = numero_rs;
        this.empresa_rs = empresa_rs;
        this.fecha_rs = fecha_rs;
        this.facturar = facturar;
        this.ordenTrabajo = ordenTrabajo;
        this.solucionado = solucionado;
        this.contrato = contrato;
        this.dpto = dpto;
        this.solicitadoPor = solicitadoPor;
        this.hora_ini = hora_ini;
        this.hora_fin = hora_fin;
        this.fechaIngreso = fechaIngreso;
        this.detalle = new ArrayList<>();
        //this.rubro_rs = rubro_rs;
        //this.descripcion = descripcion;
        this.solucion = solucion;
        //this.motivo = motivo;
        this.observaciones = observaciones;
    }
    public Reporte(){
        this.detalle = new ArrayList();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Vendedor getCod_asesor() {
        return cod_asesor;
    }

    public void setCod_asesor(Vendedor cod_asesor) {
        this.cod_asesor = cod_asesor;
    }

    public String getNumero_rs() {
        return numero_rs;
    }

    public void setNumero_rs(String numero_rs) {
        this.numero_rs = numero_rs;
    }

    public Cliente getEmpresa_rs() {
        return empresa_rs;
    }

    public void setEmpresa_rs(Cliente empresa_rs) {
        this.empresa_rs = empresa_rs;
    }

    public Date getFecha_rs() {
        return fecha_rs;
    }

    public void setFecha_rs(Date fecha_rs) {
        this.fecha_rs = fecha_rs;
    }

    public String getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(String hora_ini) {
        this.hora_ini = hora_ini;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public List<ReporteDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<ReporteDetalle> detalle) {
        this.detalle = detalle;
    }
    
    public void aggDetalle(ReporteDetalle detalle){
        this.detalle.add(detalle);
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /*public List<Rubro> getRubro_rs() {
        return rubro_rs;
    }

    public void setRubro_rs(List<Rubro> rubro_rs) {
        this.rubro_rs = rubro_rs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }*/

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isFacturar() {
        return facturar;
    }

    public void setFacturar(boolean facturar) {
        this.facturar = facturar;
    }

    public String getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(String ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public boolean isSolucionado() {
        return solucionado;
    }

    public void setSolucionado(boolean solucionado) {
        this.solucionado = solucionado;
    }

    public boolean isContrato() {
        return contrato;
    }

    public void setContrato(boolean contrato) {
        this.contrato = contrato;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getSolicitadoPor() {
        return solicitadoPor;
    }

    public void setSolicitadoPor(String solicitadoPor) {
        this.solicitadoPor = solicitadoPor;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    @Override
    public String toString() {
        return "Reporte{" + "tipo=" + tipo + ", numero_rs=" + numero_rs + '}';
    }
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || !(o instanceof Reporte))
            return false; 
        Reporte r = (Reporte)o;
        return r.numero_rs.equals(this.numero_rs);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.numero_rs);
        return hash;
    }
    
    
}
