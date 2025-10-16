package hola;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        char cad = 'l';
        int cont = 0;
        try (FileReader fr = new FileReader("Caracter.txt")) {

            int i;
            while ((i = fr.read()) != -1) {
                if (i == cad) {
                    cont++;
                }

            }
            System.out.println(cont);

        } catch (IOException e) {
        
        }

    }
}
