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
public class CtaPorPagar {

    private String tipo;
    private String nombre;
    private String pidecompra;
    private String numero;
    private String forma;

    public CtaPorPagar(String tipo, String nombre, String pidecompra, String numero, String forma) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.pidecompra = pidecompra;
        this.numero = numero;
        this.forma = forma;

    }

    public ObservableList<CtaPorPagar> CargarCtasReposicionReporte() {
        ObservableList<CtaPorPagar> listaCtas = FXCollections.observableArrayList();
        try {
            ConexionSQL canal = new ConexionSQL();
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select tipo,nombre,pidecompra,numero,forma from dp03acom where forma = 'LCOMPRA' ");
            while (r.next()) {
                CtaPorPagar ctas = new CtaPorPagar(r.getString("tipo"), r.getString("nombre"), r.getString("pidecompra"), r.getString("numero"), r.getString("forma"));
                listaCtas.add(ctas);
            }
            canal.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCtas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPidecompra() {
        return pidecompra;
    }

    public void setPidecompra(String pidecompra) {
        this.pidecompra = pidecompra;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

}
