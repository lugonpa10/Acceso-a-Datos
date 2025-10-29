package ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
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

    public static JsonValue ejercicio1() {
        String ciudad = "ourense";
        JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?q=" + ciudad
                + ",es&lang=es&+units=metric&APPID=8f8dccaf02657071004202f05c1fdce0");
        return j;
    }

    public static JsonValue ejercicio2(double lat, double lon) {
        JsonValue j = leeJSON("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
                + "&APPID=8f8dccaf02657071004202f05c1fdce0");
        return j;
    }

    public static JsonValue ejercicio3(double lat, double lon, int x) {
        JsonValue j = leeJSON("http://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=" + x
                + "&APPID=a975f935caf274ab016f4308ffa23453");
        return j;
    }

    public static JsonValue ejercicio4(String nombre) {

        JsonValue j = leeJSON("http://api.openweathermap.org/data/2.5/weather?q=" + nombre
                + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");

        System.out.println(j.asJsonObject().getInt("id"));

        return j;
    }

    public static JsonValue ejercicio5(int id) {

        JsonValue j = leeJSON("http://api.openweathermap.org/data/2.5/weather?id=" + id
                + "&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");

        System.out.println(j.asJsonObject().getString("name"));

        return j;
    }

    public static JsonValue ejercicio6(String nombre) {
        JsonValue j = leeJSON("http://api.openweathermap.org/data/2.5/weather?q=" + nombre
                + ",es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");

        JsonObject raiz = j.asJsonObject();

        JsonObject coordenadas = raiz.getJsonObject("coord");

        System.out.println("Long: " + coordenadas.getJsonNumber("lon") + "Lat: " + coordenadas.getJsonNumber("lat"));

        return j;
    }

    public static JsonValue ejercicio7() {
        JsonValue j = leeJSON(
                "http://api.openweathermap.org/data/2.5/weather?q=vigo,es&lang=es&APPID=8f8dccaf02657071004202f05c1fdce0");

        JsonObject raiz = j.asJsonObject();

        JsonObject main = raiz.getJsonObject("main");

        System.out.println("Temperatura: " + main.getJsonNumber("temp") + " Humedad: " + main.getJsonNumber("humidity"));

        JsonObject clouds = raiz.getJsonObject("clouds");

        System.out.println("Probabilidad de nubes : " + clouds.getJsonNumber("all"));

        JsonObject wind = raiz.getJsonObject("wind");

        System.out.println("velocidad viento: " + wind.getJsonNumber("speed"));

        JsonArray weather = raiz.getJsonArray("weather");

        System.out.println("Descripcion tiempo: " + weather.getJsonObject(0).getString("description"));


        long dt = raiz.getJsonNumber("dt").longValue();

        System.out.println(unixTimeToString(dt));

        return j;
    }

    public static JsonValue ejercicio8(){
        
    }

    public static String unixTimeToString(long unixTime) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Instant.ofEpochSecond(unixTime).atZone(ZoneId.of("GMT+1")).format(formatter);
    }

    public static void main(String[] args) throws FileNotFoundException {
        JsonValue j1, j2, j3;

        System.out.println("--------------------Ejercicio 1--------------------------");
        j1 = ejercicio1();
        System.out.println(j1);

        System.out.println("--------------------Ejercicio 2--------------------------");
        j2 = ejercicio2(42.232819, -8.72264);
        System.out.println(j2);

        System.out.println("--------------------Ejercicio 3--------------------------");
        j3 = ejercicio3(42.232819, -8.72264, 2);
        System.out.println(j3);

        System.out.println("--------------------Ejercicio 4--------------------------");
        ejercicio4("barcelona");

        System.out.println("--------------------Ejercicio 5--------------------------");
        ejercicio5(3105976);

        System.out.println("--------------------Ejercicio 6--------------------------");
        ejercicio6("vigo");

        System.out.println("--------------------Ejercicio 7--------------------------");
        ejercicio7();



    }
}