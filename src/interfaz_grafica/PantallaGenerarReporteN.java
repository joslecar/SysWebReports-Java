/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import conexionDB.ConexionSQL;
import entidades.Cliente;
import entidades.Reporte;
import entidades.ReporteDetalle;
import entidades.ReposicionReporte;
import entidades.ReposicionReporteDetalle;
import entidades.Rubro;
import entidades.Vendedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import recursos.SearchComboBox;
import reporteador.Email;
import reporteador.Reporteador;

/**
 *
 * @author Jose Leonardo
 */
public class PantallaGenerarReporteN {

    private Stage stagelocal;
    private ScrollPane scroll;
    private BorderPane root;
    private GridPane grid, gridRubros;
    private ImageView logo;
    private Label lempresa, lmotivo, lfecha, lordenTrabajo, ldescripcion, lsolucion, lsolicitud, ltrabajoSolucionado, lreporte, fecha, horaFinal, idRep, lhorainicial, lhorafinal;
    private Label lfacturar, lcontrato, ldpto, lobservaciones, lhora_final, lserviciopres, serviciopres, lcliente, encabezado, encabezado1, encabezado2, lfechaReporte, lesPresencial,lbreposicion;
    private SearchComboBox<Cliente> combo;
    //private SearchComboBox<Rubro> rubros;
    private FlowPane encabezadopane;
    private HBox encabezadoreporte, seleccionbox;
    private VBox encabezadoderecho, encabezadoizquierdo;
    private Button guardar, regresar, aggRubro, reposicion;
    private JFXTimePicker hora_final, hora_inicial;
    private JFXDatePicker fechaReporte;
    private TextField ordenTrabajo, solicitadopor, dpto, hora, minuto, motivo;
    private TextArea solucion, observaciones;
    private CheckBox trabajoSolucionado, facturar, contrato, esPresencial;
    private Reporte reporte;

    //private List<ReporteDetalle> mapaRubros;
    public PantallaGenerarReporteN(Stage stage, Vendedor user) {
        cargarPanes(stage, user);
        //manejarBoxRubro();
        //stage.getWidth()/1.2,stage.getHeight()/1.2  <-- esperar para probar esta opcion
        stagelocal.setScene(new Scene(scroll));
        stagelocal.centerOnScreen();
        stagelocal.show();
        //Codigo para centrar el stage creado (!!!Es importante ponerlo despues de stagelocal.show()!!!)
        centrarStage();
    }

    private void centrarStage() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stagelocal.setX((primScreenBounds.getWidth() - stagelocal.getWidth()) / 2);
        stagelocal.setY((primScreenBounds.getHeight() - stagelocal.getHeight()) / 2);
    }

    private void cargarPanes(Stage stage, Vendedor user) {
        reporte = new Reporte();
        stagelocal = new Stage();
        scroll = new ScrollPane();
        scroll.setPadding(new Insets(10, 10, 10, 10));
        root = new BorderPane();
        scroll.setContent(root);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        grid = new GridPane();
        gridRubros = new GridPane();
        combo = new SearchComboBox<>();
        hora_inicial = new JFXTimePicker();
        hora_final = new JFXTimePicker();
        lfechaReporte = new Label("Fecha");
        fechaReporte = new JFXDatePicker();
        //mapaRubros = new LinkedHashMap<>();
        logo = new ImageView(new Image("/recursos/logoSys.png", 200, 200, true, true));
        lempresa = new Label("Empresa");
        lempresa.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lmotivo = new Label("Motivo");
        lmotivo.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        motivo = new TextField();
        lfecha = new Label("Fecha Ingreso");
        lfecha.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lordenTrabajo = new Label("Orden de Trabajo");
        lordenTrabajo.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        ldescripcion = new Label("Descripcion");
        ldescripcion.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lsolucion = new Label("Solucion");
        lsolucion.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lhorafinal = new Label("Hora final");
        lhorafinal.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lhorainicial = new Label("Hora inicial");
        lhorainicial.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lsolicitud = new Label("Solicitado por ");
        lsolicitud.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        ltrabajoSolucionado = new Label("Trabajo Solucionado ");
        ltrabajoSolucionado.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lfacturar = new Label("Facturar");
        lfacturar.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lcontrato = new Label("Contrato");
        lcontrato.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lesPresencial = new Label("Es Presencial");
        lesPresencial.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        ldpto = new Label("Dpto. ");
        ldpto.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lobservaciones = new Label("Observaciones");
        lobservaciones.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lhora_final = new Label("Tiempo Total");
        lhora_final.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lserviciopres = new Label("Servicio prestado por ");
        lserviciopres.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        serviciopres = new Label(user.getNombre());
        serviciopres.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        lcliente = new Label("Cliente");
        lcliente.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
        encabezado = new Label("Systemas y Equipos de Computacion");
        encabezado.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        encabezado.setAlignment(Pos.TOP_RIGHT);
        encabezado1 = new Label("PBX: 2924030 • e-mail: syscomps@gye.satnet.net");
        encabezado1.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        encabezado1.setTextAlignment(TextAlignment.RIGHT);
        encabezado1.setAlignment(Pos.CENTER_RIGHT);
        encabezado1.setWrapText(true);
        encabezado2 = new Label("Cdla. Vernaza Norte - Mz. 25 V-16 • Guayaquil-Ecuador");
        encabezado2.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        encabezado2.setAlignment(Pos.CENTER_LEFT);
        lreporte = new Label("Reporte de Servicio");
        lreporte.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        encabezadopane = new FlowPane();
        encabezadoizquierdo = new VBox();
        encabezadoderecho = new VBox();
        encabezadoreporte = new HBox();
        seleccionbox = new HBox();
        guardar = new Button("Guardar");
        regresar = new Button("Regresar");
        aggRubro = new Button(" + ");
        reposicion = new Button("Agregar Reposicion");
        reposicion.setDisable(true);
        lbreposicion = new Label("");
        idRep = new Label();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        horaFinal = new Label(sdf.format(cal.getTime()));
        Calendar fechaAc = new GregorianCalendar();
        int año = fechaAc.get(Calendar.YEAR);
        int mes = fechaAc.get(Calendar.MONTH);
        int dia = fechaAc.get(Calendar.DAY_OF_MONTH);
        String mesString = (String.valueOf(mes + 1).length() == 2) ? String.valueOf(mes+1):"0"+String.valueOf(mes+1);
        fecha = new Label(String.valueOf(año) + "-" + mesString + "-" + String.valueOf(dia));

        combo.setItems(Cliente.obtenerClientes());
        combo.setFilter((item, text) -> item.toString().toLowerCase().contains(text.toLowerCase()));

        combo.setOnAction(e -> {
            for (String s : combo.getValue().getEmails()) {
                System.out.println("[" + s);
            }
        });

        root.setTop(encabezadopane);
        encabezadopane.getChildren().addAll(encabezadoizquierdo, encabezadoderecho);
        encabezadopane.setAlignment(Pos.CENTER);
        encabezadopane.setHgap(70);
        encabezadopane.setOrientation(Orientation.HORIZONTAL);
        encabezadopane.setPadding(new Insets(25));
        encabezadoreporte.getChildren().addAll(lreporte, idRep);
        encabezadoreporte.setSpacing(50);
        encabezadoizquierdo.getChildren().addAll(logo, encabezadoreporte);
        encabezadoderecho.getChildren().addAll(encabezado, encabezado1, encabezado2);
        /*seleccionbox.getChildren().addAll(rubros,aggRubro);
        seleccionbox.setSpacing(30);*/
        guardar = new Button("Guardar");
        guardar.setMinSize(20, 20);

        ConexionSQL canal = new ConexionSQL();
        try {
            Connection c = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
            CallableStatement sc = c.prepareCall("{call LastIdReporte(?,?)}");
            sc.setString("tipoCod", user.getTipoCod());
            sc.registerOutParameter("IDFinal", Types.VARCHAR);
            sc.execute();
            idRep.setText(sc.getString("IDFinal"));
        } catch (SQLException ex) {
            Logger.getLogger(PantallaGenerarReporteN.class.getName()).log(Level.SEVERE, null, ex);
        }
        idRep.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 14));
        esPresencial = new CheckBox();
        regresar = new Button("Regresar");
        regresar.setMinSize(20, 20);
        regresar.setOnAction((e3) -> {
            stagelocal.close();
        });
        guardar.setOnAction(e -> {
            mostrarConfirmacionReposicion(e);
            guardarReporte(user);
        });

        esPresencial.selectedProperty().addListener((e) -> {
            if (esPresencial.isSelected()) {
                reposicion.setDisable(false);
            } else {
                reposicion.setDisable(true);
            }
        });

        reposicion.setOnAction(e -> {
            ReposicionReporteUI pantallaReposicion = new ReposicionReporteUI(reporte);
            pantallaReposicion.display();
            pantallaReposicion.getStage().setOnCloseRequest(event->{
                mostrarReposicion();
            });
        });
        ordenTrabajo = new TextField();
        solucion = new TextArea();
        solucion.setMaxSize(600, 75);
        solucion.setWrapText(true);
        solicitadopor = new TextField();
        dpto = new TextField();
        trabajoSolucionado = new CheckBox();
        facturar = new CheckBox();
        contrato = new CheckBox();
        observaciones = new TextArea();
        observaciones.setMaxSize(600, 75);
        observaciones.setWrapText(true);
        hora = new TextField();
        minuto = new TextField();
        hora.setMaxSize(40, 20);
        minuto.setMaxSize(40, 20);

        root.setCenter(grid);
        grid.add(lempresa, 0, 0);
        grid.add(combo, 1, 0);
        grid.add(lmotivo, 0, 1);
        grid.add(seleccionbox, 1, 1);
        grid.add(gridRubros, 1, 2);
        grid.add(lfecha, 2, 0);
        grid.add(fecha, 3, 0);
        grid.add(lordenTrabajo, 2, 1);
        grid.add(ordenTrabajo, 3, 1);
        grid.add(lesPresencial, 2, 2);
        grid.add(esPresencial, 3, 2);
        //grid.add(ldescripcion,0,3);
        //grid.add(descripcion,1,3);
        grid.add(lsolucion, 0, 4);
        grid.add(solucion, 1, 4);
        grid.add(lfechaReporte, 2, 4);
        grid.add(fechaReporte, 3, 4);
        grid.add(lsolicitud, 0, 5);
        grid.add(solicitadopor, 1, 5);
        grid.add(ldpto, 2, 5);
        grid.add(dpto, 3, 5);
        grid.add(ltrabajoSolucionado, 0, 6);
        grid.add(trabajoSolucionado, 1, 6);
        grid.add(lfacturar, 0, 7);
        grid.add(facturar, 1, 7);
        grid.add(lcontrato, 2, 7);
        grid.add(contrato, 3, 7);
        grid.add(lobservaciones, 0, 8);
        grid.add(observaciones, 1, 8);
        grid.add(lhora_final, 2, 9);
        grid.add(horaFinal, 3, 9);
        grid.add(lserviciopres, 0, 10);
        grid.add(serviciopres, 1, 10);
        grid.add(lhorainicial, 0, 11);
        grid.add(hora_inicial, 1, 11);
        grid.add(lhorafinal, 2, 11);
        grid.add(hora_final, 3, 11);
        grid.add(guardar, 2, 12);
        grid.add(regresar, 3, 12);
        grid.add(reposicion, 0, 12);
        grid.add(lbreposicion, 1, 12);
        grid.autosize();
        grid.setVgap(10);
        grid.setHgap(15);
        grid.setAlignment(Pos.TOP_CENTER);

        gridRubros.add(aggRubro, 0, 0);
        //gridDetalles.add();
        aggRubro.setOnAction(e -> {
            NuevoDetalle nd = new NuevoDetalle();
            nd.pantallaDetalle();
        });

        //Necesito cargar estos antes para que puedan ingresar la reposicion cuando ellos deseen
        reporte.setCod_asesor(user);
        reporte.setTipo(user.getTipoCod());
        lbreposicion.textProperty().addListener(e->{
            if(reporte.getReposicion() != null){
                if(reporte.getReposicion().getReposicionDetalles() != null){
                    String total = String.valueOf(reporte.getReposicion().getTotal_trn());
                    total = "Total :"+total+"\n" ;
                    for(ReposicionReporteDetalle repdetail: reporte.getReposicion().getReposicionDetalles()){
                        String xvalor = String.valueOf(repdetail.getPrecio_ne());
                        total += repdetail.getNo_parte()+": $"+xvalor+"\n ";
                    }
                    lbreposicion.setText(total);
                }
            }else{
                lbreposicion.setText("");
            }
            
        });
        mostrarReposicion();
    }

    private void setValuesInReposicionDetails() {
        //reporte.getReposicion().aggRepoDetalle(reposicicionDetalle);
        for (ReposicionReporteDetalle reposiciondetalle : reporte.getReposicion().getReposicionDetalles()) {
            reposiciondetalle.setFecha_tra(reporte.getFecha_rs());
        }
    }

    private void guardarReposicion(ReposicionReporte reposicion, String numeroReporte, Connection cn) throws SQLException {
        System.out.println("Estoy en guardar reposicion");
        String numeroReposicion = "";
            cn.setAutoCommit(false);
            reporte.getReposicion().setFecha_t(fechaReporte.getValue());
            reporte.getReposicion().setFecha_ven(fechaReporte.getValue());
            setValuesInReposicionDetails();
            try (CallableStatement cs = cn.prepareCall("{call GuardarReposicion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
                cs.setString("tipo", reposicion.getTipo());
                //cs.setString("numero", reposicion.getNumero());
                cs.setString("numero_b", reposicion.getNumero_b());
                cs.setString("referencia", reposicion.getReferencia());
                cs.setString("grupo", reposicion.getGrupo());
                cs.setString("tipo_t", reposicion.getTipo_t());
                cs.setDate("fecha_tra", java.sql.Date.valueOf(reposicion.getFecha_tra()));
                cs.setDate("fecha_ven", java.sql.Date.valueOf(reposicion.getFecha_ven()));
                cs.setString("bodega", reposicion.getBodega());
                cs.setString("prov_cli", reposicion.getProv_cli());
                cs.setDouble("total_mov", reposicion.getTotal_mov());
                cs.setDouble("pordes", reposicion.getPordes());
                cs.setDouble("total_des", reposicion.getTotal_des());
                cs.setDouble("poriva", reposicion.getPoriva());
                cs.setDouble("total_iva", reposicion.getTotal_iva());
                cs.setDouble("total_trn", reposicion.getTotal_trn());
                cs.setDate("userfec", java.sql.Date.valueOf(reposicion.getUserfec()));
                cs.setString("usercla", reposicion.getUsercla());
                cs.setString("comenta", reposicion.getComenta());
                cs.setBoolean("anulada", reposicion.isAnulada());
                cs.setDouble("abono", reposicion.getAbono());
                cs.setString("integracnt", reposicion.getIntegracnt());
                cs.setBoolean("canc_lote", reposicion.isCanc_lote());
                cs.setDouble("base_0", reposicion.getBase_0());
                cs.setDouble("base_tax", reposicion.getBase_tax());
                cs.setString("s_impcadu", reposicion.getS_impcadu());
                cs.setString("s_autoriza", reposicion.getS_autoriza());
                cs.setDouble("total_ice", reposicion.getTotal_ice());
                cs.setString("numero_rs", numeroReporte);
                cs.registerOutParameter("numero", Types.VARCHAR);
                cs.execute();
                numeroReposicion = cs.getString("numero");
                System.out.println("Estoy antes del for");
                for (ReposicionReporteDetalle reposiciondetalle : reposicion.getReposicionDetalles()) {
                    try (CallableStatement cs1 = cn.prepareCall("{call GuardarReposicionDetail(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
                        cs1.setString("tipo", reposiciondetalle.getTipo());
                        cs1.setString("numero", numeroReposicion);
                        cs1.setInt("linea", reposiciondetalle.getLinea());
                        cs1.setString("tipo_t", reposiciondetalle.getTipo_t());
                        cs1.setDate("fecha_tra", java.sql.Date.valueOf(reposiciondetalle.getFecha_tra()));
                        cs1.setString("no_parte", reposiciondetalle.getNo_parte());
                        cs1.setString("consumo", reposiciondetalle.getConsumo());
                        cs1.setString("bodega", reposiciondetalle.getBodega());
                        cs1.setDouble("cantidad", reposiciondetalle.getCantidad());
                        cs1.setDouble("despacho", reposiciondetalle.getDespacho());
                        cs1.setDouble("precio_u", reposiciondetalle.getPrecio_u());
                        cs1.setDouble("precio_t", reposiciondetalle.getPrecio_t());
                        cs1.setDouble("costo_u", reposiciondetalle.getCosto_u());
                        cs1.setDouble("costo_t", reposiciondetalle.getCosto_t());
                        cs1.setDouble("poriva", reposiciondetalle.getPoriva());
                        cs1.setDouble("valiva", reposiciondetalle.getValiva());
                        cs1.setDouble("pordes", reposiciondetalle.getPordes());
                        cs1.setDouble("precio_ne", reposiciondetalle.getPrecio_ne());
                        cs1.setDouble("costfactor", reposiciondetalle.getCostfactor());
                        cs1.setDouble("totfactor", reposiciondetalle.getTotfactor());
                        cs1.setString("codfactor", reposiciondetalle.getCodfactor());
                        cs1.setDouble("canfactor", reposiciondetalle.getCantfactor());
                        cs1.setDouble("porICE", reposiciondetalle.getPorICE());
                        cs1.execute();
                    }
                }

                cn.commit();
            }  
        System.out.println("Acabe guardar reposicion");
    } 

    private boolean guardarReporte(Vendedor user) {
        ConexionSQL canal1 = new ConexionSQL();
        Connection cn = null;
        //String[] horaini = horainicial.getText().split(":");
        //String[] horafin = horafinal.getText().split(":");
        System.out.println("Estoy en guardar reporte");
        if (combo.getValue() != null && !hora_final.getValue().toString().isEmpty()
                && !hora_inicial.getValue().toString().isEmpty() && hora_final.getValue().isAfter(hora_inicial.getValue())
                && !reporte.getDetalle().isEmpty()) {
            reporte.setEmpresa_rs(combo.getValue());
            reporte.setContrato(contrato.isSelected());
            reporte.setDpto(dpto.getText());
            reporte.setEsPresencial(esPresencial.isSelected());
            reporte.setFacturar(facturar.isSelected());
            reporte.setHora_fin(hora_final.getValue().toString());
            reporte.setHora_ini(hora_inicial.getValue().toString());
            reporte.setFecha_rs(fechaReporte.getValue());
            reporte.setNumero_rs(idRep.getText());
            reporte.setObservaciones(observaciones.getText());
            reporte.setOrdenTrabajo(ordenTrabajo.getText());
            reporte.setSolicitadoPor(solicitadopor.getText());
            reporte.setSolucion(solucion.getText());
            reporte.setSolucionado(trabajoSolucionado.isSelected());


            try {
                cn = canal1.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS", "Sysapplog", "sa", "Rootpass1");
                cn.setAutoCommit(false);
                String numerorep;
                try (CallableStatement cs = cn.prepareCall("{call GuardarReporte(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
                    cs.setString("tipo", user.getTipoCod());
                    String numero = lreporte.getText().replaceAll("\\s", " ");
                    cs.setString("empresa_rs", combo.getValue().getCodigo());
                    cs.setString("cod_asesor", user.getSgrupo());
                    DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate parsed = LocalDate.parse(fecha.getText(),format1);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(parsed);
                    reporte.setFechaIngreso(parsed);
                    cs.setDate("fechaIngreso", sqlDate);
                    
                    java.sql.Date fecharep = java.sql.Date.valueOf(fechaReporte.getValue());
                    reporte.setFecha_rs(fechaReporte.getValue());
                    cs.setDate("fecha_rs", fecharep);
                    cs.setString("solucion", solucion.getText());
                    cs.setBoolean("facturar", facturar.isSelected());
                    cs.setString("ordenTrabajo", ordenTrabajo.getText());
                    cs.setBoolean("solucionado", trabajoSolucionado.isSelected());
                    cs.setBoolean("contrato", contrato.isSelected());
                    cs.setString("dpto", dpto.getText());
                    cs.setString("solicitadoPor", solicitadopor.getText());
                    /*if(horainicial.getText().length() == 5)
                    horainicial.setText(horainicial.getText().concat(":00"));*/
                    cs.setString("hora_ini", hora_inicial.getValue().toString() + ":00");
                    cs.setString("observaciones", observaciones.getText());
                    /*if(horafinal.getText().length() == 5)
                    horafinal.setText(horafinal.getText().concat(":00"));*/
                    cs.setString("hora_fin", hora_final.getValue().toString() + ":00");
                    cs.setBoolean("esPresencial", esPresencial.isSelected());
                    cs.registerOutParameter("numerorep", Types.VARCHAR);
                    cs.execute();
                    numerorep = cs.getString("numerorep");
                }
                System.out.println("Estoy antes de guardar reporte detail");
                int count = 1;
                for (ReporteDetalle rd : reporte.getDetalle()) {
                    try (CallableStatement cs1 = cn.prepareCall("{call GuardarReporteDetail(?,?,?,?,?,?)}")) {
                        cs1.setString("tipo", user.getTipoCod());
                        cs1.setInt("linea", count);
                        cs1.setString("numero_rs", numerorep);
                        cs1.setString("cod_rubro", rd.getRubro().getCodigo());
                        cs1.setString("time_rubro", rd.getTiempo());
                        cs1.setString("descripcion", rd.getDescripcion());
                        cs1.execute();
                    }
                    count++;
                }
                if(reporte.getReposicion() != null){
                    guardarReposicion(reporte.getReposicion(), numerorep, cn);
                }
                cn.commit();
                cn.close();
                Reporteador rep = new Reporteador();
                rep.guardarReporte(numerorep, user.getTipoCod());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Actualizacion Exitosa");
                alert.setHeaderText("Reporte generado");
                alert.showAndWait();
                SelectEmail selectemail = new SelectEmail(combo.getValue().getEmails(), numerorep, false, user.getTipoCod());
                selectemail.display();
                stagelocal.close();

                return true;
            } catch (SQLException ex) {
                try {
                    cn.rollback();
                } catch (SQLException ex1) {
                    Alert roll = new Alert(Alert.AlertType.ERROR);
                    roll.setTitle("Error de actualizacion");
                    roll.setHeaderText("Error en ROLLBACK");
                    roll.setContentText(ex1.toString());
                    roll.showAndWait();
                }
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error de actualizacion");
                a.setHeaderText("REPORTE NO INGRESADO");
                a.setContentText(ex.toString());
                a.showAndWait();
                Logger.getLogger(PantallaGenerarReporteN.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Campos ingresados incorrectamente");
            a.setHeaderText("REPORTE NO INGRESADO");
            a.setContentText("Faltan campos necesarios para ingresar el reporte. \n"
                    + "Favor verificar que exista cliente y rubros seleccionados ademas "
                    + "de horas final e inical ingresadas correctamente");
            a.showAndWait();
        }
        return false;
    }

    public BorderPane getRoot() {
        return root;
    }
    
    private void mostrarReposicion(){
        System.out.println("Estoy en la funcionm");
        lbreposicion.textProperty().addListener(e->{
            if(reporte.getReposicion() != null){
                System.out.println("Entre en lb");
                if(reporte.getReposicion().getReposicionDetalles() != null){
                    System.out.println("Antes del for");
                    String total = String.valueOf(reporte.getReposicion().getTotal_trn());
                    total = "Total :"+total+"\n" ;
                    for(ReposicionReporteDetalle repdetail: reporte.getReposicion().getReposicionDetalles()){
                        String xvalor = String.valueOf(repdetail.getPrecio_ne());
                        total += repdetail.getNo_parte()+": $"+xvalor+"\n ";
                    }
                    lbreposicion.setText(total);
                }
            }else{
                System.out.println("Estoy en else");
                lbreposicion.setText("");
            }
            
        });
    }

    public void mostrarConfirmacionReposicion(Event e) {
        if (esPresencial.isSelected()) {
            if (reporte.getReposicion() == null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("No ha ingresado ningun valor para reposicion de gastos");
                alert.setContentText("Desea continuar?");
                alert.setTitle("Confirmacion de reposicion de gastos");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.CANCEL) {
                    e.consume();
                }
            }
        }
    }

    //nuevo stage para agregar rubros al reporte
    class NuevoDetalle {

        int hor;
        int min;
        int seg;
        private Stage pantallaRubro;
        private BorderPane rootRubro;
        SearchComboBox<Rubro> rubros;
        private VBox botones, boxtiempo;
        private VBox boxDescp;
        private Label ldescp, ltime;
        private TextArea descripcion;
        private TextField tiempodetail;
        private Button cerrar, guardardetail, guardarEdicion;
        private ReporteDetalle rdetalle;

        public NuevoDetalle() {
            inicializar();
            rootRubro.setPadding(new Insets(15, 15, 15, 15));
            rubros.setItems(Rubro.obtenerRubros());
            rubros.setFilter((item, text) -> item.toString().toLowerCase().contains(text.toLowerCase()));
            boxDescp.getChildren().addAll(ldescp, descripcion);
            boxDescp.setPadding(new Insets(10, 10, 10, 10));
            descripcion.setMaxSize(600, 75);
            descripcion.setWrapText(true);
            rootRubro.setTop(rubros);
            botones.getChildren().addAll(cerrar, guardardetail);
            rootRubro.setRight(boxtiempo);
            rootRubro.setCenter(boxDescp);
            rootRubro.setBottom(botones);
            botones.setAlignment(Pos.CENTER);
            botones.setSpacing(15);
            botones.setPadding(new Insets(15, 15, 15, 15));
            boxtiempo.getChildren().addAll(ltime, tiempodetail);
            boxtiempo.setPadding(new Insets(10, 10, 10, 10));
            manejarBotones();

        }

        private void llenarGridRubros() {
            gridRubros.getChildren().clear();
            reporte.aggDetalle(rdetalle);
            for (int i = 0; i < reporte.getDetalle().size(); i++) {
                Button quitar;
                Button editar;
                Label l;
                Label tiempo;
                gridRubros.add(editar = new Button("Editar"), 2, i);
                gridRubros.add(quitar = new Button(" x "), 3, i);
                gridRubros.add(l = new Label(reporte.getDetalle().get(i).getRubro().toString()), 0, i);
                gridRubros.add(tiempo = new Label(reporte.getDetalle().get(i).getTiempo()), 1, i);

                quitar.setOnAction(b -> {
                    botonQuitar(quitar, l);
                });
                editar.setOnAction(c -> {
                    botonEditar(editar, l);
                });

            }
            gridRubros.add(aggRubro, 0, reporte.getDetalle().size());
            renovarTiempo();
            pantallaRubro.close();
        }

        private void manejarBotones() {

            guardardetail.setOnAction(e -> {
                if (tiempodetail.getText().length() == 5) {
                    tiempodetail.setText(tiempodetail.getText().concat(":00"));
                } else if (tiempodetail.getText().length() == 2) {
                    tiempodetail.setText(tiempodetail.getText().concat(":00:00"));
                }
                rdetalle = new ReporteDetalle(rubros.getValue(), descripcion.getText(), tiempodetail.getText());
                if (rubros.getValue() != null && !reporte.getDetalle().contains(rdetalle)) {
                    llenarGridRubros();

                } else if (reporte.getDetalle().contains(rdetalle)) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Rubro repetido");
                    a.setHeaderText("No se pueden reptir los rubros");
                    a.setContentText("Se editara el detalle guardado con el mismo rubro");
                    a.showAndWait();
                    reporte.getDetalle().remove(rdetalle);
                    llenarGridRubros();

                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Especificar Rubro");
                    a.setHeaderText("No hay Rubro que guardar");
                    a.setContentText("Por favor seleccione un Rubro en la caja de opciones");
                    a.showAndWait();
                }
            });
            cerrar.setOnAction(e -> {
                pantallaRubro.close();
            });
        }

        //esta funcion se encarga de crear la pantalla al presionar el boton de +
        private void pantallaDetalle() {
            pantallaRubro = new Stage();
            pantallaRubro.setScene(new Scene(rootRubro, 600, 350));
            pantallaRubro.setMaxHeight(600);
            pantallaRubro.setMaxWidth(700);
            pantallaRubro.setFullScreen(false);
            pantallaRubro.show();
        }

        private void inicializar() {
            rootRubro = new BorderPane();
            rubros = new SearchComboBox<>();
            botones = new VBox();
            cerrar = new Button("Cerrar");
            guardardetail = new Button("Guardar");
            guardarEdicion = new Button("Guardar cambio");
            boxDescp = new VBox();
            ldescp = new Label("Descripcion");
            descripcion = new TextArea();
            ltime = new Label("Tiempo total");
            tiempodetail = new TextField("00:00:00");
            boxtiempo = new VBox();
            tiempodetail.textProperty().addListener((observable, oldvalue, newvalue) -> {
                if (newvalue.length() > oldvalue.length()) {
                    if (oldvalue.length() == 2) {
                        oldvalue = oldvalue.concat(":" + newvalue.charAt(newvalue.length() - 1));
                        tiempodetail.setText(oldvalue);
                    } else if (oldvalue.length() == 5) {
                        oldvalue = oldvalue.concat(":" + newvalue.charAt(newvalue.length() - 1));
                        tiempodetail.setText(oldvalue);
                    } else if (newvalue.length() > 8) {
                        tiempodetail.setText(oldvalue);
                    }
                }
            });
        }

        private void botonEditar(Button q, Label l) {
            //root.setDisable(true);
            NuevoDetalle nd = new NuevoDetalle();
            nd.pantallaDetalle();
            //gridRubros.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(q)));
            Iterator<ReporteDetalle> itr = reporte.getDetalle().iterator();
            while (itr.hasNext()) {
                ReporteDetalle rd = itr.next();
                if (rd.getRubro().getNombre().equals(l.getText())) {
                    nd.descripcion.setText(rd.getDescripcion());
                    nd.rubros.setValue(rd.getRubro());
                    nd.tiempodetail.setText(rd.getTiempo());
                } else {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setContentText("No existe detalle asociado");
                    a.showAndWait();
                }
            }

        }

        private void editarRubro(Button q, Label l) {
            rdetalle.setDescripcion(descripcion.getText());
            rdetalle.setRubro(rubros.getValue());
            rdetalle.setTiempo(tiempodetail.getText());
            gridRubros.getChildren().clear();
            for (int i = 0; i < reporte.getDetalle().size(); i++) {
                Button quitar;
                Button editar;
                Label la;
                Label tiempo;
                gridRubros.add(editar = new Button("Editar"), 2, i);
                gridRubros.add(quitar = new Button(" x "), 3, i);
                gridRubros.add(la = new Label(reporte.getDetalle().get(i).getRubro().toString()), 0, i);
                gridRubros.add(tiempo = new Label(reporte.getDetalle().get(i).getTiempo()), 1, i);

                quitar.setOnAction(b -> {
                    botonQuitar(quitar, la);
                });
                editar.setOnAction(c -> {
                    botonEditar(editar, la);
                    editar.setDisable(true);
                });
            }
            gridRubros.add(aggRubro, 0, reporte.getDetalle().size());
            renovarTiempo();
            pantallaRubro.close();
        }

        private void renovarTiempo() {
            int horasT = 0;
            int minutosT = 0;
            int segundosT = 0;
            for (ReporteDetalle rd : reporte.getDetalle()) {
                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                    //Date d = dateFormat.parse(hora.getText()+minutos.getText()+segundos.getText());
                    cal.setTime(dateFormat.parse(rd.getTiempo()));
                    horasT += cal.get(Calendar.HOUR);
                    minutosT += cal.get(Calendar.MINUTE);
                    segundosT += cal.get(Calendar.SECOND);
                } catch (ParseException ex) {
                    Logger.getLogger(PantallaGenerarReporteN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (segundosT >= 60) {
                segundosT = segundosT - 60;
                minutosT++;
                if (minutosT >= 60) {
                    minutosT = minutosT - 60;
                    horasT++;
                }
            }
            horaFinal.setText(String.valueOf(horasT) + ":" + String.valueOf(minutosT) + ":" + String.valueOf(segundosT));
        }

        private void botonQuitar(Button q, Label l) {
            Iterator<ReporteDetalle> itr = reporte.getDetalle().iterator();

            while (itr.hasNext()) {
                if (itr.next().getRubro().getNombre().equals(l.getText())) {
                    itr.remove();
                }
            }
            int horasT = 0;
            int minutosT = 0;
            int segundosT = 0;
            for (ReporteDetalle rd : reporte.getDetalle()) {
                try {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                    //Date d = dateFormat.parse(hora.getText()+minutos.getText()+segundos.getText());
                    cal.setTime(dateFormat.parse(rd.getTiempo()));
                    horasT += cal.get(Calendar.HOUR_OF_DAY);
                    minutosT += cal.get(Calendar.MINUTE);
                    segundosT += cal.get(Calendar.SECOND);
                } catch (ParseException ex) {
                    Logger.getLogger(PantallaGenerarReporteN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (segundosT >= 60) {
                segundosT = segundosT - 60;
                minutosT++;
                if (minutosT >= 60) {
                    minutosT = minutosT - 60;
                    horasT++;
                }
            }
            horaFinal.setText(String.valueOf(horasT) + ":" + String.valueOf(minutosT) + ":" + String.valueOf(segundosT));
            gridRubros.getChildren().removeIf(node -> Objects.equals(GridPane.getRowIndex(node), GridPane.getRowIndex(q)));
        }

        /*class ContadorTiempo implements Runnable{
        
        @Override
        public void run() {
            for(hor = 0; hor < 12; hor++){
                for(min = 0; min < 60;min ++){
                    for(seg = 0; seg < 60; seg++){
                        Platform.runLater(() -> {
                            hora.setText(String.valueOf(hor)+":");
                            minutos.setText(String.valueOf(min)+":");
                            segundos.setText(String.valueOf(seg));
                        });
                        
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PantallaGenerarReporteN.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
    }
        public BorderPane getRoot(){
            return rootRubro;
        }
    }
         */
    }
}
