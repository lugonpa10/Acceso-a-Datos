
import java.io.*;
import java.util.*;

public class Ejercicio9 {
    static Scanner sc = new Scanner(System.in);

    public static class Alumno {
        int codigo;
        String nombre;
        double altura;

        public Alumno(int codigo, String nombre, double altura) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.altura = altura;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int opcion;
        do {
            System.out.println("\n--- MENÚ DE ALUMNOS ---");
            System.out.println("1. Dar de alta alumno");
            System.out.println("2. Consultar alumno");
            System.out.println("3. Modificar alumno");
            System.out.println("4. Borrar alumno");
            System.out.println("5. Listar alumnos");
            System.out.println("6. Salir");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> altaAlumno();
                case 2 -> consultarAlumno();
                case 3 -> modificarAlumno();
                case 4 -> borrarAlumno();
                case 5 -> listarAlumnos();
                case 6 -> System.out.println("Salir");
                default -> System.out.println("Opción no válida");
            }
        } while (opcion != 6);
    }

    public static void altaAlumno() throws FileNotFoundException, IOException {
        System.out.print("Código: ");
        int codigo = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Altura: ");
        double altura = sc.nextDouble();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("alumnos.dat"))) {
            dos.writeInt(codigo);
            dos.writeUTF(nombre);
            dos.writeDouble(altura);
            System.out.println("Alumno añadido");
        } catch (EOFException e) {
            System.out.println("Error al añadir alumno");
        }
    }

    public static void consultarAlumno() throws FileNotFoundException, IOException {
        System.out.print("Código a consultar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        try (DataInputStream dis = new DataInputStream(new FileInputStream("alumnos.dat"))) {
            boolean encontrado = false;
            while (true) {
                int cod = dis.readInt();
                String nombre = dis.readUTF();
                double altura = dis.readDouble();
                if (cod == codigo) {
                    System.out.printf("Alumno -> Código: %d, Nombre: %s, Altura: %.2f%n", cod, nombre, altura);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado)
                System.out.println("Alumno no encontrado");
        } catch (EOFException e) { 

        }
    }

    public static void modificarAlumno() throws FileNotFoundException, IOException {
        System.out.print("Código a modificar: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        List<Alumno> alumnos = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(new FileInputStream("alumnos.dat"))) {
            while (true) { 
                int cod = dis.readInt(); 
                String nombre = dis.readUTF();
                double altura = dis.readDouble();
                alumnos.add(new Alumno(cod, nombre, altura));
            }
        } catch (EOFException e) { 
            System.out.println("Error al leer alumnos");
          
        }

        boolean encontrado = false;
        for (Alumno a : alumnos) {
            if (a.codigo == codigo) {

                encontrado = true;

                System.out.print("Nuevo nombre: ");
                a.nombre = sc.nextLine();
                System.out.print("Nueva altura: ");
                a.altura = sc.nextDouble();
                sc.nextLine();
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Alumno no encontrado");
            return;
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("alumnos.dat"))) {
            for (Alumno a : alumnos) {
                dos.writeInt(a.codigo);
                dos.writeUTF(a.nombre);
                dos.writeDouble(a.altura);
            }
            System.out.println(" Alumno modificado");
        } catch (IOException e) {
            System.out.println("Error al modificar alumno");

        }
    }

    public static void borrarAlumno() throws FileNotFoundException, IOException {
        System.out.print("Código a borrar: ");
        int codigo = sc.nextInt(); 
        List<Alumno> alumnos = new ArrayList<>();

        try (DataInputStream dis = new DataInputStream(new FileInputStream("alumnos.dat"))) {
            while (true) {
                int cod = dis.readInt();
                String nombre = dis.readUTF();
                double altura = dis.readDouble();
                if (cod != codigo)
                    alumnos.add(new Alumno(cod, nombre, altura));
            }
        } catch (EOFException e) { 

       
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("alumnos.dat"))) {
            for (Alumno a : alumnos) {
                dos.writeInt(a.codigo);
                dos.writeUTF(a.nombre);
                dos.writeDouble(a.altura);
            }
            System.out.println(" Alumno borrado");
        } catch (EOFException e) { 

        }
    }

    public static void listarAlumnos() throws FileNotFoundException, IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("alumnos.dat"))) {
            System.out.println("\n--- LISTA DE ALUMNOS ---");
            while (true) {
                int cod = dis.readInt();
                String nombre = dis.readUTF();
                double altura = dis.readDouble();
                System.out.printf("Código: %d, Nombre: %s, Altura: %.2f%n", cod, nombre, altura);
            }
        } catch (EOFException e) {
            System.out.println("Error al listar alumnos");
        }
    }
}
