/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Leonardo
 */


public class ConexionSQL {

    // Librería de MySQL
    public String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    // Nombre de la base de datos
    //public String database = "data01";

    // Host
//<<<<<<< HEAD
   //public String hostname = "169.254.127.21";
//=======
//>>>>>>> a837ff43041664401c12801dbb61fb286b8317c8*/
    //public String hostname = "SYSWEBSERVICE\\SQLEXPRESS"; "186.68.42.222\SQLEXPRESS,52167"

    // Puerto 1403
    //public String port = "1433"; "49165"

    
     // Nombre de usuario
    //public String username = "sa";

    // Clave de usuario
    
    //public String password = "Rootpass1";
    
    // Ruta de nuestra base de datos 
    //public String url = "jdbc:sqlserver://" + hostname + ":" + port + ";databaseName=" + database+";user="+username +";password="+password;

   
    
    public Connection conn;
    //Metodo para iniciar la conexion
    public Connection conectarSQL(String hostname, String database, String username, String password) throws SQLException {
        try {
            conn = null;
            String url = "jdbc:sqlserver://"+ hostname +":49165;databaseName="+database+";user="+username+";password="+password;
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            
            return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //Metodo para cerrar la conexion
    public void desconectar() throws SQLException{
    if (conn != null) {
        conn.close();
  }
    
    }

}