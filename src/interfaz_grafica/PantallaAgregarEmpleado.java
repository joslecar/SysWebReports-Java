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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
public class PantallaAgregarEmpleado {
    
    private GridPane principal;
    private Label lnombre,lapellido,ldepartamento,lcedula,lpassword,lnroHijos,lsueldo,lfechaN,ledad,lresponsable;
    private Button guardar,regresar; 
    private TextField nombre,apellido,departamento,cedula,pass,nroHijos,sueldo,fechaN,edad;
    private CheckBox responsable;
    
    public PantallaAgregarEmpleado(Stage stage,Vendedor user){
        
        inicializarPanes(stage,user);
        
    }
    
    private void inicializarPanes(Stage stage,Vendedor user){
        
        lnombre=new Label("Nombre");
        lnombre.setTextFill(Color.WHITE);
        lnombre.setFont(new Font("Agency FB",20));
        
        lapellido=new Label("Apellido");
        lapellido.setTextFill(Color.WHITE);
        lapellido.setFont(new Font("Agency FB",20));
        
        ldepartamento=new Label("Departamento");
        ldepartamento.setTextFill(Color.WHITE);
        ldepartamento.setFont(new Font("Agency FB",20));
        
        lcedula=new Label("Cédula");
        lcedula.setTextFill(Color.WHITE);
        lcedula.setFont(new Font("Agency FB",20));
        
        lpassword=new Label("Contraseña");
        lpassword.setTextFill(Color.WHITE);
        lpassword.setFont(new Font("Agency FB",20));
        
        lnroHijos = new Label("Nro de Hijos");
        lnroHijos.setTextFill(Color.WHITE);
        lnroHijos.setFont(new Font("Agency FB",20));
        
        lsueldo = new Label("Sueldo");
        lsueldo.setTextFill(Color.WHITE);
        lsueldo.setFont(new Font("Agency FB",20));
        
        lfechaN = new Label("Fecha Nacimiento");
        lfechaN.setTextFill(Color.WHITE);
        lfechaN.setFont(new Font("Agency FB",20));
        
        ledad = new Label("Edad");
        ledad.setTextFill(Color.WHITE);
        ledad.setFont(new Font("Agency FB",20));
        
        lresponsable = new Label("Responsable");
        lresponsable.setTextFill(Color.WHITE);
        lresponsable.setFont(new Font("Agency FB",20));
        
        nombre=new TextField();
        apellido=new TextField();
        departamento=new TextField();
        cedula=new TextField();
        pass=new TextField();
        nroHijos=new TextField();
        sueldo=new TextField();
        fechaN=new TextField();
        edad=new TextField();
        responsable=new CheckBox();
        
        guardar=new Button("Guardar");
        regresar=new Button("Regresar");
        
        regresar.setOnAction((e)->{   
            stage.setScene(new Scene(new MenuAdministrador(stage,user).getPrincipal(),500,500));
        });
        guardar.setOnAction((e1)->{
            if(!nombre.getText().isEmpty() && !apellido.getText().isEmpty() &&!departamento.getText().isEmpty() &&
             !cedula.getText().isEmpty() && !pass.getText().isEmpty() && !nroHijos.getText().isEmpty() &&
             !sueldo.getText().isEmpty() && !fechaN.getText().isEmpty() && !edad.getText().isEmpty()){
                
            e1.consume();
            ConexionSQL canal = new ConexionSQL();  
        try {
            Connection c = canal.conectarSQL("186.68.42.222\\SQLEXPRESS,52167", "data01", "sa", "Rootpass1");
            CallableStatement sc = c.prepareCall("{call InEmployee(?,?,?,?,?,?,?,?,?,?,?)}");
            sc.setString(1, cedula.getText());
            sc.setString(2, nombre.getText());
            sc.setString(3, apellido.getText());
            sc.setString(4, pass.getText());
            sc.setInt(5, Integer.parseInt(nroHijos.getText()));
            sc.setInt(6, Integer.parseInt(edad.getText()));
            sc.setFloat(7, Float.parseFloat(sueldo.getText()));
            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
            Date parsed = format1.parse(fechaN.getText());
            java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
            sc.setDate(8, sqlDate);
            CallableStatement sc1 = c.prepareCall("{call IdDep(?,?)}");
            sc1.setString("nombre", departamento.getText());
            sc1.registerOutParameter("id", Types.VARCHAR);
            sc1.execute();
            String codDep = sc1.getString("id");
            sc.setString(9, codDep);
            sc.setBoolean(10, responsable.isSelected());
            sc.registerOutParameter("RESPONSE", Types.INTEGER);           
            sc.execute();
            canal.desconectar();
            
        } catch (SQLException ex) {
            Logger.getLogger(PantallaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (ParseException ex) {
                Logger.getLogger(PantallaAgregarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                   alert.setTitle("Actualizacion Exitosa");
                   alert.setHeaderText("Empleado agregado");
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

        principal=new GridPane();
        principal.add(lnombre,0, 2);
        principal.add(nombre,1,2);
        principal.add(lapellido, 0,3);
        principal.add(apellido,1,3);
        principal.add(ldepartamento,0,4);
        principal.add(departamento,1,4);
        principal.add(lcedula,0,5);
        principal.add(cedula,1,5);
        principal.add(lpassword,0,6);
        principal.add(pass,1,6);
        principal.add(lnroHijos, 0, 7);
        principal.add(nroHijos, 1, 7);
        principal.add(lfechaN, 0, 8);
        principal.add(fechaN, 1, 8);
        principal.add(ledad,0,9);
        principal.add(edad,1,9);
        principal.add(lsueldo,0,10);
        principal.add(sueldo,1,10);
        principal.add(lresponsable, 0, 11);
        principal.add(responsable,1,11);
        principal.add(guardar,2,11);
        principal.add(regresar,3,11);
        principal.setVgap(35);
        principal.setHgap(15);
        
        
        principal.setAlignment(Pos.CENTER);
        principal.setStyle("-fx-background-color: #045FB4");
        
    
    
    
    }
     public GridPane getPrincipal() {
        return principal;
    }
    
}
