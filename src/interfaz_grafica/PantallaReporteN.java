/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;

import entidades.Reporte;
import entidades.ReporteDetalle;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import reporteador.Email;
import reporteador.Reporteador;

/**
 *
 * @author Jose Leonardo
 */
public class PantallaReporteN {
    private BorderPane root;
    private GridPane grid;
    private ImageView logo;
    private Label lempresa,lmotivo,lfecha,lordenTrabajo,ldescripcion,lsolucion,lsolicitud,ltrabajoSolucionado,lreporte,fecha,horaFinal,idRep;
    private Label lfacturar,lcontrato,ldpto,lobservaciones,lhora_final,lhora_inicial,lserviciopres,serviciopres,lcliente,encabezado,encabezado1,encabezado2;
    private Label ordenTrabajo,solicitadopor,dpto,horaInicial,empresa,lfechaReporte;
    private Label descripcion,solucion,observaciones,motivo,fechaReporte;
    private Label trabajoSolucionado, facturar,contrato;
    private Button reporte, guardar, enviar;
    
    public PantallaReporteN(Reporte r){
        logo = new ImageView(new Image("/recursos/Syscompsa_Nuevo.png",160,160,true,true));
        root = new BorderPane();
        grid = new GridPane();
        reporte = new Button("Visualizar Reporte");
        guardar = new Button("Guardar");
        enviar = new Button("Enviar Reporte");
        lempresa = new Label("Empresa" + r.getEmpresa_rs().getEmpcli().trim());
        lmotivo = new Label();
        lempresa = new Label("Empresa");
        lempresa.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lmotivo= new Label("Motivo");
        lmotivo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        motivo = new Label();
        lfecha = new Label("Fecha de Visita");
        lfecha.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lfechaReporte = new Label("Fecha de Elaboracion");
        lfechaReporte.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lordenTrabajo = new Label("Orden de Trabajo");
        lordenTrabajo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ldescripcion = new Label("Descripcion");
        ldescripcion.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lsolucion = new Label("Solucion");
        lsolucion.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lsolicitud = new Label("Solicitado por ");
        lsolicitud.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ltrabajoSolucionado = new Label("Trabajo Solucionado ");
        ltrabajoSolucionado.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lfacturar = new Label("Facturar");
        lfacturar.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lcontrato = new Label("Contrato");
        lcontrato.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        ldpto = new Label("Dpto. ");
        ldpto.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lobservaciones = new Label("Observaciones");
        lobservaciones.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lhora_final = new Label("Hora Final");
        lhora_final.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lhora_inicial = new Label("Hora Inicial");
        lhora_inicial.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lserviciopres = new Label("Servicio prestado por ");
        lserviciopres.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        serviciopres = new Label(r.getCod_asesor().getNombre().trim());
        serviciopres.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lcliente = new Label("Cliente");
        lcliente.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lreporte = new Label("Reporte de Servicio");
        lreporte.setFont(Font.font("Verdana",FontWeight.BOLD,16));
        empresa = new Label(r.getEmpresa_rs().getEmpcli());
        for(ReporteDetalle rd:r.getDetalle()){
            motivo.setText(motivo.getText()+rd.getRubro().getNombre().trim()+"\n\t"+rd.getDescripcion().trim()+"\n");
            motivo.setWrapText(true);
            motivo.setMaxWidth(450);
        }
        fecha = new Label(r.getFecha_rs().toString().trim());
        ordenTrabajo = new Label(r.getOrdenTrabajo().trim());
        //fechaReporte = new Label(fecha1);
        fechaReporte = new Label(r.getFechaIngreso().toString().trim());
        solucion = new Label(r.getSolucion().trim());
        solucion.setMaxWidth(450);
        solucion.setWrapText(true);
        solicitadopor = new Label(r.getSolicitadoPor().trim());
        dpto = new Label(r.getDpto());
        trabajoSolucionado = new Label();
        if(r.isSolucionado())
            trabajoSolucionado.setText("SI");
        else
            trabajoSolucionado.setText("NO");
        facturar = new Label();
        if(r.isFacturar())
            facturar.setText("SI");
        else
            facturar.setText("NO");
        contrato = new Label();
        if(r.isContrato())
            contrato.setText("SI");
        else
            contrato.setText("NO");
        observaciones = new Label(r.getObservaciones().trim());
        observaciones.setMaxWidth(450);
        observaciones.setWrapText(true);
        horaInicial = new Label(r.getHora_ini().trim());
        horaFinal = new Label(r.getHora_fin().trim());
        serviciopres = new Label(r.getCod_asesor().getNombre().trim());
        root.setTop(logo);
        
        grid.add(lempresa, 0, 0);
        grid.add(empresa, 1, 0);
        grid.add(lmotivo, 0, 1);
        grid.add(motivo,1,1);
        grid.add(lfecha,2,0);
        grid.add(fecha,3,0);
        grid.add(lordenTrabajo,2,1);
        grid.add(ordenTrabajo,3,1);
        grid.add(lsolucion,0,3);
        grid.add(solucion,1,3);
        grid.add(lfechaReporte,2,3);
        grid.add(fechaReporte,3,3);
        grid.add(lsolicitud,0,4);
        grid.add(solicitadopor,1,4);
        grid.add(ldpto,2,4);
        grid.add(dpto,3,4);
        grid.add(ltrabajoSolucionado,0,5);
        grid.add(trabajoSolucionado,1,5);
        grid.add(lfacturar,0,6);
        grid.add(facturar,1,6);
        grid.add(lcontrato,2,6);
        grid.add(contrato,3,6);
        grid.add(lobservaciones,0,7);
        grid.add(observaciones,1,7);
        grid.add(lhora_inicial,0,8);
        grid.add(horaInicial,1,8);
        grid.add(lhora_final,2,8);
        grid.add(horaFinal,3,8);
        grid.add(lserviciopres,0,9);
        grid.add(serviciopres,1,9);
        grid.add(reporte,2,10);
        grid.add(guardar,3,10);
        grid.add(enviar,4,10);
        grid.setVgap(20);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: white");
        grid.setStyle("-fx-border-color: black");
        root.setCenter(grid);
        root.setPadding(new Insets(10,10,10,10));
        reporte.setOnAction(e->{
            Reporteador rep = new Reporteador();
            rep.crearReporte(r.getNumero_rs(), r.getTipo());
        });
        guardar.setOnAction(e->{
            Reporteador rep = new Reporteador();
            rep.guardarReporte(r.getNumero_rs(),r.getTipo());
        });
        enviar.setOnAction(e->{
            SelectEmail select = new SelectEmail(r.getEmpresa_rs().getEmails(),r.getNumero_rs(),true,r.getCod_asesor().getTipoCod());
            select.display();
        });
    }
    
    public BorderPane getRoot(){
        return root;
    }
    
    class DialogEmailSendOption{
        Reporte r;
        Stage dialog;
        Scene scene;
        VBox principalPane;
        HBox lineafacturacion;
        HBox lineacliente;
        CheckBox facturacionCheck;
        CheckBox clienteCheck;
        Button enviarbtn;
        public DialogEmailSendOption(Reporte r){
            this.r = r;
            inicializar();
            organizar();
        }
        
        private void inicializar(){
            dialog = new Stage();
            principalPane = new VBox();
            lineafacturacion = new HBox();
            lineacliente = new HBox();
            scene = new Scene(principalPane);
            facturacionCheck = new CheckBox();
            clienteCheck = new CheckBox();
            enviarbtn = new Button("Enviar");
        }
        
        private void organizar(){
            lineafacturacion.getChildren().addAll(facturacionCheck, new Label("Enviar a facturacion"));
            lineafacturacion.setSpacing(10);
            lineacliente.getChildren().addAll(clienteCheck, new Label("Enviar al cliente"));
            lineacliente.setSpacing(10);
            principalPane.getChildren().addAll(
                    new Label("Seleccione a quien desea enviar el reporte."),
                    lineafacturacion,lineacliente,enviarbtn);
            principalPane.setSpacing(10);
            principalPane.setPadding(new Insets(10,10,10,10));
            
        }
        /*private void enviar(){
            enviarbtn.setOnAction(e->{
                if(facturacionCheck.isSelected()){
                    Runnable envioCorreo = new Email("facturacion@syscompsa.com","","Reporte #"+r.getNumero_rs(), r.getNumero_rs(),r.getCod_asesor().getTipoCod());
                    new Thread(envioCorreo).start();
                }if(clienteCheck.isSelected()){
                    if(r.getEmpresa_rs().getEmails().isEmpty()){
                        Alert a = new Alert(Alert.AlertType.INFORMATION);
                        a.setHeaderText("Reporte no enviado al cliente!");
                        a.setContentText("El cliente no posee ningun email registrado en la base de datos");
                        a.showAndWait();
                    }else{
                        Runnable envioCorreo2 = new Email(r.getEmpresa_rs().getEmails().get(0),"","Reporte #"+r.getNumero_rs(),r.getNumero_rs(),r.getCod_asesor().getTipoCod());
                        new Thread(envioCorreo2).start();
                    }
                }
                dialog.close();
            });
            
        }*/
        public void display(){
            dialog.setScene(scene);
            dialog.show();
        }
    }
    
}
