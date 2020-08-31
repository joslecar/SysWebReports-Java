/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import conexionDB.ConexionSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jose Leonardo
 */
public class Rubro {
    private String master;
    private String codigo;
    private String nombre;
    private float valor;
    private boolean pideval;

    public Rubro(String master,String codigo,String nombre, float valor, boolean pideval) {
        this.master = master;
        this.codigo = codigo;
        this.nombre = nombre;
        this.valor = valor;
        this.pideval = pideval;
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

    public boolean isPideval() {
        return pideval;
    }

    public void setPideval(boolean pideval) {
        this.pideval = pideval;
    }
    public static ObservableList<Rubro> obtenerRubros(){
        ObservableList<Rubro> listaRubros = FXCollections.observableArrayList();    
        try {
            ConexionSQL canal = new ConexionSQL();
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS","Sysapplog","sa","Rootpass1");
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select master,codigo,nombre,valor,pideval from alptabla where master=(select codigo from alptabla where nomtag='I_rubser')");
            while(r.next()){
                Rubro rubro = new Rubro(r.getString("master"),r.getString("codigo"),r.getString("nombre"),r.getFloat("valor"),r.getBoolean("pideval"));
                listaRubros.add(rubro);
            }
            canal.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRubros;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Rubro))
            return false;
        Rubro r = (Rubro)o;
        return this.nombre.equals(r.nombre);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
}
