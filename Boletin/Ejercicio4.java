import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio4 {

    public static void main(String[] args) {
        File fichero = new File("texto.txt"); 
        caracterMasUsado(fichero);
    }

    
    public static void caracterMasUsado(File fichero) {
   
        int[] contador = new int[256];

        try (FileReader fr = new FileReader(fichero)) {
            int c;
            while ((c = fr.read()) != -1) {
                if (c < 256) { 
                    contador[c]++;
                }
            }

            
            int max = 0;
            int posicion = 0;

            for (int i = 0; i < contador.length; i++) {
                if (contador[i] > max) {
                    max = contador[i];
                    posicion = i;
                }
            }

            if (max == 0) {
                System.out.println("El fichero está vacío o no contiene caracteres válidos.");
            } else {
                System.out.println("El carácter más usado es '" + (char)posicion + 
                                   "' con " + max + " apariciones.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
