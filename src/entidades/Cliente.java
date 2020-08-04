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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jose Leonardo
 */
public class Cliente {
    private String codigo;
    private String pc_ncta;
    private String empcli;
    private String nomcli;
    private String ruc;
    private String vendedor;
    private List<String> emails = new ArrayList<>();
    
    public Cliente(String codigo,String pc_ncta, String empcli, String nomcli, String ruc, String vendedor, String email){
        this.codigo = codigo;
        this.pc_ncta = pc_ncta;
        this.empcli = empcli;
        this.nomcli = nomcli;
        this.ruc = ruc;
        this.vendedor = vendedor;
        String[] em = email.split(";");
        for(String s :em){
            s = s.trim();
        }
        emails = Arrays.asList(em);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPc_ncta() {
        return pc_ncta;
    }

    public void setPc_ncta(String pc_ncta) {
        this.pc_ncta = pc_ncta;
    }

    public String getEmpcli() {
        return empcli;
    }

    public void setEmpcli(String empcli) {
        this.empcli = empcli;
    }

    public String getNomcli() {
        return nomcli;
    }

    public void setNomcli(String nomcli) {
        this.nomcli = nomcli;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }
    
    public static ObservableList<Cliente> obtenerClientes(){
        ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();    
        try {
            ConexionSQL canal = new ConexionSQL();
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS","data01","sa","Rootpass1");
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery("select * from dp05a110");
            while(r.next()){
                Cliente client = new Cliente(r.getString("codigo"),r.getString("pc_ncta"),r.getString("empcli"),r.getString("nomcli"),r.getString("ruc"),r.getString("Vendedor"),r.getString("email"));
                listaClientes.add(client);
            }
            canal.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }
    
    @Override
    public String toString() {
        return empcli;
    }
    
}
   