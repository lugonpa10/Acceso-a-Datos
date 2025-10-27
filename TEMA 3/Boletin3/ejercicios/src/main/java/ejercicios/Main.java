package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.net.ssl.HttpsURLConnection;

public class Main {

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

    public static void ejercicio1() {
        Scanner sc = new Scanner(System.in);
        String localidad;

        System.out.println("Escribe una localidad");
        localidad = sc.nextLine();

        JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?q=" + localidad
                + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");
        System.out.println(j);

    }
https://prod.liveshare.vsengsaas.visualstudio.com/join?6E16D2CC3EE022143F513A4A3517CB76F4F4
    public static void ejercicio2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime una longitud");
        float lon = sc.nextFloat();
        System.out.println("");
        float lat = sc.nextFloat();

        JsonValue j = leeJSON(
                "http://api.openweathermap.org/data/2.5/weather?lat=42.232819&lon=-8.72264&APPID=8f8dccaf02657071004202f05c1fdce0");
    }

    public static void main(String[] args) {
        ejercicio1();
    }
}