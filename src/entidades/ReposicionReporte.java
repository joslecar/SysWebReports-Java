/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;

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
    private Date fecha_t;
    private Date fecha_ven;
    private String bodega;
    private String prov_cli;
    private double total_mov;
    private double pordes;
    private double total_des;
    private double poriva;
    private double total_iva;
    private double total_trn;
    private Date userfec;
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
    
    public ReposicionReporte(String tipo, String numero, String numero_b ){
        
    }
            
    
}
