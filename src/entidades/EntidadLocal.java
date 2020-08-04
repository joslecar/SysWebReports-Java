/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Jose Leonardo
 */
public class EntidadLocal {
    private String cedula;
    private String codigo;
    private String nombre;
    private String usuario;
    private String pass;
    private boolean isRespon;
    private String tipoCod;
    
    public EntidadLocal(String cedula, String codigo, String nombre, String usuario, String pass, boolean isRespon,String tipoCod){
        this.cedula = cedula;
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;  
        this.isRespon = isRespon;
        this.tipoCod = tipoCod;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isIsRespon() {
        return isRespon;
    }

    public void setIsRespon(boolean isRespon) {
        this.isRespon = isRespon;
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
    
}
