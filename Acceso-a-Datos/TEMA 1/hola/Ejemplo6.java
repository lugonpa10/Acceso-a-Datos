package hola;

import java.io.IOException;
import java.io.PrintWriter;

public class Ejemplo6 {
    public static void main(String[] args) {
        try (PrintWriter pw = new PrintWriter("archivo2.txt")) {
            pw.println("alonso 33");
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
