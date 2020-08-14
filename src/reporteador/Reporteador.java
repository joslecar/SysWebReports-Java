/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporteador;

import conexionDB.ConexionSQL;
import interfaz_grafica.ErrorMessage;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Jose Leonardo
 */
public class Reporteador {
    
    
    public void crearReporte(String numero_rs, String tipoUser){
        try {
            
            ConexionSQL con = new ConexionSQL();
            InputStream is = Reporteador.class.getResourceAsStream("/reporteador/ReporteFinal.jasper");
            //JasperReport reporte = (JasperReport) JRLoader.loadObject(is);
            //System.out.println(reporte.toString());
            Map parametro = new HashMap();
            parametro.put("numero_rs", numero_rs);
            parametro.put("imagen", Reporteador.class.getResourceAsStream("/recursos/Syscompsa.jpg"));
            parametro.put("tipoUser", tipoUser);
            JasperPrint j = JasperFillManager.fillReport(is, parametro,con.conectarSQL("SYSWEBSERVICE\\SQLEXPRESS", "data01", "sa", "Rootpass1") );
            JasperViewer jv = new JasperViewer(j,false);
            jv.setTitle("Reporte de servicio");
            jv.setVisible(true);
        } catch (JRException ex) {
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error JRException");
            a.setContentText( ex.getLocalizedMessage());
            System.out.println(ex);
            a.showAndWait();
        } catch (SQLException ex) {
            ErrorMessage e = new ErrorMessage(ex.getClass().getName(),"Excepcion en Reporteador CrearReporte",ex.toString());
            e.display();
            Logger.getLogger(Reporteador.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText(ex.toString());
            a.showAndWait();
            Logger.getLogger(Reporteador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void guardarReporte(String numero_rs,String tipoUser){
        JasperReport jasperReport;        
        try{
          ConexionSQL con = new ConexionSQL();
          //se carga el reporte
          //in=this.getClass().getResource("src/reporteador/ReporteFinal.jasper" );
          InputStream in = Reporteador.class.getResourceAsStream("/reporteador/ReporteFinal.jasper");
          //jasperReport=(JasperReport)JRLoader.loadObject("src/reporteador/ReporteFinal.jasper");
          //jasperReport=(JasperReport)JRLoader.loadObject(in);
          //se procesa el archivo jasper
          Map parametro = new HashMap();
          parametro.put("numero_rs", numero_rs);
          parametro.put("imagen", Reporteador.class.getResourceAsStream("/recursos/Syscompsa.jpg"));
          parametro.put("tipoUser", tipoUser);
          JasperPrint jasperPrint = JasperFillManager.fillReport(in, parametro, con.conectarSQL("SYSWEBSERVICE\\SQLEXPRESS", "data01", "sa", "Rootpass1") );
          //se crea el archivo PDF
          File directorio = new File("./reportesGuardados");
          if(!directorio.exists()){
            if(directorio.mkdirs()){
                JasperExportManager.exportReportToPdfFile( jasperPrint, "./reportesGuardados/R"+tipoUser+"-"+numero_rs+".pdf");
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Carpeta creada");
                a.setContentText("Reporte guardado con exito");
                a.showAndWait();
            }else{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Error en guardar Reporte");
                a.setContentText("La carpeta reportesGuardados no ha sido creada");
                a.showAndWait();
            }
          }else{
                JasperExportManager.exportReportToPdfFile( jasperPrint, "./reportesGuardados/R"+tipoUser+"-"+numero_rs+".pdf");
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Carpeta existente");
                a.setContentText("Reporte guardado con exito");
                a.showAndWait();
          }
        }catch (JRException ex){
            Platform.runLater(()->{
                ErrorMessage e = new ErrorMessage(ex.getClass().getName(),"Excepcion en Reporteador GuardarReporte",ex.toString());
                e.display();
            });
        }catch (SQLException ex) {
            Platform.runLater(()->{
                ErrorMessage e = new ErrorMessage(ex.getClass().getName(),"Excepcion en Reporteador GuardarReporte",ex.toString());
                e.display();
            });
            Logger.getLogger(Reporteador.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            Platform.runLater(()->{
                ErrorMessage e = new ErrorMessage(ex.getClass().getName(),"Excepcion en Reporteador GuardarReporte",ex.toString());
                e.display();
            });
        }
    }

}
