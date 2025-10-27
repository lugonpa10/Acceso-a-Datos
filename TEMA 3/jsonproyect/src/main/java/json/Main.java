package json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;
import javax.net.ssl.HttpsURLConnection;

public class Main {
    
    public static JsonValue leeJSON(String ruta) {
        try {
            if (ruta.toLowerCase().startsWith("http://")) {
                return leerHttp(ruta);
            } else if (ruta.toLowerCase().startsWith("https://")) {
                return leerHttps(ruta);
            } else {
                return leerFichero(ruta);
            }
        } catch (IOException e) {
            System.out.println("Error procesando documento Json " + e.getLocalizedMessage());
            return null;
        }
    }
    
    public static void ejercicio1() {
        Scanner sc = new Scanner(System.in);
        String localidad;

        System.out.println("Escribe una localidad");
        localidad = sc.nextLine();

      JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?q=" + localidad +",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");
      System.out.println(j);

    }

    public static JsonValue leerFichero(String ruta) throws FileNotFoundException {
        try (JsonReader reader = Json.createReader(new FileReader(ruta))) {
            return reader.read();
            /*
             * JsonStructure jsonSt = reader.read();
             * System.out.println(jsonSt.getValueType());
             * JsonObject jsonObj = reader.readObject();
             * System.out.println(jsonObj.getValueType());
             * JsonArray jsonArr = reader.readArray();
             * System.out.println(jsonArr.getValueType());
             */
        }
    }

    public static JsonValue leerHttp(String direccion) throws IOException {
        URL url = new URL(direccion);
        try (InputStream is = url.openStream();
                JsonReader reader = Json.createReader(is)) {
            return reader.read();
        }
    }

    public static JsonValue leerHttps(String direccion) throws IOException {
        URL url = new URL(direccion);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        try (InputStream is = conn.getInputStream();
                JsonReader reader = Json.createReader(is)) {
            return reader.read();
        } finally {
            conn.disconnect();
        }
    }

    public static void escribeJSON(JsonValue json, File f) throws FileNotFoundException {
        System.out.println("Guardando tipo: " + json.getValueType());
        PrintWriter pw = new PrintWriter(f);
        JsonWriter writer = Json.createWriter(pw);

        if (json.getValueType() == JsonValue.ValueType.OBJECT) {
            writer.writeObject(json.asJsonObject());
        } else if (json.getValueType() == JsonValue.ValueType.ARRAY) {
            writer.writeArray(json.asJsonArray());
        } else {
            System.out.println("No se soporta la escritura");
        }

        writer.close();
    }

    public static void navegarPelis() {
        JsonValue j = leeJSON("src/main/resources/pelis.json");
        // JsonValue j =
        // leeJSON("C:/Users/lucas/Desktop/Repositorios/Acceso-a-Datos/TEMA
        // 3/jsonproyect/src/main/resources/pelis.json");
        System.out.println(j);

        JsonArray raiz = j.asJsonArray();
        // System.out.println(raiz.size());

        for (JsonValue peli : raiz) {
            JsonObject p = peli.asJsonObject();
            System.out.println("Título: " + p.getString("titulo") + " - Año: " + p.getInt("año"));

            JsonArray interpretes = p.getJsonArray("interpretes");
            System.out.println("Intérpretes:");

            for (JsonValue interprete : interpretes) {
                JsonObject inter = interprete.asJsonObject();
                System.out.println(inter.getString("nombre"));
                System.out.printf(
                        "Fecha de nacimiento: año - %d, mes - %d\n",
                        inter.getJsonObject("fechaNacimiento").getInt("año"),
                        inter.getJsonObject("fechaNacimiento").getInt("mes"));
            }
        }
    }

    public static JsonArray creaArray() {
        JsonArray array = (JsonArray) Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("titulo", "El atlas de las nubes")
                        .add("año", 2012)
                        .add("directores", "Lana Wachowski, Tom Tykwer, Lilly Wachowski")
                        .add("interpretes", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("nombre", "Tom Hanks")
                                        .add("fechaNacimiento", Json.createObjectBuilder()
                                                .add("año", 1956)
                                                .add("mes", 8)))
                                .add(Json.createObjectBuilder()
                                        .add("nombre", "Halle Berry")
                                        .add("fechaNacimiento", Json.createObjectBuilder()
                                                .add("año", 1966)
                                                .add("mes", 7)))))
                .add(Json.createObjectBuilder()
                        .add("titulo", "La red social")
                        .add("año", 2010)
                        .add("directores", "David Fincher")
                        .add("interpretes", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("nombre", "Jesse Eisenberg")
                                        .add("fechaNacimiento", Json.createObjectBuilder()
                                                .add("año", 1983)
                                                .add("mes", 9)))
                                .add(Json.createObjectBuilder()
                                        .add("nombre", "Andrew Garfield")
                                        .add("fechaNacimiento", Json.createObjectBuilder()
                                                .add("año", 1983)
                                                .add("mes", 7)))))
                .build();
        return array;
    }

    public static void generaEndisco(File f) throws FileNotFoundException {
        JsonGenerator generator = Json.createGenerator(new FileOutputStream(f));
        generator.writeStartArray()
                .writeStartObject()
                .write("titulo", "El atlas de las nubes")
                .write("año", 2012)
                .write("directores", "Lana Wachowski, Lilly Wachowski")
                .writeStartArray("intepretes")
                .writeStartObject()
                .write("nombre", "Tom Hanks")
                .writeStartObject("fechaNacimiento")
                .write("año", "1956")
                .write("mes", 8)
                .writeEnd()
                .writeEnd()
                .writeStartObject()
                .write("nombre", "Halle Berry")
                .writeStartObject("fechaNacimiento")
                .write("año", "1966")
                .write("mes", 7)
                .writeEnd()
                .writeEnd()
                .writeEnd()
                .writeEnd()
                .writeEnd()
                .close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        // JsonValue json = leeJSON("https://pokeapi.co/api/v2/pokemon/ditto");
        // escribeJSON(json, new File("jsonproyect/src/main/resources/ditto.json"));
        // navegarPelis();
        // generaEndisco(new File("src/main/resources/pelisgenerado.json"));
        ejercicio1();
    }
}
