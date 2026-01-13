package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/personas")
public class Personas {
    static ArrayList<Persona> personas = new ArrayList<>();

    @POST
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void guardar(Persona p){
       
       
        this.personas.add(p);
        
    }

@GET
@Produces({MediaType.APPLICATION_JSON})
public ArrayList<Persona> listar(){
    return personas;

}






    
}
