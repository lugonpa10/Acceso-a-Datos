
import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjerciciosBoletin {
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

    //EJERCICIO 1
    public static void consultarAlumnos(String cad) {
        int cont = 0;
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select * from alumnos where nombre like '%" + cad + "%'";
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "," + rs.getString(2) + "," + rs.getString(3) + "," + rs.getInt(4)
                        + "," + rs.getInt(5));

                cont++;
            }
            System.out.println(cont);

        } catch (Exception e) {

        }
    }

    //EJERCICIO 2
    public static void altaAlumnos(String nombre, String ape, int altura, int aula) {

        try (Statement st = conexion.createStatement()) {
            String consulta = "Insert into alumnos(nombre,apellidos,altura,aula) VALUES("+"'" + nombre +"'" +  ", '" + ape + "', " + altura +
                    ", " + aula + ")";
                    System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

        } catch (SQLException e) {
            System.out.println("ERROR");
        }

    }

    // EJERCICIO 2
    public static void AltaAsignaturas(String nombre){
        try (Statement st = conexion.createStatement()) {
            String consulta = "Insert into asignaturas (nombre) VALUES("+"'"+nombre+"'"+")";
            System.out.println(consulta);
             int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

            
        } catch (SQLException e) {

        }
    }

    //EJERCICIO 3
    public static void bajaAlumnos(int id){
        try (Statement st = conexion.createStatement()) {
            String consulta = "Delete from alumnos where codigo= "+ id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);
            
        } catch (SQLException e) {

            System.out.println("ERROR");
        }
    }
    //EJERCICIO 3

   public static void bajaAsignaturas(int id){
        try (Statement st = conexion.createStatement()) {
            String consulta = "Delete from asignaturas where cod= "+ id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);
            
        } catch (SQLException e) {

            System.out.println("ERROR");
        }
    }
    //EJERCICIO 4

    public static void modificarAlumnos(String nombre,int id){
        try (Statement st = conexion.createStatement()) {
            String consulta = "Update alumnos set nombre="+"'"+ nombre+ "'"+"where codigo="+id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);
            
        } catch (SQLException e) {

        }
    }
    //EJERCICIO 4

    public static void modificarAsignaturas(String nombre,int id){
        try (Statement st = conexion.createStatement()) {
            String consulta = "Update asignaturas set nombre="+"'"+ nombre+ "'"+"where cod="+id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);
            
        } catch (SQLException e) {

        }
    }
//Ejercicio 5
    public static void nombreAulasAlumnos(){
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select Distinct nombreAula from aulas join alumnos on Aulas.numero = alumnos.aula ";
            System.out.println(consulta);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString(1));

                
            }


            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void AlumnosAsignaturasNotas(){
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select Distinct Alumno.nombre,Asignaturas.nombre,notas.nota from alumno join on Alumno.codigo =Notas.alumno join asignaturas.cod = notas.asignatura where notas.nota > 5    ";
              System.out.println(consulta);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString(1));

                
            }

            
        } catch (SQLException e) {
        }
    }


    public static void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getLocalizedMessage());
        }
    }

    public static void main(String[] args) {
        abrirConexion("add", "localhost", "root", "");
        // consultarAlumnos("a");
        // altaAlumnos( "lucas", "gonzalez", 185, 21);
        // AltaAsignaturas("furbo");
        // bajaAlumnos(10);
        // bajaAsignaturas(9);
        // modificarAlumnos("Denis",2);
        // modificarAsignaturas("mates",3);
        // nombreAulasAlumnos();
        AlumnosAsignaturasNotas();

        
        cerrarConexion();
    }
}
