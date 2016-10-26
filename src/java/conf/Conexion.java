/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conf;

/**
 *
 * @author Johnfe Vargas
 */


import java.sql.Connection;


import java.sql.DriverManager; 
public class Conexion {

    public Connection getConexion() {
        Connection conexion = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://johnfedb.cetojpwybnz3.us-west-2.rds.amazonaws.com:3306/imagenes";
        String user = "johnfeuser";
        String password = "j0hnf3db";
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion correcta");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
        return conexion;
    }
}
