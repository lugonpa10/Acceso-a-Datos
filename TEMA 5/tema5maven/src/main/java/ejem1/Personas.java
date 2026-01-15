package ejem1;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/personas")
public class Personas {
    static ArrayList<Persona> personas = new ArrayList<>();

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void guardar(Persona p) {

        this.personas.add(p);

    }

    @GET
    @Produces({ MediaType.APPLICATION_XML })

    public ArrayList<Persona> listar() {
        return personas;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{nombre}")
    public Persona ver(@PathParam("nombre") String nombre) {
        for (Persona p : personas) {
            if (p.getNombre().equals(nombre)) {
                return p;

            }

        }
        return null;

    }

    @GET
    @Path("/buscar")
    @Produces((MediaType.APPLICATION_JSON))
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response ver2(@QueryParam("nombre") String cadena) {
        ArrayList<Persona> personaConPatron = new ArrayList<>();
        for (Persona p : personas) {
            if (p.getNombre().toLowerCase().contains(cadena)) {
                personaConPatron.add(p);
            }

        }
        return Response.ok(personaConPatron).build();

    }
    
    


}
