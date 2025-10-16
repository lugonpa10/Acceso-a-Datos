package hola;

import java.io.File;

public class ejemplo1 {
    public static void main(String[] args) {
        File carpeta = new File("hola");
        // File carpeta2 = new File("adios");
        // System.out.println(carpeta.getName());
        // System.out.println(carpeta.getPath());
        // System.out.println(carpeta.getAbsolutePath());
        // System.out.println(carpeta.exists());
        // System.out.println(carpeta.mkdir());
        // System.out.println(carpeta.renameTo(carpeta2));
        // System.out.println(carpeta2.isFile());
        // System.out.println(carpeta2.isDirectory());
        File array[] = carpeta.listFiles();
        System.out.println("Archivo:");

        for (int i = 0; i < array.length; i++) {

            if (array[i].isFile()) {

                System.out.println(array[i].getName());

            }

        }

        
        System.out.println("\nCarpetas:");

        for (int i = 0; i < array.length; i++) {
            if (array[i].isDirectory()) {

                System.out.println(array[i].getName());

            }

        }

    }

}

