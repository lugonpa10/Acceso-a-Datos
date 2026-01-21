
import java.lang.Thread.State;
import java.security.PublicKey;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

    // EJERCICIO 1
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

    // EJERCICIO 2
    public static void altaAlumnos(String nombre, String ape, int altura, int aula) {

        try (Statement st = conexion.createStatement()) {
            String consulta = "Insert into alumnos(nombre,apellidos,altura,aula) VALUES(" + "'" + nombre + "'" + ", '"
                    + ape + "', " + altura +
                    ", " + aula + ")";
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

        } catch (SQLException e) {
            System.out.println("ERROR");
        }

    }

    // EJERCICIO 2
    public static void AltaAsignaturas(String nombre) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Insert into asignaturas (nombre) VALUES(" + "'" + nombre + "'" + ")";
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

        } catch (SQLException e) {

        }
    }

    // EJERCICIO 3
    public static void bajaAlumnos(int id) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Delete from alumnos where codigo= " + id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

        } catch (SQLException e) {

            System.out.println("ERROR");
        }
    }
    // EJERCICIO 3

    public static void bajaAsignaturas(int id) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Delete from asignaturas where cod= " + id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

        } catch (SQLException e) {

            System.out.println("ERROR");
        }
    }
    // EJERCICIO 4

    public static void modificarAlumnos(String nombre, int id) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Update alumnos set nombre=" + "'" + nombre + "'" + "where codigo=" + id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

        } catch (SQLException e) {

        }
    }
    // EJERCICIO 4

    public static void modificarAsignaturas(String nombre, int id) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Update asignaturas set nombre=" + "'" + nombre + "'" + "where cod=" + id;
            System.out.println(consulta);
            int numFilasAfectas = st.executeUpdate(consulta);
            System.out.println(numFilasAfectas);

        } catch (SQLException e) {

        }
    }

    // Ejercicio 5
    public static void nombreAulasAlumnos() {
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

    // Ejercicio 5_2
    public static void AlumnosAsignaturasNotas() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select  Alumnos.nombre,Asignaturas.nombre,notas.nota from notas join alumnos on Notas.alumno =Alumnos.codigo join Asignaturas on  asignaturas.cod = notas.asignatura where notas.nota >= 5";
            System.out.println(consulta);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.print("Nombre:" + rs.getString(1));
                System.out.print(" Asignaturas: " + rs.getString(2));
                System.out.println(" \nNotas: " + rs.getInt(3));

            }

        } catch (SQLException e) {
        }
    }

    // Ejercicio 5_3
    public static void AsignaturasVacias() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select Asignaturas.nombre from asignaturas where not exists (Select asignatura from notas where asignaturas.cod = notas.asignatura)";
            System.out.println(consulta);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println("Nombre: " + rs.getString(1));

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // Ejercicio 6_1

    private static PreparedStatement ps = null;

    public static void consultar(String nombre, int altura) throws SQLException {
        String consulta = "Select * from alumnos where nombre like ? and altura > ? ";
        ps = conexion.prepareStatement(consulta);
        ps.setString(1, "%a%");
        ps.setInt(2, altura);
        System.out.println(consulta);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(2));

        }

    }

    // Ejercicio 6_2

    public static void consultar2(String nombre, int altura) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "Select * from alumnos where nombre like '" + nombre + "' and altura> " + altura;
            System.out.println(consulta);
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
    }

    // Ejercicio 8
    public static void insertarColumna(String tabla, String nombre, String tipoDato, String propiedades) {
        try (Statement st = conexion.createStatement()) {
            String consulta = "ALTER TABLE " + tabla + " ADD " + nombre + tipoDato + propiedades;
            System.out.println(consulta);
            int res = st.executeUpdate(consulta);
            System.out.println("Columnas añadidas: " + res);
        } catch (SQLException e) {
            System.out.println("Error");
        }

    }

    // Ejercicio 9_A
    public static void Datos() {
        try {
            DatabaseMetaData dm = conexion.getMetaData();
            System.out.println("Nombre Driver: " + dm.getDriverName());
            System.out.println("Version Driver: " + dm.getDriverVersion());
            System.out.println("url de conexión: " + dm.getURL());
            System.out.println("Usuario conectado: " + dm.getUserName());
            System.out.println("Nombre SGBD: " + dm.getDatabaseProductName());
            System.out.println("Version SGBD: " + dm.getDatabaseProductVersion());
            System.out.println("Palabras Reservadas: " + dm.getSQLKeywords());
        } catch (SQLException e) {
            System.out.println("Error");
        }

    }

    // Ejercicio 9_B

    public static void catalogos() {
        try {
            DatabaseMetaData dm = conexion.getMetaData();
            System.out.println(dm.getCatalogs());
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    // Ejercicio 9_C
    public static void nombre_Tipo(String bd) {
        try {

            DatabaseMetaData dm = conexion.getMetaData();
            ResultSet tablas = dm.getTables(bd, null, null, null);
            while (tablas.next()) {
                System.out.println(tablas.getString("TABLE_NAME") + " - " + tablas.getString("TABLE_TYPE"));

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }

    }

    // Ejercicio 9_D

    public static void vistas(String bd) {
        try {
            DatabaseMetaData dm = conexion.getMetaData();
            ResultSet tablas = dm.getTables(bd, null, null, null);
            while (tablas.next()) {
                if (tablas.getString("TABLE_TYPE").equals("VIEW")) {

                    System.out.println(tablas.getString("TABLE_NAME") + " - " + tablas.getString("TABLE_TYPE"));
                }

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    // Ejercicio 9_E

    public static void combinarBC(String bd) throws SQLException {
        DatabaseMetaData dm = conexion.getMetaData();
        ResultSet tablas = dm.getTables(bd, null, null, null);
        System.out.println(dm.getCatalogs());

        while (tablas.next()) {
            System.out.println(tablas.getString("TABLE_NAME") + " - " + tablas.getString("TABLE_TYPE"));

        }

    }

    // Ejercicio 9_F

    public static void procedimientosAlmacenados(String bd) {

        try {
            DatabaseMetaData dm = conexion.getMetaData();
            ResultSet rs = dm.getProcedures("add", null, null);
            while (rs.next()) {
                System.out.println(rs.getString("PROCEDURE_NAME"));
            }

        } catch (SQLException e) {
            // TODO: handle exception
        }
    }

    // Ejercicio 9_G

    public static void datosTablas(String bd) {
        try {
            DatabaseMetaData dm = conexion.getMetaData();
            ResultSet tablas = dm.getTables(bd, null, "a%", null);
            while (tablas.next()) {
                System.out.println(tablas.getString("TABLE_NAME") + " - " + tablas.getString("TABLE_TYPE"));
                ResultSet columnas = dm.getColumns(bd, null, tablas.getString("TABLE_NAME"), null);

                while (columnas.next()) {
                    System.out.println(String.format(" %s %s %d %s %s", columnas.getString("COLUMN_NAME"),
                            columnas.getString("TYPE_NAME"), columnas.getInt("COLUMN_SIZE"),
                            columnas.getString("IS_NULLABLE"), columnas.getString("IS_AUTOINCREMENT")));
                }

            }

        } catch (SQLException e) {
            System.out.println("error");
        }

    }

    // Ejercicio 9_H

    public static void clavesPrimarias(String bd) {
        try {

            DatabaseMetaData dm = conexion.getMetaData();
            ResultSet rs = dm.getPrimaryKeys("add", null, null);
            System.out.println("Claves Primarias");
            while (rs.next()) {
                System.out.println(rs.getString("COLUMN_NAME"));

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public static void clavesForaneas(String bd) {
        try {

            DatabaseMetaData dm = conexion.getMetaData();
            ResultSet rs = dm.getExportedKeys("add", null, null);

            System.out.println("Claves Foraneas");
            while (rs.next()) {
                System.out.println(rs.getString("FKCOLUMN_NAME"));

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    // Ejercicio 10

    public static void obtenerDatos() {
        try (Statement st = conexion.createStatement()) {
            String consulta = "select *, nombre as non from alumnos";
            ResultSet rs = st.executeQuery(consulta);
            ResultSetMetaData rsdm = rs.getMetaData();
            for (int i = 0; i < rsdm.getColumnCount(); i++) {

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    // Ejercicio 12_1

    public static void ejercicio12_1() {
        try {
            conexion.setAutoCommit(false);
            Statement st = conexion.createStatement();
            st.executeUpdate(
                    "INSERT INTO alumnos (nombre, apellidos, altura, curso) VALUES ('Denis', 'Alonso Rodriguez', 175, 2)");
            System.out.println("Inserción relizada correctamente");
            conexion.commit();
            System.out.println("Commit realizado");
        } catch (SQLException e) {
            System.out.println("Error en una consulta: " + e.getLocalizedMessage());
            try {
                if (conexion != null) {
                    System.out.println("Se ha producido un error");
                    conexion.rollback();
                }
            } catch (SQLException i) {
                System.out.println("Error en el rollback: " + i.getLocalizedMessage());
            }
        }
    }

    // Ejercicio 12_2

    public static void ejercicio12_2() {
        try {
            conexion.setAutoCommit(false);
            Statement st = conexion.createStatement();
            st.executeUpdate(
                    "INSERT INTO alumnos (nombre, apellidos, altura, curso) VALUES ('Denis', 'Alonso Rodriguez', 175, 2)");
            System.out.println("Inserción relizada");
            conexion.commit();
            System.out.println("Commit realizado");
            st.close();
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException i) {
                System.out.println("Error RollBack");
            }
        }
    }

    // Ejercicio 15_1

    public static void ejercicio15_1() {
        try {
            int numeroAula = 0;
            String nombreAula = "";
            int puestos = 0;
            CallableStatement cs = conexion.prepareCall("CALL getAulas(?,?)");
            cs.setInt(1, 10);
            cs.setString(2, "o");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                numeroAula = rs.getInt("numero");
                nombreAula = rs.getString("nombreAula");
                puestos = rs.getInt("puestos");
                System.out.println("Numero: " + numeroAula + " Nombre " + nombreAula + " Puestos " + puestos);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    // Ejercicio 15_2

    public static void ejercicio15_2() {
        try {
            CallableStatement cs = conexion.prepareCall("CALL SUMA()");
            if (cs.execute()) {
                int resultado = cs.getInt(1);
                System.out.println("Resultado:" + resultado);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL");
        }
    }

    // Ejercicio 16

    public static void ejercicio16(String textoBuscado, String bd) {
        try {
            DatabaseMetaData dbmd = conexion.getMetaData();
            ResultSet tablas = dbmd.getTables(bd, null, "%", new String[] { "TABLE" });
            while (tablas.next()) {
                String nombreTabla = tablas.getString("TABLE_NAME");
                ResultSet columnas = dbmd.getColumns(bd, null, nombreTabla, "%");
                while (columnas.next()) {
                    String nombreColumna = columnas.getString("COLUMN_NAME");
                    String tipo = columnas.getString("TYPE_NAME");
                    if (tipo.equalsIgnoreCase("CHAR") || tipo.equalsIgnoreCase("VARCHAR")) {
                        String sql = "SELECT " + nombreColumna + " FROM " + nombreTabla + " WHERE " + nombreColumna
                                + " LIKE ?";
                        PreparedStatement ps = conexion.prepareStatement(sql);
                        ps.setString(1, "%" + textoBuscado + "%");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String valor = rs.getString(1);
                            System.out.println("BD: " + bd + " | Tabla: " + nombreTabla + " | Columna: " + nombreColumna
                                    + " | Valor: " + valor);
                        }
                        rs.close();
                        ps.close();
                    }
                }
                columnas.close();
            }
            tablas.close();
        } catch (SQLException e) {
            System.out.println("Error SQL");
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
        abrirConexion("add", "localhost", "root", "");
        // consultarAlumnos("a");
        // altaAlumnos( "lucas", "gonzalez", 185, 21);
        // AltaAsignaturas("furbo");
        // bajaAlumnos(10);
        // bajaAsignaturas(9);
        // modificarAlumnos("Denis",2);
        // modificarAsignaturas("mates",3);
        // nombreAulasAlumnos();
        // AlumnosAsignaturasNotas();
        // AsignaturasVacias();
        // consultar("%a%", 175);
        // consultar2("%a%", 175);
        // Datos();
        // catalogos();
        // nombre_Tipo("add");
        // vistas("add");
        // combinarBC("add");
        // procedimientosAlmacenados("add");
        // datosTablas("add");
        // clavesPrimarias("add");
        // clavesForaneas("add");
        // insertarColumna("prueba_mvc", "Aura", "int", "");
        // ejercicio12_1();
        // ejercicio12_2();
        // ejercicio15_1();
        // ejercicio15_2();

        cerrarConexion();
    }
}
