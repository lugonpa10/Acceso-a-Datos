package hola;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//FLUJOS DE DATOS

//ENTRADA (leer)
//Caracteres       FileReader fr = new Filereader(ruta)
                //fr.close()
                    
                //FileWriter fw = new FileWriter(ruta)

//Cadenas

public class Ejemplo2 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("Prueba.txt")) {

            int i;
            while ((i = fr.read()) != -1) { // el indice i guarda el caracter leido hasta que de -1 (llegar al final)
                System.out.print((char)i);
            }

        

        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
