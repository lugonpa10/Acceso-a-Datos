package ejem1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.export.ExceptionFactory.SqlExceptionFactory;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/deportistas")
public class GestionaDeportistas {

    private static final String URL = "jdbc:mariadb://localhost:3306/ad_tema6";
    private static final String USER = "root";
    private static final String PASS = "";
    public static ArrayList<Deportista> listaDeportistas = new ArrayList<>();
    public static ArrayList<Deportista> listaMasculinos = new ArrayList<>();
    public static ArrayList<Deportista> listaFemeninos = new ArrayList<>();
    public static ArrayList<Deportista> listaGeneral = new ArrayList<>();

    Deportista deportista;

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public Response obtenerTodos() {

        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from deportistas");
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

    @Path("/{id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response buscarJugador(@PathParam("id") String id) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from deportistas where id = " + id);
                while (rs.next()) {
                    deportista = new Deportista(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getBoolean("activo"),
                            rs.getString("deporte"),
                            rs.getString("genero"));

                }
                return Response.ok(deportista).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL")
                        .build();
            }

        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se encuentra el driver").build();

        }

    }

    @Path("/deporte/{nombreDeporte}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response listarPorDeporte(@PathParam("nombreDeporte") String nombreDeporte) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM deportistas WHERE deporte = '" + nombreDeporte + "'");
                while (rs.next()) {
                    listaDeportistas.add(new Deportista(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getBoolean("activo"),
                            rs.getString("deporte"),
                            rs.getString("genero")));

                }
                return Response.ok(listaDeportistas).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL")
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se encuentra el driver").build();

        }

    }

    @Path("/activos")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response activos() {
        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from deportistas where activo = 1");
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

    @Path("/retirados")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response retirados() {
        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("Select * from deportistas where activo = 0");
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

    @Path("/masculinos")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response masculinos() {
        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                String genero = "Masculino";
                String consulta = "Select * from deportistas where genero='" + genero + "'";
                ResultSet rs = st.executeQuery(consulta);

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

    @Path("/femeninos")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response femeninos() {
        try {

            Class.forName("org.mariadb.jdbc.Driver");

            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                String genero = "Femenino";

                ResultSet rs = st.executeQuery("Select * from deportistas where genero='" + genero + "'");
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

    @Path("/xg")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deportesPorGenero() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try (Connection conexion = DriverManager.getConnection(URL, USER, PASS)) {
                Statement st = conexion.createStatement();
                String consulta = "Select * from deportistas";
                ResultSet rs = st.executeQuery(consulta);
                while (rs.next()) {
                    listaGeneral.add(new Deportista(rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getBoolean("activo"),
                            rs.getString("deporte"),
                            rs.getString("genero")));

                }

                ArrayList<Deportista> listaFinal = new ArrayList<>();

                for (Deportista d : listaGeneral) {
                    if (d.getGenero().equals("Masculino")) {
                        listaFinal.add(d);

                    }

                }
                for (Deportista d : listaGeneral) {
                    if (d.getGenero().equals("Femenino")) {
                        listaFinal.add(d);

                    }

                }
                return Response.ok(listaFinal).build();

            } catch (SQLException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error SQL")
                        .build();
            }
        } catch (ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("No se encuentra el driver").build();

        }
    }

}
