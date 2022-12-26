

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nacho
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persistencia {
    private Connection cn;
    private ResultSet rs;
    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    
    public String servidor, basededatos, usuario, clave, ejecutar;
    
    public Connection conectarse(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        
        
        servidor="localhost:3306/";
        basededatos="cac_integrating_proyect_2022";
        usuario = "nacho@localhost";
        clave = "";
        
        /* Validamos que se conecte a pesar de los errores y que sea una conexion segura con: ?autoReconect=true&useSSL=false*/
        cn=DriverManager.getConnection("jdbc:mysql://" + servidor+basededatos+"?autoReconect=true&useSSL=false", usuario, clave);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cn;
    }
    
    /**
     * @return ************ *********************/
    /* Establecer consulta a la base de datos */
    public ResultSet consultaSQL(String busqueda){
        try {
            /* Llama al metodo conectarse y ejecuta la consulta a la base de datos */ 
            ps = conectarse().prepareStatement(busqueda);
            rs = ps.executeQuery();
            rsm = rs.getMetaData();        
            
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    return rs;
    }
    
    
    public void cargarPedidoSQL(String pedido){
        try {
            ps = conectarse().prepareStatement(pedido);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
