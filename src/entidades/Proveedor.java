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
public class Proveedor {

    public String contable1;
    public String codigo;
    public String empresa;
    public String ruc;
    public String direccion;

    public Proveedor(String contable1, String codigo, String empresa, String ruc, String direccion) {
        this.contable1 = contable1;
        this.codigo = codigo;
        this.empresa = empresa;
        this.ruc = ruc;
        this.direccion = direccion;
    }

    public static ObservableList<Proveedor> obtenerProveedores() {
        ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
        try {
            ConexionSQL canal = new ConexionSQL();
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select contable1,codigo,empresa,ruc,direccion from dp06a110");
            while (r.next()) {
                Proveedor proveedor = new Proveedor(r.getString("contable1"), r.getString("codigo"), r.getString("empresa"), r.getString("ruc"), r.getString("direccion"));
                listaProveedores.add(proveedor);
            }
            canal.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProveedores;
    }

    public String getContable1() {
        return contable1;
    }

    public void setContable1(String contable1) {
        this.contable1 = contable1;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
