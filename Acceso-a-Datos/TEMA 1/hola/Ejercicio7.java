package hola;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el archivo");
        String archivo = sc.nextLine();

        System.out.println("Introduce la opcion");
        String opcion = sc.nextLine();

        realizarOperacion(archivo, opcion);

        sc.close();

    }

    public static void crearArchivo(String archivo, String nuevoNombre, ArrayList<String> lineas) {

        try (FileWriter fw = new FileWriter(nuevoNombre)) {

            for (String linea : lineas) {

                fw.write(linea);
                fw.write(System.lineSeparator());
            }

            System.out.println("Archivo: " + nuevoNombre + "creado a partir de " + archivo);

        } catch (IOException e) {
            // TODO: handle exception
        }

    }

    public static void realizarOperacion(String archivo, String opcion) {
        try (Scanner sc2 = new Scanner(new File(archivo))) {
            int numLineas = 0;
            int numPalabras = 0;
            ArrayList<String> lineas = new ArrayList<>();
            while (sc2.hasNextLine()) {
                String linea = sc2.nextLine();
                lineas.add(linea);

                numLineas++;
                String[] palabras = linea.split("\\W+");
                numPalabras += palabras.length;

            }

            switch (opcion) {
                case "n":
                    System.out.println("Numero de lineas: " + numLineas);
                    System.out.println("Numero paklabras" + numPalabras);
                    break;
                case "A":
                    Collections.sort(lineas);

                    crearArchivo(archivo, "ascendenteSensible.txt", lineas);
                    break;
                case "D":
                    Collections.sort(lineas, Collections.reverseOrder());

                    crearArchivo(archivo, "descendenteSensible.txt", lineas);

                    break;
                case "a":

                    Collections.sort(lineas, String.CASE_INSENSITIVE_ORDER);

                    crearArchivo(archivo, "ascendenteSensible.txt", lineas);
                    break;
                case "d":
                    Collections.sort(lineas, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));

                    crearArchivo(archivo, "ascendenteSensible.txt", lineas);
                    break;

                default:
                    break;

            }

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

}

// OPCION A = Collections.sort(lineas)
// OPCION D = " ".(lineas,Collections.ReverseOrder())
// OPCION a = Collections.sort(lineas,String.CASE_INSENSITIVE_ORDER)
// OPCION d =
// Collections.sort(lineas,Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER))
