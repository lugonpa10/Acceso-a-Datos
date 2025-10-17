import java.io.*;
import java.util.*;

public class Ejercicio6 {

    // 1. Dividir por número de caracteres
    public static void dividirPorCaracteres(File origen, int n) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(origen))) {
            char[] buffer = new char[n];
            int leido;
            int contador = 1;

            while ((leido = br.read(buffer)) != -1) {
                File parte = new File("parte_chars_" + contador + ".txt");
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(parte))) {
                    bw.write(buffer, 0, leido);
                }
                contador++;
            }
        }
    }

    // 2. Dividir por número de líneas
    public static void dividirPorLineas(File origen, int l) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(origen))) {
            String linea;
            int contadorLineas = 0;
            int contadorFicheros = 1;
            BufferedWriter bw = new BufferedWriter(new FileWriter("parte_lineas_" + contadorFicheros + ".txt"));

            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
                contadorLineas++;

                if (contadorLineas == l) {
                    bw.close(); // cerramos el actual
                    contadorFicheros++;
                    contadorLineas = 0;
                    bw = new BufferedWriter(new FileWriter("parte_lineas_" + contadorFicheros + ".txt"));

                }
            }
            bw.close(); // cerrar el último fichero si quedó abierto
        }
    }

    // 3. Unir una lista de ficheros en uno solo
    public static void unirFicheros(List<File> lista, File destino) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destino))) {
            for (File f : lista) {
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        bw.write(linea);
                        bw.newLine();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File ficheroOriginal = new File("texto.txt"); 

    
        dividirPorCaracteres(ficheroOriginal, 5);

        dividirPorLineas(ficheroOriginal, 3);

 
        List<File> partes = Arrays.asList(
                new File("parte_lineas_1.txt"),
                new File("parte_lineas_2.txt"),
                new File("parte_lineas_3.txt"));
        unirFicheros(partes, new File("fichero_unido.txt"));
    }
}
