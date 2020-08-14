/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import com.jfoenix.controls.JFXDatePicker;
import entidades.Rubro;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import recursos.SearchComboBox;

/**
 *
 * @author Jose Leonardo
 */
public class TiempoRubro {
    Stage stage;
    BorderPane root;
    ComboBox<Rubro> rubros;
    JFXDatePicker time;
    
    public TiempoRubro(){
        stage = new Stage();
        rubros = new ComboBox();
        root = new BorderPane();
        time = new JFXDatePicker();
        ordenarPanes();
        manejarRubros();
    }   
    private void ordenarPanes(){
        stage.setScene(new Scene(root));
        root.setTop(rubros);
        rubros.setItems(Rubro.obtenerRubros());
        stage.show();
    }
    private void manejarRubros(){
        rubros.setOnAction(e->{
            System.out.println(rubros.getValue());
        });
    }
}
