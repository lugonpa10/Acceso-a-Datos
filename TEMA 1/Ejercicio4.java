import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio4 {
    public static void main(String[] args) {

        try (FileReader fr = new FileReader("archivo.txt")) {

            int frecuencias[] = new int[256];
            int i;
            while ((i = fr.read()) !=-1) {
                frecuencias[i]++;
                System.out.println(i);

                for ( i = 0; i<=frecuencias) {
                    
                }
            }

        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
