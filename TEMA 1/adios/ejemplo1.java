package hola;

import java.io.File;

public class ejemplo1 {
    public static void main(String[] args) {
        File carpeta = new File("hola");
        File carpeta2 = new File("adios");
        // System.out.println(carpeta.getName());
        // System.out.println(carpeta.getPath());
        // System.out.println(carpeta.getAbsolutePath());
        // System.out.println(carpeta.exists());
        // System.out.println(carpeta.mkdir());
        System.out.println(carpeta.renameTo(carpeta2));

    }

}