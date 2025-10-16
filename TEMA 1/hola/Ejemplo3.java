package hola;

import java.io.FileReader;
import java.io.IOException;

public class Ejemplo3 {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("Prueba.txt")) {
          
            char buffer[] = new char[6];
            int i;
            while ((i = fr.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, i));

            }

        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
