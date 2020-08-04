/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jose Leonardo
 */
public class Vendedor {
    private String master;
    private String codigo;
    private String nombre;
    private float valor;
    private String nomtag;
    private String gestion;
    private boolean pideval;
    private String campo1;
    private String sgrupo; //codigo que une a las otras tablas
    private String campo2;
    private float lencod;
    private int valor2;
    private String rutaImagen;
    private String cedula;
    private String tipoCod; // este campo no esta en la base de datos principal, solo la uso para el registro
    
    public Vendedor(String master,String codigo,String nombre,float valor,String nomtag,String gestion,
            boolean pideval,String campo1,String sgrupo,String campo2,float lencod,int valor2,String tipoCod){
        this.master = master;
        this.codigo = codigo;
        this.nombre = nombre;
        this.valor = valor;
        this.nomtag = nomtag;
        this.gestion = gestion;
        this.pideval = pideval;
        this.campo1 = campo1;
        this.sgrupo = sgrupo;
        this.campo2 = campo2;
        this.lencod = lencod;
        this.valor2 = valor2;
        this.tipoCod = tipoCod;
        if(cedula != null)
            this.rutaImagen = "recursos/"+cedula+".png";
        else
            this.rutaImagen = "recursos/0927827501.png";
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNomtag() {
        return nomtag;
    }

    public void setNomtag(String nomtag) {
        this.nomtag = nomtag;
    }

    public String getGestion() {
        return gestion;
    }

    public void setGestion(String gestion) {
        this.gestion = gestion;
    }

    public boolean isPideval() {
        return pideval;
    }

    public void setPideval(boolean pideval) {
        this.pideval = pideval;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getSgrupo() {
        return sgrupo;
    }

    public void setSgrupo(String sgrupo) {
        this.sgrupo = sgrupo;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public float getLencod() {
        return lencod;
    }

    public void setLencod(float lencod) {
        this.lencod = lencod;
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
    
    public ImageView getImagen(){
        Image imagen=new Image(rutaImagen,150,150,true,true);
        ImageView imgv=new ImageView(imagen);
        return imgv;
    
    }

    public String getTipoCod() {
        return tipoCod;
    }

    public void setTipoCod(String tipoCod) {
        this.tipoCod = tipoCod;
    }
    @Override
    public String toString(){
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
}
