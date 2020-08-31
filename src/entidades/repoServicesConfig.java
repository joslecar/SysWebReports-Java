/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Leonardo
 */
public class repoServicesConfig {
    private String id;
    private String tipoSWS;
    private String tipoINVCAB;
    private String bodega;
    private double poriva;
    private String usercla;

    public repoServicesConfig(String id, String tipoSWS, String tipoINVCAB, String bodega, double poriva, String usercla) {
        this.id = id;
        this.tipoSWS = tipoSWS;
        this.tipoINVCAB = tipoINVCAB;
        this.bodega = bodega;
        this.poriva = poriva;
        this.usercla = usercla;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoSWS() {
        return tipoSWS;
    }

    public void setTipoSWS(String tipoSWS) {
        this.tipoSWS = tipoSWS;
    }

    public String getTipoINVCAB() {
        return tipoINVCAB;
    }

    public void setTipoINVCAB(String tipoINVCAB) {
        this.tipoINVCAB = tipoINVCAB;
    }

    public String getBodega() {
        return bodega;
    }

    public void setBodega(String bodega) {
        this.bodega = bodega;
    }

    public double getPoriva() {
        return poriva;
    }

    public void setPoriva(double poriva) {
        this.poriva = poriva;
    }

    public String getUsercla() {
        return usercla;
    }

    public void setUsercla(String usercla) {
        this.usercla = usercla;
    }
    
    
}
