package hola;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa la ruta");
        String archivo = sc.nextLine();

        System.out.println("Ingresa la cadena");
        String cadena = sc.nextLine();

        try (Scanner sc2 = new Scanner(new File(archivo))) {

            int numLinea = 1;

            while (sc2.hasNextLine()) {
                String lineaActual = sc2.nextLine();

                if (lineaActual.contains(cadena)) {
                     System.out.println("La cadena: " + cadena + " aparece en la linea " + numLinea);
                }
                numLinea++;
                
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
