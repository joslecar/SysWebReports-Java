/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporteador;

import com.sun.mail.util.MailSSLSocketFactory;
import interfaz_grafica.ErrorMessage;
import java.io.File;
import java.util.List;
import java.util.Properties;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Jose Leonardo
 */
public class Email implements Runnable{
    boolean enviado = false;
    List<String> para;
    String mensaje;
    String asunto;
    String numero_rs;
    String tipoCod;
    public Email(List<String> para, String mensaje, String asunto,String numero_rs,String tipoCod){
        this.para = para;
        this.mensaje = mensaje;
        this.asunto = asunto;
        this.numero_rs = numero_rs;
        this.tipoCod = tipoCod;
    }
    
    public boolean enviarCorreo(){    
        try {
            String host = "mail.syscompsa.com";
            String de = "reporte@syscompsa.com";
            String clave ="Reporte.2019";
            
            Properties prop = System.getProperties();
            
            prop.put("mail.smtp.starttls.enable","true");
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.user",de);
            prop.put("mail.smtp.password", clave);
            prop.put("mail.smtp.port","587");
            prop.put("mail.smtp.socketFactory.class", 
                "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.auth","true");
            prop.put("mail.smtp.ssl.trust", host);
            
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.imap.ssl.trust", host);
            prop.put("mail.imap.ssl.socketFactory", sf);
            
            Session sesion = Session.getInstance(prop,null);
            sesion.setDebug(true);
            BodyPart texto = new MimeBodyPart();
            BodyPart adjunto = new MimeBodyPart();
            texto.setText("Reporte de servicio \n Realizado desde la aplicacion. ");
            File file = new File("./reportesGuardados/R"+tipoCod+"-"+numero_rs+".pdf");
            System.out.println("Estoy aqui");
            if(!file.exists()){
                /*Reporteador rep = new Reporteador();
                rep.guardarReporte(numero_rs); */
                Platform.runLater(()->{
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Archivo no encontrado");
                    a.setContentText("Primero guarde el archivo para poder enviarlo");
                    a.showAndWait();
                });
            }
            System.out.println("LLegue aca");
            //adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\Jose Leonardo\\AppData\\Local\\Temp\\reporte"+numero_rs+".pdf")));
            adjunto.setDataHandler(new DataHandler(new FileDataSource(file)));
            adjunto.setFileName("R"+tipoCod+"-"+numero_rs+".pdf");
            MimeMultipart multiParte = new MimeMultipart();

            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            MimeMessage message = new MimeMessage(sesion);
            
            message.setFrom(new InternetAddress(de));
            System.out.println("Pase los mensajes");
            /*
            
            NOTA: para enviar correo electronico masivo
            
            InternetAddress[] direcciones = new InternetAddress[para.length];
            for(int i=0;i<para.length;i++){
            direcciones[i] = new InternetAddress(para[i]);
            }
            
            for(int i=0;i<direcciones.length;i++){
            message.addRecipient(Message.RecipientType.TO, direcciones[i]);
            }
            
            */
            InternetAddress[] direcciones = new InternetAddress[para.size()];
            int count = 0;
            for(String s:para){
                direcciones[count++] = new InternetAddress(s);
            }
            message.setRecipients(Message.RecipientType.TO, direcciones);
            message.addRecipient(Message.RecipientType.CC, new InternetAddress("sylvia@syscompsa.com"));
            
            message.setSubject(asunto);
            message.setText(mensaje);
            message.setContent(multiParte);
            System.out.println("Mensaje multiparte");
            Transport transport = sesion.getTransport("smtp");
            
            transport.connect(de,clave);
            
            transport.sendMessage(message, message.getAllRecipients());
            
            transport.close();
            System.out.println("Estoy al final");
            enviado = true;
        } catch (AddressException ex) {
            Platform.runLater(()->{
                ErrorMessage e = new ErrorMessage(ex.getClass().getName(),"Error en las direcciones",ex.toString());
                e.display();
            });
        } catch (MessagingException ex) {
            Platform.runLater(()->{
                ErrorMessage e = new ErrorMessage(ex.getClass().getName(),"Error",ex.toString());
                e.display();
            });
        } catch (Exception ex){
            Platform.runLater(()->{
                ErrorMessage e = new ErrorMessage(ex.getClass().getName(),"Error",ex.toString());
                e.display();
            });
        }
        if(enviado){
            Platform.runLater(()->{
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Correo enviado");
                a.setContentText("Correo enviado con exito a "+para);
                a.showAndWait();
            });
        }
        return enviado;
    }

    @Override
    public void run() {
        enviarCorreo();
    }
}
