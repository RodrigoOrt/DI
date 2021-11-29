package refugio.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Clase para conectar a la base de datos
 * 
 * @author gabri
 */
public class ConnectionManager {

    private static String URL;
    private static String USER;
    private static String PASS;
    
    private Connection connection;    
    private static ConnectionManager instance;
    
    private ConnectionManager(){
        try (InputStream is = new FileInputStream("refugio.ini")){
            
            Properties prop = new Properties();
            prop.load(is);
            URL  = prop.getProperty("URL");
            USER = prop.getProperty("USER");
            PASS = prop.getProperty("PASS");
            
            //connection=DriverManager.getConnection(URL, USER, PASS);
            
            //prop.forEach((k,v)->System.out.println("Clave: " + k + " Valor: " + v));
            
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (SQLException ex) {
            ex.printStackTrace();
        } */
    }
    /*
    * Implementa el patrón de diseño Singletón
    */
    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager(); 
        }
            
        return instance;
    }
    
    /**
     * Obtiene la conexión a la base de datos
     * Implementa el patrón de diseño Singletón
     * @return Connection object
     */
    
    public synchronized Connection getConnection(){
        try {
            if (connection == null || connection.isClosed())
              connection=DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return connection;
    }
}