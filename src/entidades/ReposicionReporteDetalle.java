/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Leonardo
 */
public class ReposicionReporteDetalle {
    private String tipo;
    private String numero;
    private int linea;
    private String tipo_t;
    private LocalDate fecha_tra;
    private String no_parte;  //Este es el codigo del producto
    private String consumo;
    private String bodega;
    private double cantidad;
    private double despacho; //0.00 por defecto en tabla?
    private double precio_u;
    private double precio_t;
    private double costo_u;
    private double costo_t;
    private double poriva; // IVA?
    private double valiva; // IVA?
    private double pordes;
    private double precio_ne;
    private double costfactor;
    private double totfactor;
    private String codfactor;
    private double cantfactor;
    private double porICE; //0.00 por defecto en tabla

    public ReposicionReporteDetalle(String tipo, String numero, int linea, String tipo_t, LocalDate fecha_tra, String no_parte, String consumo, String bodega, double cantidad, double despacho, double precio_u, double precio_t, double costo_u, double costo_t, double poriva, double valiva, double pordes, double precio_ne, double costfactor, double totfactor, String codfactor, double cantfactor, double porICE) {
        this.tipo = tipo;
        this.numero = numero;
        this.linea = linea;
        this.tipo_t = tipo_t;
        this.fecha_tra = fecha_tra;
        this.no_parte = no_parte;
        this.consumo = consumo;
        this.bodega = bodega;
        this.cantidad = cantidad;
        this.despacho = despacho;
        this.precio_u = precio_u;
        this.precio_t = precio_t;
        this.costo_u = costo_u;
        this.costo_t = costo_t;
        this.poriva = poriva;
        this.valiva = valiva;
        this.pordes = pordes;
        this.precio_ne = precio_ne;
        this.costfactor = costfactor;
        this.totfactor = totfactor;
        this.codfactor = codfactor;
        this.cantfactor = cantfactor;
        this.porICE = porICE;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getTipo_t() {
        return tipo_t;
    }

    public void setTipo_t(String tipo_t) {
        this.tipo_t = tipo_t;
    }

    public LocalDate getFecha_tra() {
        return fecha_tra;
    }

    public void setFecha_tra(LocalDate fecha_tra) {
        this.fecha_tra = fecha_tra;
    }

    public String getNo_parte() {
        return no_parte;
    }

    public void setNo_parte(String no_parte) {
        this.no_parte = no_parte;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getDespacho() {
        return despacho;
    }

    public void setDespacho(double despacho) {
        this.despacho = despacho;
    }

    public double getPrecio_u() {
        return precio_u;
    }

    public void setPrecio_u(double precio_u) {
        this.precio_u = precio_u;
    }

    public double getPrecio_t() {
        return precio_t;
    }

    public void setPrecio_t(double precio_t) {
        this.precio_t = precio_t;
    }

    public double getCosto_u() {
        return costo_u;
    }

    public void setCosto_u(double costo_u) {
        this.costo_u = costo_u;
    }

    public double getCosto_t() {
        return costo_t;
    }

    public void setCosto_t(double costo_t) {
        this.costo_t = costo_t;
    }

    public double getPoriva() {
        return poriva;
    }

    public void setPoriva(double poriva) {
        this.poriva = poriva;
    }

    public double getValiva() {
        return valiva;
    }

    public void setValiva(double valiva) {
        this.valiva = valiva;
    }

    public double getPordes() {
        return pordes;
    }

    public void setPordes(double pordes) {
        this.pordes = pordes;
    }

    public double getPrecio_ne() {
        return precio_ne;
    }

    public void setPrecio_ne(double precio_ne) {
        this.precio_ne = precio_ne;
    }

    public double getCostfactor() {
        return costfactor;
    }

    public void setCostfactor(double costfactor) {
        this.costfactor = costfactor;
    }

    public double getTotfactor() {
        return totfactor;
    }

    public void setTotfactor(double totfactor) {
        this.totfactor = totfactor;
    }

    public String getCodfactor() {
        return codfactor;
    }

    public void setCodfactor(String codfactor) {
        this.codfactor = codfactor;
    }

    public double getCantfactor() {
        return cantfactor;
    }

    public void setCantfactor(double cantfactor) {
        this.cantfactor = cantfactor;
    }

    public double getPorICE() {
        return porICE;
    }

    public void setPorICE(double porICE) {
        this.porICE = porICE;
    }

    @Override
    public String toString() {
        return "ReposicionReporteDetalle{" + "tipo=" + tipo + ", numero=" + numero + ", linea=" + linea + ", tipo_t=" + tipo_t + ", fecha_tra=" + fecha_tra + ", no_parte=" + no_parte + ", consumo=" + consumo + ", bodega=" + bodega + ", cantidad=" + cantidad + ", despacho=" + despacho + ", precio_u=" + precio_u + ", precio_t=" + precio_t + ", costo_u=" + costo_u + ", costo_t=" + costo_t + ", poriva=" + poriva + ", valiva=" + valiva + ", pordes=" + pordes + ", precio_ne=" + precio_ne + ", costfactor=" + costfactor + ", totfactor=" + totfactor + ", codfactor=" + codfactor + ", cantfactor=" + cantfactor + ", porICE=" + porICE + '}';
    }
    
    
}
