package hola;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Para escribir en un archivo, no hace falta que exista.

public class ejemplo4 {
    public static void main(String[] args) {
        String cad = "\nCelta 3-1 Girona";

        try (FileWriter fw = new FileWriter("archivo.txt", true)) { // si pones el true se vuelve a sobreescribir

            fw.write(cad);
            fw.write(System.lineSeparator());// otra forma del cambio de linea

        } catch (IOException e) {

        }
    }

}
