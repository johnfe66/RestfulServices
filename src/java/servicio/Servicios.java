/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import conf.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.Usuario;

/**
 * REST Web Service
 *
 * @author Johnfe Vargas
 */
@Path("usuario")
public class Servicios {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of prueba
     */
    public Servicios() {
    }

    /**
     * Retrieves representation of an instance of servicio.prueba
     *
     * @param key
     * @param user
     * 
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUser() {
        Usuario u = new Usuario();
        u.setId(1);
        u.setNombre("felipe");
        u.setCedula("1234.556.8.9");
        u.setUsuario("johnfe");
        u.setClave("j0hn");
        return u;
    }

    @GET
    @Path("/listuser")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getListUser() {

        List<Usuario> lista = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Usuario u = new Usuario();
            u.setId(i);
            u.setNombre("Felipe "+i);
            lista.add(u);
        }

        return lista;
    }

   
    /**
     * PUT method for updating or creating an instance of prueba
     *
     * @param nombre
     * @param usuario
     * @param clave
     * @param cedula
     * @param id
     * @param content representation for the resource
     * @return 
     * @throws java.sql.SQLException 
     */
    @GET
    @Path("/nuevoUsuario")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String nuevoUsuario(@QueryParam("nombre") String nombre,
                               @QueryParam("usuario") String usuario,
                               @QueryParam("clave") String clave,
                               @QueryParam("cedula") String cedula,
                               @QueryParam("id") String id) throws SQLException {
        int insertados=0;
        Conexion conexion= new Conexion();
        String mensaje="";
        try {
            
            //conexion.getConexion().setAutoCommit(false);
            PreparedStatement sentenciaPreparada = null;
            String sql="Insert into usuario (nombre,usuario,clave,cedula,id) values(?,?,?,?,?)";
            sentenciaPreparada= conexion.getConexion().prepareStatement(sql);
            sentenciaPreparada.setString(1,nombre);
            sentenciaPreparada.setString(2,usuario);
            sentenciaPreparada.setString(3,clave);
            sentenciaPreparada.setString(4,cedula);
            sentenciaPreparada.setString(5,id);
            insertados=sentenciaPreparada.executeUpdate();
         //   conexion.getConexion().commit();
            conexion.getConexion().close();
            
            
        } catch (SQLException ex) {
           // conexion.getConexion().rollback();
            conexion.getConexion().close();
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(insertados==1){
            
            System.out.println(insertados);
            mensaje = "{\"mensaje\":\"Registro OK\"}";
        
        }else{
             mensaje = "{\"mensaje\":\"Registro Fallido\"}";
        }
    return mensaje;

}
    
    @GET
    @Path("/listaUsuarios")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> listaUsuarios() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        Conexion conexion= new Conexion();
        try {
            
            //conexion.getConexion().setAutoCommit(false);
            PreparedStatement sentenciaPreparada = null;
            String sql="select * from usuario";
            sentenciaPreparada= conexion.getConexion().prepareStatement(sql);
            ResultSet resultSet=sentenciaPreparada.executeQuery();
            
            while(resultSet.next()){
                Usuario u = new Usuario();
                u.setId(resultSet.getInt("id"));
                u.setNombre(resultSet.getString("nombre"));
                u.setCedula(resultSet.getString("cedula"));
                u.setUsuario(resultSet.getString("usuario"));
                u.setClave(resultSet.getString("clave"));
                lista.add(u);
            }
         //   conexion.getConexion().commit();
            conexion.getConexion().close();
            
            
        } catch (SQLException ex) {
           // conexion.getConexion().rollback();
            conexion.getConexion().close();
            Logger.getLogger(Servicios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return lista;

}
    
}
