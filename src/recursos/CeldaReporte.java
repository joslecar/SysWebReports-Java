/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import entidades.Reporte;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Jose Leonardo
 */
public class CeldaReporte extends ListCell<Reporte>{
    //private Reporte reporte;
    private BorderPane root;
    private FlowPane cuerpo;
    private HBox linea;
    private FlowPane lineabotones;
    private Label nroReporte;
    private Label cliente;
    private Label fecha;
    private Button visualizar;
    //private Button regresar;
    
    public CeldaReporte(BorderPane principal){
        inicializar();
        //this.reporte = reporte;
        root.setCenter(cuerpo);
            linea.getChildren().addAll(nroReporte,fecha);
            linea.setSpacing(25);
            /*lineabotones.getChildren().addAll(cliente,visualizar);
            lineabotones.setOrientation(Orientation.HORIZONTAL);*/
            //lineabotones.setHgap(5);
            cuerpo.getChildren().addAll(linea,cliente,visualizar);
            cuerpo.setOrientation(Orientation.VERTICAL);
            cuerpo.setVgap(5);
        
    
    }
    
    public final void inicializar(){
        root = new BorderPane();
        cuerpo = new FlowPane();
        linea = new HBox();
        nroReporte = new Label();
        cliente = new Label();
        fecha = new Label();
        visualizar = new Button();
        lineabotones = new FlowPane();
    }
    
    @Override
    public void updateItem(Reporte r, boolean empty){
        if(r != null && !empty){
            setGraphic(root);
            nroReporte.setText("Reporte #" + r.getNumero_rs());
            nroReporte.setFont(Font.font("Verdana", FontWeight.BOLD, 9));
            cliente.setText(r.getEmpresa_rs().getEmpcli());
            fecha.setText(r.getFecha_rs().toString());
            fecha.setFont(Font.font("Times new Roman", FontWeight.SEMI_BOLD, 10));
            visualizar.setText("Visualizar");
        }else{
            setGraphic(null);
        }
        
        visualizar.setOnAction(e->{
            //PantallaReporte repor = new PantallaReporte()
        });
        
    }
    
    
}
