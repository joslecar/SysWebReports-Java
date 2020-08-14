/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;


import conexionDB.ConexionSQL;
import entidades.Cliente;
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
import entidades.Reporte;
import entidades.Rubro;
import entidades.Vendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
/**
 *
 * @author Municipio de Gye
 */
public class MenuPrincipal {
       private VBox principal,datos,cajabot;
       private Label lbienv;
       private HBox cajadatos;
       private ImageView imgv,fondo;
       private Button generarRep,verRep,cerrarSesion;
       private StackPane contenedor;
       private Stage stagelocal;

    public MenuPrincipal(Stage stage,Vendedor user) {
        inicializarPanes(stage,user);
        
        
    }
    
    private void inicializarPanes(Stage stage,Vendedor user){
        contenedor = new StackPane();
        Screen pantalla = Screen.getPrimary();
        Rectangle2D bounds = pantalla.getVisualBounds();
        stage.setMaximized(true);
        //stagelocal.setMaximized(true);
        fondo = new ImageView(new Image("recursos/imagen1.png"));
        fondo.setFitHeight(bounds.getHeight());
        fondo.setFitWidth(bounds.getWidth());
        lbienv=new Label("Bienvenid@  "+user.getNombre());
        lbienv.setFont(new Font("Agency FB",40));
        lbienv.setTextFill(Color.WHITE);
        imgv=user.getImagen();
        
        Label nombre;
        
        nombre=new Label("Usuario: "+user.getNombre());
        nombre.setFont(new Font("Agency FB",22));
        nombre.setTextFill(Color.WHITE);
        
        
        datos=new VBox(nombre);
        datos.setSpacing(20);
        datos.setAlignment(Pos.CENTER_LEFT);
        
        cajadatos=new HBox(imgv,datos);
        cajadatos.setSpacing(10);
        cajadatos.setPadding(new Insets(0,10,0,10));
        cajadatos.setStyle(" -fx-border-color:white; -fx-border-width: 3; -fx-border-style: solid");
        
        generarRep=new Button("Ingresar Reporte");
        generarRep.setFont(new Font("Agency FB",20));
        
        
        
        verRep=new Button("Consulta");
        verRep.setFont(new Font("Agency FB",20));
        
        
        cerrarSesion=new Button("Cerrar Sesión");
        cerrarSesion.setMinSize(139,27);
        cerrarSesion.setFont(new Font("Agency FB",20));
        cerrarSesion.setStyle("-fx-border-radius: 20px");
  
        
        
        cajabot=new VBox(generarRep,verRep,cerrarSesion);
        cajabot.setSpacing(25);
        //cajabot.setStyle("-fx-background-color: #045FB4");
        cajabot.setAlignment(Pos.CENTER);
        
        
        principal=new VBox(lbienv,cajadatos,cajabot);
        principal.setSpacing(35);
        principal.setAlignment(Pos.CENTER);
        //principal.setStyle("-fx-background-color: #045FB4");
        contenedor.getChildren().addAll(fondo,principal);
    
        cerrarSesion.setOnAction((e1)->{
            try {
                ConexionSQL canal = new ConexionSQL();
                canal.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            e1.consume();
            Login nlogin=new Login(stage);
            stage.setScene(new Scene(nlogin.getPrincipal(),500,500));
            
        });
        
        generarRep.setOnAction((e2)->{
            e2.consume();
            PantallaGenerarReporteN rep=new PantallaGenerarReporteN(stage,user);
 
        });
        
        verRep.setOnAction((e3)->{
                e3.consume();    
            List<Reporte> listareportes = new ArrayList<>();
            PantallaVerReportes pv = new PantallaVerReportes(stage,user,listareportes);
            stage.setScene(new Scene(pv.getPrincipal(),1100,700));
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            
        });
    }
    
    
    public StackPane getPrincipal() {
        return contenedor;
    }
       
       
       
       
       
}
