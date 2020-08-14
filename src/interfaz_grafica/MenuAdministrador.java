/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import entidades.Vendedor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Municipio de Gye
 */
public class MenuAdministrador {
    
       private VBox principal,datos,cajabot;
       private Label lbienv;
       private HBox cajadatos;
       private ImageView imgv;
       private Button agregarEmpleado,agregarCliente,cerrarSesion;
    //TENGO QUE HACER DOS BOTONES,UNO PARA AGREGAR EMPLEADO NUEVO Y OTRO PARA PONER UNA NUEVA EMPRESA
    //OJO TAMBIEN LE DEBE SALIR CAJADATOS
    //OJO2 LOS RESPON

    public MenuAdministrador(Stage stage,Vendedor user) {
        cargarPanes(stage,user);
        
    }
       
    private void cargarPanes(Stage stage,Vendedor user){
    
        lbienv=new Label("Bienvenid@  "+user.getNombre());
        lbienv.setFont(new Font("Agency FB",40));
        lbienv.setTextFill(Color.WHITE);
        imgv=user.getImagen();
        
        Label nombre;
        
        nombre=new Label("Nombre: "+user.getNombre());
        nombre.setFont(new Font("Agency FB",22));
        nombre.setTextFill(Color.WHITE);
        
        datos=new VBox(nombre);
        datos.setStyle("-fx-background-color: #045FB4");
        datos.setSpacing(15);
        datos.setAlignment(Pos.CENTER_LEFT);
        
        cajadatos=new HBox(imgv,datos);
        cajadatos.setSpacing(20);
        cajadatos.setPadding(new Insets(0,20,0,20));
        cajadatos.setStyle("-fx-background-color: #045FB4; -fx-border-color:white; -fx-border-width: 3; -fx-border-style: solid");
        
        
        agregarCliente=new Button("Agregar Nuevo Cliente");
        agregarCliente.setFont(new Font("Agency FB",20));
        
        
        
        agregarEmpleado=new Button("Agregar Empleado");
        agregarEmpleado.setFont(new Font("Agency FB",20));
        agregarEmpleado.setMinWidth(170);
        
      
        
        cerrarSesion=new Button("Cerrar Sesión");
        cerrarSesion.setFont(new Font("Agency FB",20));
        cerrarSesion.setMinWidth(170);
        
    
        cajabot=new VBox(agregarEmpleado,agregarCliente,cerrarSesion);
        cajabot.setSpacing(25);
        cajabot.setStyle("-fx-background-color: #045FB4");
        cajabot.setAlignment(Pos.CENTER);
        
        principal=new VBox(lbienv,cajadatos,cajabot);
        principal.setSpacing(35);
        principal.setAlignment(Pos.CENTER);
        principal.setStyle("-fx-background-color: #045FB4");
        
    
        cerrarSesion.setOnAction((e1)->{
            e1.consume();
            Login nlogin=new Login(stage);
            stage.setScene(new Scene(nlogin.getPrincipal(),500,500));
            
        });
        
        agregarEmpleado.setOnAction((e1)->{
            e1.consume();
            PantallaAgregarEmpleado agregarEmp=new PantallaAgregarEmpleado(stage,user);
            stage.setScene(new Scene(agregarEmp.getPrincipal(),700,700));
        
        });
        
        agregarCliente.setOnAction((e2)->{
            e2.consume();
            PantallaAgregarCliente addcli=new PantallaAgregarCliente(stage,user);
            stage.setScene(new Scene(addcli.getPrincipal(),900,600));
        });
    }
    
    public VBox getPrincipal(){
        return principal;
    
    }
    
}
