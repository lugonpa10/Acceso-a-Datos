package hola;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejemplo7 {

    public static void main(String[] args) { // leer y escribir
        try (FileInputStream fin = new FileInputStream("archivo.txt");
        FileOutputStream fout = new FileOutputStream("fichero.dat",true)) { // .dat porque es binario, el true es para no sobreescribir
            int i;
            while ((i = fin.read()) != -1) {
             fout.write(i);   

             System.out.println((char) i);
            }
            
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
    
}
