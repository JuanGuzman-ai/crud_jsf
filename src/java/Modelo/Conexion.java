/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author juand
 */
//importante clase para hacer la conexión con la base de datos
public class Conexion {
    //creamos el metodo para la conexión
    public Connection getConexion(){
        Connection con = null;
        //ponemos la ruta del jdbc y donde esta alojada la bd seleccionada con su user y pwd
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/proyectoed2",
            "root",
            "root");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("e");
        }
        return con;
    }
}
