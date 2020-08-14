/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import conexionDB.ConexionSQL;
import entidades.Empleado;
import entidades.Vendedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Municipio de Gye
 */
public class PantallaAgregarCliente {
    
    private GridPane principal;
    private Label lidCliente,lruc_ced,lnombre,lresponsable,lubicacion,idCliente;
    private Button guardar,regresar; 
    private TextField ruc_ced,nombre,responsable;
    private TextArea ubicacion;
    
    
    public PantallaAgregarCliente(Stage stage,Vendedor user){
        
        inicializarPanes(stage,user);
    }
    
    private void inicializarPanes(Stage stage,Vendedor user){
        
        lidCliente=new Label("Id de Cliente");
        lidCliente.setFont(new Font("Agency FB",20));
        lidCliente.setTextFill(Color.WHITE);
        
        lruc_ced=new Label("Ruc o cédula");
        lruc_ced.setFont(new Font("Agency FB",20));
        lruc_ced.setTextFill(Color.WHITE);
        
        lnombre=new Label("Nombre Cliente/Empresa");
        lnombre.setFont(new Font("Agency FB",20));
        lnombre.setTextFill(Color.WHITE);
        
        lresponsable=new Label("Responsable");
        lresponsable.setFont(new Font("Agency FB",20));
        lresponsable.setTextFill(Color.WHITE);
        
        lubicacion=new Label("Ubicacion");
        lubicacion.setFont(new Font("Agency FB",20));
        lubicacion.setTextFill(Color.WHITE);
        
        idCliente =new Label(); //OJO AQUI TENGO QUE MANDAR A EJECUTAR EL PROCEDURE PARA OBTENER EL ULTIMO NUMERO DE ID Y SUMARLE 1
        ConexionSQL canal = new ConexionSQL();         
        try {
            Connection c = canal.conectarSQL("186.68.42.222\\SQLEXPRESS,52167", "data01", "sa", "Rootpass1");
            CallableStatement sc = c.prepareCall("{call LastIdCliente(?)}");
            sc.registerOutParameter("idC", Types.INTEGER);
            sc.execute();
            idCliente.setText(String.valueOf(sc.getInt("idC")+1));
        } catch (SQLException ex) {
            Logger.getLogger(PantallaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        idCliente.setFont(new Font("Agency FB",40));
        idCliente.setTextFill(Color.WHITE);
        
        guardar=new Button("Guardar");
        guardar.setFont(new Font("Agency FB",20));
        
        regresar=new Button("Regresar");
        regresar.setFont(new Font("Agency FB",20));
        
        guardar.setOnAction((e)->{
        if(!ruc_ced.getText().isEmpty() && !nombre.getText().isEmpty() && !responsable.getText().isEmpty()&&
            !ubicacion.getText().isEmpty()){
            
            e.consume();
            ConexionSQL canal1 = new ConexionSQL();        
        try{
            Connection cn = canal1.conectarSQL("186.68.42.222\\SQLEXPRESS,52167", "data01", "sa", "Rootpass1");
            CallableStatement sc = cn.prepareCall("{call InCustomer(?,?,?,?,?)}");
            sc.setInt(1, Integer.parseInt(idCliente.getText()));
            sc.setString(2, ruc_ced.getText());
            sc.setString(3, nombre.getText());
            sc.setString(4, responsable.getText());
            sc.setString(5, ubicacion.getText());         
            sc.execute();
            canal1.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(PantallaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Actualizacion Exitosa");
                    alert.setHeaderText("Cliente agregado");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(!result.isPresent())
                    // alert is exited, no button has been pressed.
                        stage.setScene(new Scene(new MenuAdministrador(stage,user).getPrincipal(),500,500));
                    else if(result.get() == ButtonType.OK)
                    //oke button is pressed
                        stage.setScene(new Scene(new MenuAdministrador(stage,user).getPrincipal(),500,500));
                    else if(result.get() == ButtonType.CANCEL)
                    // cancel button is pressed
                        stage.setScene(new Scene(new MenuAdministrador(stage,user).getPrincipal(),500,500));
                   
            }
        });
        
        regresar.setOnAction((e1)->{
            e1.consume();
            MenuAdministrador mad=new MenuAdministrador(stage,user);
            stage.setScene(new Scene(mad.getPrincipal(),500,500));
        
        });
    
        ruc_ced=new TextField();
        nombre=new TextField();
        responsable=new TextField();
        ubicacion=new TextArea();
        
              principal=new GridPane();
        principal.add(lidCliente,0, 2);
        principal.add(idCliente,1,2);
        principal.add(lruc_ced, 0,3);
        principal.add(ruc_ced,1,3);
        principal.add(lnombre,0,4);
        principal.add(nombre,1,4);
        principal.add(lresponsable,0,5);
        principal.add(responsable,1,5);
        principal.add(lubicacion,0,6);
        principal.add(ubicacion,1,6);
        principal.add(guardar,2,7);
        principal.add(regresar,3,7);
        principal.setVgap(30);
        principal.setHgap(20);
        
        
        principal.setAlignment(Pos.CENTER);
        principal.setStyle("-fx-background-color: #045FB4");
    }

    public GridPane getPrincipal() {
        return principal;
    }
    
    
    
}
