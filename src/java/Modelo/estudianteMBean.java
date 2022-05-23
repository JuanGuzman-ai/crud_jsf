/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author juand
 */
@ManagedBean
@RequestScoped
/**gran solución para evitar lineas de codigo cuando se hacen pasos de parametros
 * por ejemplo de un formulario, hacer una clase, etc
 * Basicamente el JSF Managed Bean nos facilita la forma de comunicarnos con la BD
 */
public class estudianteMBean {

    /**
     * Creates a new instance of estudianteMBean
     */
    public estudianteMBean() {
    }
    
    //atributos de la base de datos
    int codigo;
    String nombre;
    String programa;
    int telefono;
    String genero;
    String user;
    String clave;
    
    //crear los getter y setters de los atributos

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    //METODOS PARA EL CRUD
    
    //el metodo para que muestre todo en la pagina
    public List<estudianteMBean> getTablaEstudiante(){
        //creamos una lista para almacenas despues la información
      List<estudianteMBean>data = new ArrayList<>();
      Conexion conex = new Conexion();
      try{
          //nos conectamos a la bd
          Connection con = conex.getConexion();
          Statement sql = con.createStatement();
          //traigo la info de toda la tabla estudiante
          ResultSet rs = sql.executeQuery("select * from estudiante");
          //recorrer el resultado de el select
          while(rs.next()){
              //creamos un objeto y a cada atributo le mandamos la info
              estudianteMBean obj = new estudianteMBean();
              obj.setCodigo(rs.getInt("codigo"));
              obj.setNombre(rs.getString("nombre"));
              obj.setPrograma(rs.getString("programa"));
              obj.setTelefono(rs.getInt("telefono"));
              obj.setGenero(rs.getString("genero"));
              //el objeto data que es la lista se le añade todo el contenido de la tabla
              data.add(obj);
          }
      } catch (SQLException e){
          System.out.println(e);
      }
      return data;
    }
    //metodo para insertar valores
    public void agregar(){
        //creamos el objeto para la conexion de la BD
        Conexion conex = new Conexion();
        try{
            //obtenemos la conexión
            Connection con = conex.getConexion();
            //para consultas preparadas
            PreparedStatement pst;
            //el comando sql de insertar para hacer la función de agregar a la bd los datos
            pst = con.prepareStatement("insert into estudiante (codigo, nombre, programa, telefono, genero) values(?,?,?,?,?)");
            //manda los valores en orden hacia los atributos
            pst.setInt(1, codigo);
            pst.setString(2, nombre);
            pst.setString(3, programa);
            pst.setInt(4, telefono);
            pst.setString(5, genero);
            //se ejecuta la sentencia
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
     
    public void modificar(){
       Conexion conex = new Conexion();
        try{
            Connection con = conex.getConexion();
            PreparedStatement pst;
            //el comando sql de actualizar para hacer la función de actualizar los datos de la BD
            pst = con.prepareStatement("update estudiante set nombre=?, programa=?, telefono=?, genero=? where codigo=?");
            pst.setString(1, nombre);
            pst.setString(2, programa);
            pst.setInt(3, telefono);
            pst.setString(4, genero);
            pst.setInt(5, codigo);
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        } 
    }
    
    public void eliminar(){
        Conexion conex = new Conexion();
        try{
            Connection con = conex.getConexion();
            PreparedStatement pst;
            //el comando sql de eliminar
            pst = con.prepareStatement("delete from estudiante where codigo=?");
            //solo se manda la pk la cual es codigo porque con eso basta para eliminar un estudiante
            pst.setInt(1, codigo);
            pst.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

}
