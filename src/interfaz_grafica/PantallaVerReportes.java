/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import conexionDB.ConexionSQL;
import entidades.Cliente;
import entidades.Reporte;
import entidades.ReporteDetalle;
import entidades.Rubro;
import entidades.Vendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Municipio de Gye
 */
public class PantallaVerReportes {

    private BorderPane principal;
    private ListView<Reporte> lvwreportes;
    private VBox vbox;
    private HBox cajaFec, cajabot, cajaNom;
    private Button regresar, buscarFecha, buscarNombre, busquedaConjunta;
    private DatePicker fecha1, fecha2;
    private TextField busquedaNombre;
    private String query;
    private Stage stage;
    private ObservableList<Reporte> listareportes1;
    private Vendedor user;

    public PantallaVerReportes(Stage stage, Vendedor user, List<Reporte> reportes) {
        cargarPanes(stage, user, reportes);
    }

    private void cargarPanes(Stage stage, Vendedor user, List<Reporte> reportes) {
        this.user = user;
        this.stage = stage;
        vbox = new VBox();
        cajaFec = new HBox();
        cajaNom = new HBox();
        fecha1 = new DatePicker(LocalDate.now());
        fecha2 = new DatePicker(LocalDate.now());
        query = "select * from (webservices w join webservices_detail wd on w.numero_rs = wd.numero_rs and w.tipo = wd.tipo) join alptabla a on wd.cod_rubro = a.codigo "
                + "join dp05a110 d on w.empresa_rs = d.codigo join (select sgrupo, nombre as nombreAsesor from alptabla where master = (select codigo from alptabla where nomtag = 'I_VENDE')) na "
                + "on w.cod_asesor = na.sgrupo";
        buscarFecha = new Button("Buscar por Fecha");
        buscarNombre = new Button("Buscar por Nombre");
        busquedaConjunta = new Button("Busqueda filtrada");
        listareportes1 = FXCollections.observableArrayList();
        principal = new BorderPane();
        busquedaNombre = new TextField();
        busquedaNombre.setPromptText("Buscar por Nombre");
        lvwreportes = new ListView<>();

        //manejo del boton para buscar por fechas
        buscarFecha.setOnAction(e -> {
            listareportes1.clear();
            if (fecha1.getValue() == null || fecha2.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Date error");
                alert.setHeaderText("Por favor llene los DOS campos de fechas");
                alert.setContentText("No es posible realizar su busqueda si no ingresa los rangos de fecha necesarios");
                alert.showAndWait();
            } else if (fecha1.getValue().isAfter(fecha2.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Date error");
                alert.setHeaderText("Revisar los datos de fecha ingresados");
                alert.setContentText("Verifique si los datos ingresados en los campos son correctos");
                alert.showAndWait();
            } else {
                buscarPorFecha();
            }
        });
        //manejo del boton para buscar por nombres
        buscarNombre.setOnAction(e -> {
            listareportes1.clear();
            if (busquedaNombre.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Name Field error");
                alert.setHeaderText("Por favor llene el campo nombre");
                alert.setContentText("No es posible realizar su busqueda si no ingresa un nombre para buscar");
                alert.showAndWait();
            } else {
                buscarPorNombre();
            }

        });
        busquedaConjunta.setOnAction(e -> {
            listareportes1.clear();
            if (busquedaNombre.getText().isEmpty() && (fecha1.getValue() == null || fecha2.getValue() == null)) {
                ErrorMessage error = new ErrorMessage("Filter error", "No existen filtros para buscar", "Llene algun campo para empezar la busqueda!");
                error.display();
            } else if (fecha1.getValue().isAfter(fecha2.getValue())) {
                ErrorMessage error = new ErrorMessage("Date error", "Revisar los datos de la fecha ingresada", "Verifique si los datos ingresados en los campos son correctos");
                error.display();
            } else if (busquedaNombre.getText().isEmpty()) {
                buscarPorFecha();
            } else if(fecha1.getValue() == null || fecha2.getValue() == null){
                buscarPorNombre();
            }else{
                //buscar();
            }
        });

        regresar = new Button("Regresar");
        regresar.setFont(new Font("Monaco", 20));
        regresar.setOnAction((e1) -> {
            e1.consume();
            MenuPrincipal mp = new MenuPrincipal(this.stage, user);
            this.stage.setScene(new Scene(mp.getPrincipal(), 500, 500));

        });
        lvwreportes.setPadding(new Insets(10, 10, 10, 10));
        lvwreportes.setPrefHeight(this.stage.getHeight());
        lvwreportes.setBackground(Background.EMPTY);
        lvwreportes.setOnScroll(e -> {

        });
        //lvwreportes.scrollTo(listareportes1.size());
        //lvwreportes.
        fecha1.setMaxWidth(120);
        fecha2.setMaxWidth(120);
        busquedaNombre.setMinWidth(225);
        busquedaNombre.setMaxWidth(231);
        cajaFec.getChildren().addAll(fecha1, fecha2, buscarFecha);
        cajaNom.getChildren().addAll(busquedaNombre, buscarNombre);
        vbox.getChildren().addAll(cajaNom, cajaFec, lvwreportes);
        principal.setLeft(vbox);
        regresar.setAlignment(Pos.CENTER);
        cajabot = new HBox(regresar);
        cajabot.setAlignment(Pos.CENTER);
        principal.setBottom(cajabot);
        principal.setStyle("-fx-background-color: whitesmoke");
        vbox.setStyle("-fx-background-color: gray");

    }

    private void buscarPorFecha() {
        try {
            ConexionSQL canal = new ConexionSQL();
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
            Statement s = cn.createStatement();
            ResultSet r = s.executeQuery(query + " where w.cod_asesor = '"+user.getSgrupo()+"' and w.fecha_rs between '" + fecha1.getValue().toString() + "' and '" + fecha2.getValue().toString() + "'");
            while (r.next()) {
                List<ReporteDetalle> listadetalles = new ArrayList<>();
                Statement s2 = cn.createStatement();
                ResultSet r2 = s2.executeQuery("select master,cod_rubro,nombre,valor,pideval,w.numero_rs,wd.descripcion,wd.time_rubro from (webservices w join webservices_detail wd on w.numero_rs = wd.numero_rs and w.tipo = wd.tipo) join alptabla a on wd.cod_rubro = a.codigo join "
                        + " dp05a110 d on w.empresa_rs = d.codigo where w.numero_rs = '" + r.getString("numero_rs") + "'" + " and w.tipo = '" + r.getString("tipo") + "'");
                while (r2.next()) {
                    ReporteDetalle detalle = new ReporteDetalle(new Rubro(r2.getString("master"), r2.getString("cod_rubro"), r2.getString("nombre"), r2.getFloat("valor"), r2.getBoolean("pideval")),
                            r2.getString("descripcion"), r2.getString("time_rubro"));
                    listadetalles.add(detalle);
                }
                r2.close();
                s2.close();
                Reporte reporte = new Reporte(r.getString("tipo"), r.getString("numero_rs"),
                        new Cliente(r.getString("codigo"), r.getString("pc_ncta"),
                                r.getString("empcli"), r.getString("nomcli"), r.getString("ruc"), r.getString("vendedor"), r.getString("email")),
                        new Vendedor(r.getString("master"), r.getString("cod_asesor"), r.getString("nombreAsesor"),
                                r.getFloat("valor"), r.getString("nomtag"), r.getString("gestion"), r.getBoolean("pideval"),
                                r.getString("campo1"), r.getString("sgrupo"), r.getString("campo2"), r.getFloat("lencod"),
                                r.getInt("valor2"), user.getTipoCod()), r.getDate("fecha_rs").toLocalDate(), r.getDate("fechaIngreso").toLocalDate(), r.getString("solucion"), r.getBoolean("facturar"), r.getString("ordenTrabajo"),
                        r.getBoolean("solucionado"), r.getBoolean("contrato"), r.getString("dpto"), r.getString("SolicitadoPor"),
                        r.getString("hora_ini"), r.getString("hora_fin"), r.getString("observaciones"));
                reporte.setDetalle(listadetalles);
                if (!listareportes1.contains(reporte)) {
                    listareportes1.add(reporte);
                }
            }           
            lvwreportes = crearListView(listareportes1);
            vbox.getChildren().add(lvwreportes);
            canal.desconectar();
        } catch (SQLException ex) {
            ErrorMessage error = new ErrorMessage("Error en busqueda", ex.getClass().toString(), ex.toString());
            error.display();
        }
    }

    private void buscarPorNombre() {
        try {
            ConexionSQL canal = new ConexionSQL();
            Connection cn = canal.conectarSQL("SYSWEBSERVICE\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
            try (Statement s = cn.createStatement(); ResultSet r = s.executeQuery(query + " where w.cod_asesor = '"+user.getSgrupo()+"' and empcli COLLATE SQL_Latin1_General_Cp1_CI_AI like '%" + busquedaNombre.getText() + "%'")) {
                while (r.next()) {
                    List<ReporteDetalle> listadetalles = new ArrayList<>();
                    Statement s2 = cn.createStatement();
                    ResultSet r2 = s2.executeQuery("select master,cod_rubro,nombre,valor,pideval,w.numero_rs,wd.descripcion,wd.time_rubro from (webservices w join webservices_detail wd on w.numero_rs = wd.numero_rs and w.tipo = wd.tipo) join alptabla a on wd.cod_rubro = a.codigo join "
                            + " dp05a110 d on w.empresa_rs = d.codigo where w.numero_rs = '" + r.getString("numero_rs") + "'" + " and w.tipo = '" + r.getString("tipo") + "'");

                    while (r2.next()) {
                        ReporteDetalle detalle = new ReporteDetalle(new Rubro(r2.getString("master"), r2.getString("cod_rubro"), r2.getString("nombre"), r2.getFloat("valor"), r2.getBoolean("pideval")),
                                r2.getString("descripcion"), r2.getString("time_rubro"));
                        listadetalles.add(detalle);
                    }
                    s2.close();
                    r2.close();
                    Reporte reporte = new Reporte(r.getString("tipo"), r.getString("numero_rs"),
                            new Cliente(r.getString("codigo"), r.getString("pc_ncta"),
                                    r.getString("empcli"), r.getString("nomcli"), r.getString("ruc"), r.getString("vendedor"), r.getString("email")),
                            new Vendedor(r.getString("master"), r.getString("cod_asesor"), r.getString("nombreAsesor"),
                                    r.getFloat("valor"), r.getString("nomtag"), r.getString("gestion"), r.getBoolean("pideval"),
                                    r.getString("campo1"), r.getString("sgrupo"), r.getString("campo2"), r.getFloat("lencod"),
                                    r.getInt("valor2"), user.getTipoCod()), r.getDate("fecha_rs").toLocalDate(), r.getDate("fechaIngreso").toLocalDate(), r.getString("solucion"), r.getBoolean("facturar"), r.getString("ordenTrabajo"),
                            r.getBoolean("solucionado"), r.getBoolean("contrato"), r.getString("dpto"), r.getString("SolicitadoPor"),
                            r.getString("hora_ini"), r.getString("hora_fin"), r.getString("observaciones"));
                    reporte.setDetalle(listadetalles);
                    if (!listareportes1.contains(reporte)) {
                        listareportes1.add(reporte);
                    }

                }
                lvwreportes = crearListView(listareportes1);
                vbox.getChildren().add(lvwreportes);
                canal.desconectar();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ListView<Reporte> crearListView(ObservableList<Reporte> r) {
        vbox.getChildren().remove(lvwreportes);
        ListView<Reporte> lista = new ListView<>();
        lista.setCellFactory(b -> new CeldaReporteN());
        lista.setItems(r);
        lista.setPadding(new Insets(10, 10, 10, 10));
        lista.setFixedCellSize(130);
        lista.setPrefHeight(stage.getHeight());
        lista.setBackground(Background.EMPTY);
        //lista.scrollTo(r.size());
        return lista;
    }

    public BorderPane getPrincipal() {
        return principal;
    }

    class CeldaReporteN extends ListCell<Reporte> {

        private BorderPane root;
        private FlowPane cuerpo;
        private HBox linea;
        private HBox horas, motivos;
        private Label nroReporte;
        private Label cliente;
        private Label fecha;
        private Label hora_final;
        private Label hora_inicial;
        private Label motivo;
        private Label contMotivo;

        public CeldaReporteN() {
            inicializar();
            root.setCenter(cuerpo);
            linea.getChildren().addAll(nroReporte, fecha);
            linea.setSpacing(25);
            horas.setSpacing(20);
            //motivos.getChildren().addAll(motivo,contMotivo);
            horas.getChildren().addAll(hora_inicial, hora_final);
            cuerpo.getChildren().addAll(linea, cliente, motivos, horas);
            cuerpo.setOrientation(Orientation.VERTICAL);
            cuerpo.setVgap(5);
            cuerpo.setStyle("-fx-border-color: whitesmoke");
            cuerpo.setBackground(Background.EMPTY);
            root.setStyle("-fx-border-color: blue; -fx-border-width: 5px;-fx-background-insets: 0; -fx-padding: 0;");
            root.setBackground(Background.EMPTY);
        }

        public final void inicializar() {
            root = new BorderPane();
            cuerpo = new FlowPane();
            linea = new HBox();
            nroReporte = new Label();
            cliente = new Label();
            fecha = new Label();
            hora_final = new Label();
            hora_inicial = new Label();
            motivo = new Label("Motivo: ");
            horas = new HBox();
            motivos = new HBox();
            contMotivo = new Label();
        }

        @Override
        public void updateItem(Reporte r, boolean empty) {
            if (r != null && !empty) {
                setGraphic(root);
                nroReporte.setText("Reporte #" + r.getNumero_rs());
                nroReporte.setFont(Font.font("Verdana", FontWeight.BOLD, 9));
                cliente.setText(r.getEmpresa_rs().getEmpcli());
                fecha.setText(r.getFecha_rs().toString());
                fecha.setFont(Font.font("Times new Roman", FontWeight.SEMI_BOLD, 10));
                hora_inicial.setText("Hora Inicial: " + r.getHora_ini().toString());
                hora_inicial.setFont(Font.font("Times new Roman", FontWeight.BOLD, 10));
                hora_final.setText("Hora Final: " + r.getHora_fin());
                hora_final.setFont(Font.font("Times new Roman", FontWeight.BOLD, 10));
                r.getDetalle().forEach((ru) -> {
                    contMotivo.setText(ru.getRubro().getNombre() + "\n");
                });
                contMotivo.setFont(Font.font("Times new Roman", FontWeight.SEMI_BOLD, 10));
                motivo.setFont(Font.font("Times new Roman", FontWeight.SEMI_BOLD, 10));
                root.setBackground(Background.EMPTY);
                root.setOnMouseClicked(e -> {
                    PantallaReporteN repor = new PantallaReporteN(r);
                    principal.setCenter(repor.getRoot());
                });

            } else {
                setGraphic(null);
            }

        }
    }

}
