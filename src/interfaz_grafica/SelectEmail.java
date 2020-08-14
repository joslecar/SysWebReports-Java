/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import entidades.Reporte;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import reporteador.Email;

/**
 *
 * @author Jose Leonardo
 */
public class SelectEmail {
    VBox principal;
    HBox lineabtn, lineaFacturacion;
    GridPane grid;
    Button aceptar,cerrar;
    List<String> listamails;
    String numerorep;
    Label headerText;
    Label labelOtroEmail;
    TextField otroEmail;
    Stage stage;
    Scene scene;
    CheckBox checkFacturacion;
    Label lbFacturacion;
    boolean viewFacturacion;
    String tipoCod;
    String regex = "^(.+)@(.+)$";
    
    public SelectEmail(List<String> listamails, String numerorep, boolean viewFacturacion, String tipoCod){
        this.listamails = listamails;
        this.numerorep = numerorep;
        this.viewFacturacion = viewFacturacion;
        this.tipoCod = tipoCod;
        inicializar();
        organizarPanes();
        crearOpciones();
    }
    
    private void inicializar(){
        stage = new Stage();
        principal = new VBox();
        scene = new Scene(principal);
        lineabtn = new HBox();
        lineaFacturacion = new HBox();
        grid = new GridPane();
        headerText = new Label("Seleccione los correos a los que desea enviar");
        aceptar = new Button("Enviar");
        cerrar = new Button("Cerrar");
        labelOtroEmail = new Label("Otro email");
        otroEmail = new TextField();
        lbFacturacion = new Label("Enviar a facturacion");
        checkFacturacion = new CheckBox();
    }
    private void organizarPanes(){
        headerText.setStyle("-fx-font-size: 15px;");
        principal.getChildren().addAll(headerText,grid,lineabtn);
        principal.setSpacing(10);
        lineaFacturacion.getChildren().addAll(checkFacturacion,lbFacturacion);
        lineabtn.getChildren().addAll(aceptar,cerrar);
        lineabtn.setPadding(new Insets(10,10,10,10));
        grid.setPadding(new Insets(10,10,10,10));
        aceptar.setOnAction(e->{
            enviarCorreo();
        });
        cerrar.setOnAction(e->{
            stage.close();
        });
    }
    private void crearOpciones(){
        int count = 0;
        if(!listamails.isEmpty()){
            for (String email : listamails) {
                ToggleButton emailbutton = new ToggleButton(email.trim());
                emailbutton.getStylesheets().add("recursos/button.css");
                emailbutton.setMaxWidth(220);
                grid.add(emailbutton, 0, count);
                count++;  
            }   
        }
        if(viewFacturacion){
            grid.add(lineaFacturacion,0,count);
        }
        grid.add(labelOtroEmail,0,count+1);
        grid.add(otroEmail,1,count+1);       
        
    }
    private void enviarCorreo(){
        List<String> emailsEnviar = new ArrayList<>();
        for(Node n: grid.getChildren()){
            if(n instanceof ToggleButton){
                ToggleButton t = (ToggleButton)n;
                if(t.isSelected()){
                    emailsEnviar.add(t.getText());
                }   
            }
        }
        if(!otroEmail.getText().isEmpty()){
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(otroEmail.getText());
            if(matcher.matches()){
                emailsEnviar.add(otroEmail.getText());
            }else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setHeaderText("Correo invalido");
                a.setContentText("El correo ingresado no cumple el patrón desigando de correos");
                a.showAndWait();
            }
        }if(checkFacturacion.isSelected() || !viewFacturacion){
                emailsEnviar.add("facturacion@syscompsa.com");
        }
        Runnable envioCorreo2 = new Email(emailsEnviar,"","R"+tipoCod+"-"+numerorep,numerorep,tipoCod);
        new Thread(envioCorreo2).start();
        stage.close();
    }
    public void display(){
        stage.setScene(scene);
        stage.show();
    }
}
