/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Leonardo
 */
public class ReposicionReporte {
    private String tipo;
    private String numero;
    private String numero_b; //vacio
    private String referencia; //vacio
    private String grupo;
    private String tipo_t;
    private LocalDate fecha_tra;
    private LocalDate fecha_ven;
    private String bodega;
    private String prov_cli;
    private double total_mov;
    private double pordes;
    private double total_des;
    private double poriva;
    private double total_iva;
    private double total_trn;
    private LocalDate userfec;
    private String usercla;
    private String comenta;
    private boolean anulada;
    private double abono;
    private String integracnt;
    private boolean canc_lote;
    private double base_0;
    private double base_tax;
    private String s_impcadu;
    private String s_autoriza; //vacio
    private double total_ice; //0.00 por defecto
    private List<ReposicionReporteDetalle> reposicionDetalles;

    public ReposicionReporte(String tipo, String numero, String numero_b, String referencia, String grupo, String tipo_t, LocalDate fecha_tra, LocalDate fecha_ven, String bodega, String prov_cli, double total_mov, double pordes, double total_des, double poriva, double total_iva, double total_trn, LocalDate userfec, String usercla, String comenta, boolean anulada, double abono, String integracnt, boolean canc_lote, double base_0, double base_tax, String s_impcadu, String s_autoriza, double total_ice) {
        this.tipo = tipo;
        this.numero = numero;
        this.numero_b = numero_b;
        this.referencia = referencia;
        this.grupo = grupo;
        this.tipo_t = tipo_t;
        this.fecha_tra = fecha_tra;
        this.fecha_ven = fecha_ven;
        this.bodega = bodega;
        this.prov_cli = prov_cli;
        this.total_mov = total_mov;
        this.pordes = pordes;
        this.total_des = total_des;
        this.poriva = poriva;
        this.total_iva = total_iva;
        this.total_trn = total_trn;
        this.userfec = userfec;
        this.usercla = usercla;
        this.comenta = comenta;
        this.anulada = anulada;
        this.abono = abono;
        this.integracnt = integracnt;
        this.canc_lote = canc_lote;
        this.base_0 = base_0;
        this.base_tax = base_tax;
        this.s_impcadu = s_impcadu;
        this.s_autoriza = s_autoriza;
        this.total_ice = total_ice;
        this.reposicionDetalles = new ArrayList();
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

    public String getNumero_b() {
        return numero_b;
    }

    public void setNumero_b(String numero_b) {
        this.numero_b = numero_b;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo_t() {
        return tipo_t;
    }

    public void setTipo_t(String tipo_t) {
        this.tipo_t = tipo_t;
    }

    public LocalDate getFecha_t() {
        return fecha_tra;
    }

    public void setFecha_t(LocalDate fecha_tra) {
        this.fecha_tra = fecha_tra;
    }

    public LocalDate getFecha_ven() {
        return fecha_ven;
    }

    public void setFecha_ven(LocalDate fecha_ven) {
        this.fecha_ven = fecha_ven;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public String getProv_cli() {
        return prov_cli;
    }

    public void setProv_cli(String prov_cli) {
        this.prov_cli = prov_cli;
    }

    public double getTotal_mov() {
        return total_mov;
    }

    public void setTotal_mov(double total_mov) {
        this.total_mov = total_mov;
    }

    public double getPordes() {
        return pordes;
    }

    public void setPordes(double pordes) {
        this.pordes = pordes;
    }

    public double getTotal_des() {
        return total_des;
    }

    public void setTotal_des(double total_des) {
        this.total_des = total_des;
    }

    public double getPoriva() {
        return poriva;
    }

    public void setPoriva(double poriva) {
        this.poriva = poriva;
    }

    public double getTotal_iva() {
        return total_iva;
    }

    public void setTotal_iva(double total_iva) {
        this.total_iva = total_iva;
    }

    public double getTotal_trn() {
        return total_trn;
    }

    public void setTotal_trn(double total_trn) {
        this.total_trn = total_trn;
    }

    public LocalDate getUserfec() {
        return userfec;
    }

    public void setUserfec(LocalDate userfec) {
        this.userfec = userfec;
    }

    public String getUsercla() {
        return usercla;
    }

    public void setUsercla(String usercla) {
        this.usercla = usercla;
    }

    public String getComenta() {
        return comenta;
    }

    public void setComenta(String comenta) {
        this.comenta = comenta;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public void setAnulada(boolean anulada) {
        this.anulada = anulada;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getIntegracnt() {
        return integracnt;
    }

    public void setIntegracnt(String integracnt) {
        this.integracnt = integracnt;
    }

    public boolean isCanc_lote() {
        return canc_lote;
    }

    public void setCanc_lote(boolean canc_lote) {
        this.canc_lote = canc_lote;
    }

    public double getBase_0() {
        return base_0;
    }

    public void setBase_0(double base_0) {
        this.base_0 = base_0;
    }

    public double getBase_tax() {
        return base_tax;
    }

    public void setBase_tax(double base_tax) {
        this.base_tax = base_tax;
    }

    public String getS_impcadu() {
        return s_impcadu;
    }

    public void setS_impcadu(String s_impcadu) {
        this.s_impcadu = s_impcadu;
    }

    public String getS_autoriza() {
        return s_autoriza;
    }

    public void setS_autoriza(String s_autoriza) {
        this.s_autoriza = s_autoriza;
    }

    public double getTotal_ice() {
        return total_ice;
    }

    public void setTotal_ice(double total_ice) {
        this.total_ice = total_ice;
    }
    
    public void aggRepoDetalle(ReposicionReporteDetalle reposicicionDetalle){
        this.reposicionDetalles.add(reposicicionDetalle);
    }

    public LocalDate getFecha_tra() {
        return fecha_tra;
    }

    public void setFecha_tra(LocalDate fecha_tra) {
        this.fecha_tra = fecha_tra;
    }

    public List<ReposicionReporteDetalle> getReposicionDetalles() {
        return reposicionDetalles;
    }

    public void setReposicionDetalles(List<ReposicionReporteDetalle> reposicionDetalles) {
        this.reposicionDetalles = reposicionDetalles;
    }
    
   

    @Override
    public String toString() {
        String detalle = "";
        for(ReposicionReporteDetalle rp :reposicionDetalles){
            detalle += rp.toString()+"\n";
        }
        return "ReposicionReporte{" + "tipo=" + tipo + ", numero=" + numero + ", numero_b=" + numero_b + ", referencia=" + referencia + ", grupo=" + grupo + ", tipo_t=" + tipo_t + ", fecha_tra=" + fecha_tra + ", fecha_ven=" + fecha_ven + ", bodega=" + bodega + ", prov_cli=" + prov_cli + ", total_mov=" + total_mov + ", pordes=" + pordes + ", total_des=" + total_des + ", poriva=" + poriva + ", total_iva=" + total_iva + ", total_trn=" + total_trn + ", userfec=" + userfec + ", usercla=" + usercla + ", comenta=" + comenta + ", anulada=" + anulada + ", abono=" + abono + ", integracnt=" + integracnt + ", canc_lote=" + canc_lote + ", base_0=" + base_0 + ", base_tax=" + base_tax + ", s_impcadu=" + s_impcadu + ", s_autoriza=" + s_autoriza + ", total_ice=" + total_ice + ", reposicionDetalles=" + detalle + '}';
    }

            
    
}
