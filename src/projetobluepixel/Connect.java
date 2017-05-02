package projetobluepixel;


import java.sql.*;


public class Connect {
    Connection conn = null;
 
    public static Connection ConnectDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:projetobluepixel/loja.sqlite");
            
            System.out.println("Connect");
            
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
