import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicios {
    private static Connection conexion;

    public static void abrirConexion(String bd, String servidor, String usuario,
            String password) {
        try {
            String url = String.format("jdbc:mariadb://%s:3306/%s", servidor, bd);
            // Establecemos la conexión con la BD
            conexion = DriverManager.getConnection(url, usuario, password);
            if (conexion != null) {
                System.out.println("Conectado a " + bd + " en " + servidor);
            } else {
                System.out.println("No conectado a " + bd + " en " + servidor);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getLocalizedMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código error: " + e.getErrorCode());
        }
    }

    public static void getInfo(String bd){
        try {
            DatabaseMetaData dm = conexion.getMetaData();
            ResultSet tablas = dm.getTables(bd, null, null, null);
            while (tablas.next()) {
                System.out.println(tablas.getString("TABLE_NAME") +" - " + tablas.getString("TABLE_TYPE"));
                ResultSet columnas = dm.getColumns(bd, null, tablas.getString("TABLE_NAME"), null);
            
                while (columnas.next()) {
                    System.out.println(String.format("  %s %s %d %s %s",columnas.getString("COLUMN_NAME"),columnas.getString("TYPE_NAME"),columnas.getInt("COLUMN_SIZE"),columnas.getString("IS_NULLABLE"),columnas.getString("IS_AUTOINCREMENT")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void getInfoConuslta(){
        try (Statement st = conexion.createStatement()) {
            // String consulta = "Select * from jugadores_celta";
            String consulta = "Select nombre,dorsal from jugadores_celta";
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsm = rs.getMetaData();
            System.out.println("NUM NAME TYPE");
            for (int i = 1; i <= rsm.getColumnCount(); i++) {

                System.out.printf("%d %s %s\n",i,rsm.getColumnName(i),rsm.getColumnTypeName(i));
                
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void consultarJugadores() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select * from Jugadores_celta";
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println(rs.getInt("Dorsal") + "//" + rs.getString(2));

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void borrarJugador(int id) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Delete from jugadores_celta where dorsal =" + id;
            int numFilasAfectadas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectadas);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void jugadoresEdad(int edad) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select Nombre,Edad from jugadores_celta where edad>" + 30;
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println(rs.getString(1) + "//" + rs.getInt(2));

            }
        } catch (SQLException e) {
            System.out.println("Error");

        }
    }

   public static void insertarManuel(int dorsal, String nombre, String posicion, int edad, String nacionalidad,
                                  int convocado, int partidos_jugados, int goles, int min_jugados) {

    try (Statement st = conexion.createStatement()) {

        String consulta = "INSERT INTO jugadores_celta (dorsal, nombre, posicion, edad, nacionalidad, convocado, partidos_jugados, goles, minutos_jugados) " +
                "VALUES (" + dorsal + ", '" + nombre + "', '" + posicion + "', " + edad +
                ", '" + nacionalidad + "', " + convocado + ", " + partidos_jugados +
                ", " + goles + ", " + min_jugados + ")";

        int numFilasAfectadas = st.executeUpdate(consulta);
        System.out.println(numFilasAfectadas);

    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}


    private static PreparedStatement ps = null;

    public static void consultar(int dorsal, int edad) throws SQLException {

        String consulta = "Select * from jugadores_celta where dorsal= ? and nombre like ? and edad = ?";
        ps = conexion.prepareStatement(consulta);
        ps.setInt(1, dorsal);
         ps.setString(2, "%a%");
        ps.setInt(3, edad);
        System.out.println(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(2));

        }
    }

    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws SQLException {
        abrirConexion("celta", "localhost", "root", "");
        // consultarJugadores();
        // borrarJugador(1);
        // jugadoresEdad(30);
        // insertarManuel(99, "manuel", "Delantero", 20, "congoleño", 7, 6, 1, 3);
        // consultar(2, 29);
            // getInfo("celta");
            getInfoConuslta();
        cerrarConexion();
    }

}
