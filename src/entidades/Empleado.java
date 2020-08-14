/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Municipio de Gye
 */
public class Empleado {
    private String nombre,apellido,cedula,urlimg,password;
    private Date fechaingresosistema,fechaNacimiento;
    private Map<String,Reporte_eliminar> reportes;
    private boolean isresponsable;
    private int nroHijos,edad;
    private double sueldo;
    private Departamento departamento;
    
    public Empleado(String nombre, String apellido, Departamento departamento, String cedula,
            String password,int nroHijos,int edad,double sueldo,Date fechaNacimiento
    ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.departamento = departamento;
        this.cedula = cedula;
        this.fechaingresosistema=new Date();
        urlimg="/recursos/"+cedula+".png";
        this.password=password;
        this.reportes=new HashMap<>();
        this.isresponsable=false;
        this.nroHijos=nroHijos;
        this.edad=edad;
        this.sueldo=sueldo;
        this.fechaNacimiento=fechaNacimiento;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cédula) {
        this.cedula = cédula;
    }

    public Date getFechaingresosistema() {
        return fechaingresosistema;
    }

    public void setFechaingresosistema(Date fechaingresosistema) {
        this.fechaingresosistema = fechaingresosistema;
    }

    public String getUrlimg() {
        return urlimg;
    }
    
    public ImageView getImagen(){
        Image imagen=new Image(urlimg,150,150,true,true);
        ImageView imgv=new ImageView(imagen);
        return imgv;
    
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void cambiarEstado(){
        this.isresponsable=!this.isresponsable;
    }
    
    public boolean isresponsable(){
        return isresponsable;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Map<String, Reporte_eliminar> getReportes() {
        return reportes;
    }

    public void setReportes(Map<String, Reporte_eliminar> reportes) {
        this.reportes = reportes;
    }

    public void setIsresponsable(boolean isresponsable) {
        this.isresponsable = isresponsable;
    }

    public int getNroHijos() {
        return nroHijos;
    }

    public void setNroHijos(int nroHijos) {
        this.nroHijos = nroHijos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }
    
}
