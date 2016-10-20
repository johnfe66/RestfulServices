/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import model.Usuario;

/**
 * REST Web Service
 *
 * @author Johnfe Vargas
 */
@Path("servicios")
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
    @Path("/prueba")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("key") String key,@QueryParam("user") String user ) {
        return "{"+key+":"+user+":valor}";
    }

    @GET
    @Path("/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUser() {
        Usuario u = new Usuario();
        u.setId(1);
        u.setName("felipe");
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
            u.setName("Felipe "+i);
            lista.add(u);
        }

        return lista;
    }

   
    /**
     * PUT method for updating or creating an instance of prueba
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
