package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportistas")
public class GestionaDeportistas {

    private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String USER = "root";
    private static final String PASS = "";

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response obtenerTodos() {
        ArrayList<Deportista> listaDeportistas = new ArrayList<>();

        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS);
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("Select * from deportistas");) {
                while (rs.next()) {
                    listaDeportistas.add(new Deportista(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getBoolean("activo"),
                            rs.getString("deporte"),
                            rs.getString("genero")));
                }

                GenericEntity<List<Deportista>> entity = new GenericEntity<List<Deportista>>(listaDeportistas) {
                };

                return Response.ok(entity).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL")
                        .build();

            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se encuentra el driver").build();
        }

    }

}
