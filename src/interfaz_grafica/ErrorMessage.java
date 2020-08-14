/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import javafx.scene.control.Alert;

/**
 *
 * @author Jose Leonardo
 */
public class ErrorMessage {
    String title;
    String header;
    String content;
    public ErrorMessage(String title, String header, String content){
        this.title = title;
        this.header = header;
        this.content = content;
    }
    public void display(){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        a.showAndWait();
    }
}
