/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Date;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Municipio de Gye
 */
public class Contrato {
    private String id,contratoTacito,descripcion,strimg;
    private Cliente_eliminar cliente;
    private Boolean estado;
    private Date fechaContrato;
    private ImageView imagen;

    public Contrato(String id, String img, String contratoTacito, String descripcion, Cliente_eliminar cliente, Boolean estado, Date fechaContrato) {
        this.id = id;
        this.contratoTacito = contratoTacito;
        this.strimg=img;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.estado = estado;
        this.fechaContrato = fechaContrato;
        Image image=new Image("/recursos/"+img+".png");
        this.imagen = new ImageView(image);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContratoTacito() {
        return contratoTacito;
    }

    public void setContratoTacito(String contratoTacito) {
        this.contratoTacito = contratoTacito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStrimg() {
        return strimg;
    }

    public void setStrimg(String strimg) {
        this.strimg = strimg;
    }

    public Cliente_eliminar getCliente() {
        return cliente;
    }

    public void setCliente(Cliente_eliminar cliente) {
        this.cliente = cliente;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }
    
    
    
    
}
