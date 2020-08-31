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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Leonardo
 */
public class ProductosReposicion {
    private String pc_ncta;
    private String no_parte; //campo que se une en respocion de reportes
    private String nombre;
    private String vstock;
    private String iva_sn;

    public ProductosReposicion(String pc_ncta, String no_parte, String nombre, String vstock, String iva_sn) {
        this.pc_ncta = pc_ncta;
        this.no_parte = no_parte;
        this.nombre = nombre;
        this.vstock = vstock;
        this.iva_sn = iva_sn;
    }
    
    public static ObservableList<ProductosReposicion> obtenerProductosReposicion() {
        ObservableList<ProductosReposicion> listaProductos = FXCollections.observableArrayList();
        try {
            ConexionSQL canal = new ConexionSQL();
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select pc_ncta,no_parte,nombre,vstock,iva_sn from dp03a110 where repo_cc=1");
            while (r.next()) {
                ProductosReposicion producto = new ProductosReposicion(r.getString("pc_ncta"),r.getString("no_parte"),r.getString("nombre"),r.getString("vstock"),r.getString("iva_sn"));
                listaProductos.add(producto);
            }
            canal.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProductos;
    }
    
    public String getPc_ncta() {
        return pc_ncta;
    }

    public void setPc_ncta(String pc_ncta) {
        this.pc_ncta = pc_ncta;
    }

    public String getNo_parte() {
        return no_parte;
    }

    public void setNo_parte(String no_parte) {
        this.no_parte = no_parte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVstock() {
        return vstock;
    }

    public void setVstock(String vstock) {
        this.vstock = vstock;
    }

    public String getIva_sn() {
        return iva_sn;
    }

    public void setIva_sn(String iva_sn) {
        this.iva_sn = iva_sn;
    }
    
    
}
