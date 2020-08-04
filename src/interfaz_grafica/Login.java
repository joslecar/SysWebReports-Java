/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz_grafica;


import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import conexionDB.ConexionSQL;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import entidades.Empleado;
import entidades.EntidadLocal;
import entidades.Vendedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

/**
 *
 * @author Municipio de Gye
 */
public class Login {
    private AnchorPane root;
    private VBox principal,panelizquierdo;
    private HBox cajausuario,cajapass,cajabot;
    private Label lusuario,lpass;
    private PasswordField pass;
    private TextField usuario;
    private Button ingresar,salir,cambios,acercade;
    private Image imagen;
    private ImageView imgview,fondo;
    public  EntidadLocal user;
    private MenuPrincipal menup;
    private MenuAdministrador menuad;
    private Connection cn1;
    private Vendedor userfinal;
    private StackPane contenedor;


    public Login(Stage stage) {
        //usuariosDefecto();
        inicializarPanes(stage); 
    }
    
    
    private void inicializarPanes(Stage stage){
        root = new AnchorPane();
        contenedor = new StackPane();
        Screen pantalla = Screen.getPrimary();
        Rectangle2D bounds = pantalla.getVisualBounds();
        imagen=new Image("/recursos/Syscompsa_Nuevo.png",250,250,true,true);
        imgview=new ImageView(imagen);
        
        cambios = new Button("Cambios de cuenta2");
        acercade = new Button("Acerca de");
        
        lusuario=new Label("Usuario");
        lusuario.setTextFill(Color.WHITE);
        lusuario.setFont(new Font("Agency FB",20));
        
        lpass=new Label("Contraseña");
        lpass.setTextFill(Color.WHITE);
        lpass.setFont(new Font("Agency FB",20));
        
        pass=new PasswordField();
        usuario=new TextField();
        ingresar=new Button("Ingresar");
        ingresar.setFont(new Font("Agency FB",20));
        ingresar.setDefaultButton(true);
        
        salir=new Button("Salir");
        salir.setId("salir");

        salir.getStylesheets().add("recursos/button.css");
        salir.setBackground(Background.EMPTY);
        salir.setFont(new Font("Agency FB",20));
        
        salir.setOnAction((e)->{
            e.consume();
            System.exit(0);
        });
        ingresar.setId("iniciar");
        ingresar.getStylesheets().add("recursos/button.css");
        ingresar.setBackground(Background.EMPTY);
        ingresar.setOnAction((c)->{
            if((usuario.getText().length()!=0)&&(pass.getText().length()!=0)){
            c.consume();
            user=obtenerEmpleado(usuario.getText(),pass.getText());
            if(user!=null){
                if(!user.isIsRespon()){
                    ConexionSQL canal = new ConexionSQL();     
                    try {
                        cn1 = canal.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS", "data01", "sa", "Rootpass1");
                        Statement s = cn1.createStatement();
                        ResultSet re = s.executeQuery("select * from alptabla where master=(select codigo from alptabla where nomtag='I_Vende')  and len(ltrim(rtrim(sgrupo)))>0 and sgrupo = '"+user.getCodigo()+"';");
                        while(re.next()){
                            userfinal = new Vendedor(re.getString("master"),re.getString("codigo"),re.getString("nombre"),re.getFloat("valor"),re.getString("nomtag"),re.getString("gestion"),
                                    re.getBoolean("pideval"),re.getString("campo1"),re.getString("sgrupo"),re.getString("campo2"),re.getFloat("campo2"),re.getInt("valor2"),user.getTipoCod());
                        }
                    cn1.close();
            
                    } catch (SQLException ex) {
                        Alert a = new Alert(Alert.AlertType.WARNING);
                        a.setTitle("SQLException");
                        a.setHeaderText("Error al conectar con la base de datos");
                        a.setContentText(ex.toString());
                        a.showAndWait();
                        Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    menup=new MenuPrincipal(stage,userfinal);  
                    stage.setScene(new Scene(menup.getPrincipal(),500,500));
                }else{
                    menuad=new MenuAdministrador(stage,userfinal);
                    stage.setScene(new Scene(menuad.getPrincipal(),500,500));
                }
            }
            else{
                   Alert alert = new Alert(AlertType.WARNING);
                   alert.setTitle("No se puede iniciar sesión!!");
                   alert.setHeaderText("Usuario y/o contraseña incorrectos");
                   alert.showAndWait();
                   usuario.clear();
                   pass.clear();
            }
        
        }
            else{
               
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No se puede iniciar sesión!!");
            alert.setHeaderText("Usted no ha ingresado usuario y/o contraseña");
            alert.showAndWait();
            
            }
            
        });
        fondo = new ImageView(new Image("recursos/imagen2.png"));
        fondo.setFitHeight(bounds.getHeight());
        fondo.setFitWidth(bounds.getWidth());
        /*int numero = 1;
        String casa = "Hola";
        String total = String.valueOf(numero)+casa;*/
        System.out.println();
        cajausuario=new HBox(lusuario,usuario);
        cajausuario.setSpacing(35);
        cajausuario.setAlignment(Pos.CENTER);
        
        cajapass=new HBox(lpass,pass);
        cajapass.setSpacing(15);
        cajapass.setAlignment(Pos.CENTER);
        
        cajabot=new HBox(ingresar,salir);
        cajabot.setSpacing(25);
        cajabot.setAlignment(Pos.CENTER);
        
        panelizquierdo = new VBox();
        
        
        principal=new VBox(imgview,cajausuario,cajapass,cajabot);

        contenedor.getChildren().addAll(fondo,root);
        //Medidas de panel izquierdo
        AnchorPane.setTopAnchor(panelizquierdo, 10.0);
        AnchorPane.setRightAnchor(panelizquierdo, 1150.0);
        AnchorPane.setLeftAnchor(panelizquierdo, 10.0);
        AnchorPane.setBottomAnchor(panelizquierdo, 40.0);
        //Medidas de panel central
        AnchorPane.setTopAnchor(principal, 10.0);
        AnchorPane.setRightAnchor(principal, 200.0);
        AnchorPane.setLeftAnchor(principal, 500.0);
        AnchorPane.setBottomAnchor(principal, 40.0);
        
        root.getChildren().addAll(panelizquierdo,principal);
        panelizquierdo.getChildren().addAll(cambios,acercade);
        //panelizquierdo.setSpacing(20);
        panelizquierdo.setStyle("-fx-background-color: #90C8AD; -fx-border-color: #66957F; -fx-border-width: 5px");
        panelizquierdo.setPadding(new Insets(10,10,10,10));
        cambios.setId("cambios");
        acercade.setId("acercade");
        cambios.setMinSize(200, 45);
        cambios.setMaxSize(200, 75);
        cambios.setBackground(Background.EMPTY);
        acercade.setMinSize(200, 45);
        acercade.setMaxSize(200, 75);
        acercade.setBackground(Background.EMPTY);
        cambios.getStylesheets().add("recursos/button.css");
        acercade.getStylesheets().add("recursos/button.css");
        cambios.setOnAction(e->{
            
        });
        
        principal.setSpacing(30);
        principal.setAlignment(Pos.CENTER);
        
    }

    public StackPane getPrincipal() {
        return contenedor;
    }
    
    private EntidadLocal obtenerEmpleado(String usuario,String pass){
        ConexionSQL canalSQL = new ConexionSQL();
        EntidadLocal e = null;
         try {
            cn1 = canalSQL.conectarSQL("SYSWEBSERVICE\\\\SQLEXPRESS","Sysapplog","sa","Rootpass1");
            Statement s = cn1.createStatement();
            ResultSet re = s.executeQuery("select * from Vendedores where usuario = '"+usuario+"' and pass ='"+pass+"';");
            while(re.next()){
                e = new EntidadLocal(re.getString("cedula"),re.getString("codigo"),re.getString("nombre"),re.getString("usuario"),re.getString("pass"),re.getBoolean("esRespon"),re.getString("tipoCod"));

                if(re.getBoolean("esRespon"))
                    e.setIsRespon(true);
            }
            cn1.close();
        } catch (SQLException ex) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("SQLException");
            a.setHeaderText("Error al conectar con la base de datos");
            a.setContentText(ex.toString());
            a.showAndWait();
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e; 
    }
    
    
    
}
