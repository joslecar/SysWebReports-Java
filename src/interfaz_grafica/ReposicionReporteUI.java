/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import conexionDB.ConexionSQL;
import entidades.Cliente;
import entidades.ProductosReposicion;
import entidades.Reporte;
import entidades.ReposicionReporte;
import entidades.ReposicionReporteDetalle;
import entidades.repoServicesConfig;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Leonardo
 */
public class ReposicionReporteUI {

    private Stage stage;
    private Scene sc;
    private BorderPane root;
    private GridPane tablaReposicion;
    private Button aceptar;
    private Button cancelar;
    private Label tableHeaderlb;
    private HBox lineabotones;
    private Label titulo;
    private Reporte reporte;
    private List<ReposicionReporteDetalle> listareposicionDetalles;

    public ReposicionReporteUI(Reporte r) {
        this.reporte = r;
        inicializar();
        organizarPanes();
    }

    public void inicializar() {
        stage = new Stage();
        root = new BorderPane();
        sc = new Scene(root);
        tablaReposicion = new GridPane();
        aceptar = new Button("Aceptar");
        cancelar = new Button("Cancelar");
        tableHeaderlb = new Label("Lista de productos");
        lineabotones = new HBox();
        titulo = new Label("Reposicion de gastos");
        listareposicionDetalles = new ArrayList<>();
    }

    public void organizarPanes() {
        root.setTop(titulo);
        titulo.setStyle("-fx-font-family:sans-serif; -fx-font-size:24;");
        titulo.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(titulo, Pos.CENTER);
        titulo.setPadding(new Insets(10));
        root.setCenter(tablaReposicion);
        lineabotones.getChildren().addAll(cancelar, aceptar);
        lineabotones.setPadding(new Insets(10));
        root.setBottom(lineabotones);
        tablaReposicion.setGridLinesVisible(true);
        tablaReposicion.setPadding(new Insets(10));
        aceptar.setOnAction(e -> {
            guardar();
        });
        cancelar.setOnAction(e -> {
            cancelar();
        });
        armarTabla();
    }

    public void armarTabla() {
        tablaReposicion.add(new Label("Tiene IVA?"), 0, 0);
        tablaReposicion.add(new Label("Codigo Producto"), 1, 0);
        tablaReposicion.add(new Label("Nombre Producto"), 2, 0);
        tablaReposicion.add(new Label("Valor"), 3, 0);
        tablaReposicion.add(new Label("IVA Total / Produc."), 4, 0);
        tablaReposicion.add(new Label("Cantidad"), 5, 0);
        tablaReposicion.add(new Label("Total sin IVA"), 6, 0);
        int count = 1;

        //Aqui empieza toda la logica para llenar la tabla de reposicion de reporte
        for (ProductosReposicion pr : ProductosReposicion.obtenerProductosReposicion()) {
            CheckBox tieneIva = new CheckBox();
            TextField valorIngresado = new TextField("0.00");
            TextField IVAIngresado = new TextField("0.00");
            TextField cantidadtxt = new TextField("1");
            Label totalSinIVA = new Label("");
            totalSinIVA.prefWidth(100);
            totalSinIVA.setMinWidth(100);
            IVAIngresado.setDisable(true);
            tablaReposicion.add(tieneIva, 0, count);
            tablaReposicion.add(new Label(pr.getNo_parte()), 1, count);
            tablaReposicion.add(new Label(pr.getNombre()), 2, count);
            tablaReposicion.add(valorIngresado, 3, count);
            tablaReposicion.add(IVAIngresado, 4, count);
            tablaReposicion.add(cantidadtxt, 5, count);
            tablaReposicion.add(totalSinIVA, 6, count);
            valorIngresado.setId(pr.getNo_parte() + "value");
            IVAIngresado.setId(pr.getNo_parte() + "iva");
            cantidadtxt.setId(pr.getNo_parte() + "cantidad");
            totalSinIVA.setId("totalPorFila");

            tieneIva.selectedProperty().addListener((o) -> {
                double iva = 0;
                if (tieneIva.isSelected()) {
                    arreglarIva(valorIngresado,cantidadtxt,IVAIngresado);
                } else {
                    IVAIngresado.setText("0.00");
                }
            });
            /*tieneIva.selectedProperty().not().addListener((o) -> {
                System.out.println("Not?");
                    IVAIngresado.setText("0.00");
                    
            });*/
            // **Listener para mantener el formato del campo para el valor sin IVA**
            valorIngresado.textProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                        if (!newValue.matches("\\d+\\.?\\d*")) {
                            if (newValue.equals("")) {
                                valorIngresado.setText("0");
                            } else if (newValue.equals(",")) {
                                valorIngresado.setText(valorIngresado + ".");
                            } else {
                                valorIngresado.setText(oldValue);
                            }
                        }
                        if (tieneIva.isSelected()) {
                            arreglarIva(valorIngresado, cantidadtxt, IVAIngresado);
                        }
                        totalSinIVA.setText(String.valueOf((Double.valueOf(valorIngresado.getText()) * Integer.valueOf(cantidadtxt.getText()) - Double.valueOf(IVAIngresado.getText())) ));
                    });

            IVAIngresado.textProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                        totalSinIVA.setText(String.valueOf((Double.valueOf(valorIngresado.getText()) * Integer.valueOf(cantidadtxt.getText()) - Double.valueOf(IVAIngresado.getText())) ));
                        
                    });

            // **Listener para mantener el formato del campo para el IVA**
            cantidadtxt.textProperty().addListener(
                    (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                        if (!newValue.matches("\\d+")) {
                            if (newValue.equals("")) {
                                cantidadtxt.setText("0");
                            } else {
                                cantidadtxt.setText(oldValue);
                            }
                        }
                        if (tieneIva.isSelected()) {
                            arreglarIva(valorIngresado, cantidadtxt, IVAIngresado);
                        }
                        totalSinIVA.setText(String.valueOf((Double.valueOf(valorIngresado.getText()) * Integer.valueOf(cantidadtxt.getText()) - Double.valueOf(IVAIngresado.getText())) ));

                    });
            count++;
        }
    }

    private void arreglarIva(TextField valorIngresado, TextField cantidadIngresada, TextField IVAIngresado) {
        double valor = Double.valueOf(valorIngresado.getText());
        double cantidad = Double.valueOf(cantidadIngresada.getText());
        double iva = calcularIva(valor, cantidad);
        IVAIngresado.setText(String.valueOf(iva));

    }

    private double calcularTotal() {
        double valorTotal = 0;
        Iterator<Node> itr = tablaReposicion.getChildren().iterator();
        while (itr.hasNext()) {
            Node n = itr.next();
            if (n instanceof TextField) {
                TextField valor = (TextField) n;
                TextField iva = (TextField) itr.next();
                TextField cantidad = (TextField) itr.next();
                valorTotal += (Double.valueOf(valor.getText()))*Double.valueOf(cantidad.getText());
            }
        }
        System.out.println(valorTotal);
        return valorTotal;
    }

    private double calcularTotalPorIva() {
        double valorIva = 0;
        Iterator<Node> itr = tablaReposicion.getChildren().iterator();
        while (itr.hasNext()) {
            Node n = itr.next();
            if (n instanceof TextField) {
                TextField valor = (TextField) n;
                TextField iva = (TextField) itr.next();
                TextField cantidad = (TextField) itr.next();
                valorIva += (Double.valueOf(iva.getText()));
            }
        }
        return valorIva;
    }

    private double calcularBase_0() {
        double base_0 = 0;
        Iterator<Node> itr = tablaReposicion.getChildren().iterator();
        while (itr.hasNext()) {
            Node n = itr.next();
            if (n instanceof TextField) {
                TextField valortxt = (TextField) n;
                TextField ivatxt = (TextField) itr.next();
                TextField cantidadtxt = (TextField) itr.next();
                double valor = Double.valueOf(valortxt.getText());
                double iva = Double.valueOf(ivatxt.getText());
                double cantidad = Double.valueOf(cantidadtxt.getText());
                if (iva == 0) {
                    base_0 += (valor) * cantidad;
                }
            }
        }
        System.out.println(base_0);
        return base_0;
    }

    private double calcularBase_tax() {
        double base_tax = 0;
        Iterator<Node> itr = tablaReposicion.getChildren().iterator();
        while (itr.hasNext()) {
            Node n = itr.next();
            if (n instanceof TextField) {
                TextField valortxt = (TextField) n;
                TextField ivatxt = (TextField) itr.next();
                TextField cantidadtxt = (TextField) itr.next();
                double valor = Double.valueOf(valortxt.getText());
                double iva = Double.valueOf(ivatxt.getText());
                double cantidad = Double.valueOf(cantidadtxt.getText());
                if (iva > 0) {
                    base_tax += (valor) * cantidad;
                }
            }
        }
        System.out.println(base_tax);
        return base_tax;
    }

    public void guardar() {
        ConexionSQL canal = new ConexionSQL();
        repoServicesConfig config = null;
        try {
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
            Statement s = cn.createStatement();

            ResultSet r = s.executeQuery("select * from repoServicesConfig where tipoSWS = '" + reporte.getCod_asesor().getTipoCod() + "'");
            while (r.next()) {
                config = new repoServicesConfig(r.getString("id"), r.getString("tipoSWS"), r.getString("tipoINVCAB"), r.getString("bodega"), r.getDouble("poriva"), r.getString("usercla"));
            }
            canal.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(PantallaGenerarReporteN.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (config != null) {
            LocalDate fecha = LocalDate.now();
            if(reporte.getFecha_rs() != null){
                fecha = reporte.getFecha_rs();
            }
            ReposicionReporte reposicion = new ReposicionReporte(config.getTipoINVCAB(), null, "", "", "C", "I", fecha, fecha,
                    config.getBodega(), reporte.getCod_asesor().getSgrupo(), calcularTotal() - calcularTotalPorIva(), 0.00, 0.00, config.getPoriva(), calcularTotalPorIva(),
                    calcularTotal(), fecha, config.getUsercla(), reporte.getCod_asesor().getNombre() + "////", false, 0.00, "",
                    true, calcularBase_0(), calcularBase_tax(), "", "", 0.00);
            int count = 1;
            //Usamos iterator para coger los textfields siguientes, con for no puedo hacerlo asi
            Iterator<Node> itr = tablaReposicion.getChildren().iterator();
            while (itr.hasNext()) {
                Node n = itr.next();
                if (n instanceof TextField) {
                    TextField valortxt = (TextField) n;
                    TextField ivatxt = (TextField) itr.next();
                    TextField cantidadtxt = (TextField) itr.next();
                    double valor = Double.valueOf(valortxt.getText());
                    double totaliva = Double.valueOf(ivatxt.getText());
                    double cantidad = Double.valueOf(cantidadtxt.getText());
                    double iva = totaliva > 0 ? 12.00 : 0.00;
                    if (!valortxt.getText().isEmpty() && valor != 0 && Objects.equals(GridPane.getRowIndex(valortxt), GridPane.getRowIndex(ivatxt))) {
                        Node producto = getNodeByColumnRowIndex(GridPane.getRowIndex(valortxt), 1);
                        Label lbproducto = (Label) producto;
                        //Aqui estoy creando el objeto del detalle de la reposicion del reporte
                        ReposicionReporteDetalle reposicionDetalle = new ReposicionReporteDetalle(config.getTipoINVCAB(), "", count, "I", fecha, lbproducto.getText(),
                                "", config.getBodega(), cantidad, 0.00, (valor-totaliva/cantidad), (cantidad*valor)-totaliva, (valor-totaliva/cantidad), (cantidad *valor)-totaliva, iva, totaliva, 0.00, (valor * cantidad)-totaliva, (valor-totaliva/cantidad), (valor * cantidad)-totaliva, "UNI", cantidad, 0.00);
                        reposicion.aggRepoDetalle(reposicionDetalle);
                        count++;
                    }
                }

            }
            reporte.setReposicion(reposicion);
            System.out.println(reporte.getReposicion().toString());
            stage.close();
        }
    }
    
    public boolean saveInDatabase(){
        return false;
        
    }
    private double calcularIva(double valor, double cantidad) {
        return valor * (0.12) * cantidad;
    }

    /*private double calcularTotal(double valor, double cantidad){
        double total = valor*cantidad;
        return calcularIva(valor, cantidad)+total;
    }*/
    public Node getNodeByColumnRowIndex(int row, int column) {
        Node result = null;
        for (Node n : tablaReposicion.getChildren()) {
            if (!(n instanceof Group)) {
                if (GridPane.getRowIndex(n) == row && GridPane.getColumnIndex(n) == column) {
                    result = n;
                    break;
                }
            }
        }
        return result;
    }

    public void cancelar() {
        stage.close();
    }

    public void display() {
        stage.setScene(sc);
        stage.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    
    public Stage getStage(){
        return stage;
    }

}
