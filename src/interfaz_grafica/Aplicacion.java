/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import conexionDB.ConexionSQL;
import conexionDB.ConexionSQLdata01;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Municipio de Gye
 */
public class Aplicacion extends Application {
    private Login log;
    //public static ConexionMySQL SQL = new ConexionSQL();
    @Override
    public void start(Stage stage) {
        log = new Login(stage);
        Screen pantalla = Screen.getPrimary();
        Rectangle2D bounds = pantalla.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        Scene sc=new Scene(log.getPrincipal(),bounds.getWidth(),bounds.getHeight());
        stage.setScene(sc);
        stage.setMaximized(true);
        stage.setTitle("Syscompsa App");
        //stage.setFullScreen(true);
       // sc
        stage.setOnCloseRequest((WindowEvent event) -> {
            try {
                ConexionSQLdata01 SQL = new ConexionSQLdata01();
                SQL.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            event.consume();
            Platform.exit();
            System.exit(0);
        });
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }    
    
}
