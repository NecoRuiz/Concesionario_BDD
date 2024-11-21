package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static Connection connection;

    //si alguien pide una conexion--> si esta se la doy|| si no esta la creo

    public Connection getConnection() {
        
        if(connection==null){
            
            //la creo
            newConnection();
        }
        //le doy la conexion
        return connection;

    }

    private void newConnection() {
        //url de conexion jdb:mysql://

       String url = "jdbc:mysql://127.0.0.1/concesionario";
        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            System.out.println("Error en la conexion de la base de datos.");
        }


    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error en el cierre de la conexion.");
        }finally {
            connection = null;
        }
    }
}
