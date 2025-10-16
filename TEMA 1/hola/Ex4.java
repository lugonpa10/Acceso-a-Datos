

import java.io.FileReader;
import java.io.IOException;

public class Ex4 {
    public static void main(String[] args) {

        try (FileReader fr = new FileReader("denis.txt")){
            
            int[] caracteres = new int[256];
            int max = 0;
 
            int i;
            while ((i = fr.read()) != -1) {
                if (i != 10 && i != 32){
                    caracteres[i]++;
                }
                
            }
                for (int j = 0; j < caracteres.length; j++) {
                    if (caracteres[j] > max) {
                        max = j;
                    } 
                }
            
            System.out.print("El caracter que más aparece es: " + (char)max);
            
        } catch (IOException e) {
            // TODO: handle exception
        }
        
    }
}