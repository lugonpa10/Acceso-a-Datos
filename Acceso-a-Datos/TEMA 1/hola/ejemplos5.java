package hola;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Leer un archivo

public class ejemplos5 {
    public static void main(String[] args) throws FileNotFoundException {


        try (Scanner sc = new Scanner(new File("Caracter.txt")))  {
            
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            
        } 
    }
}
