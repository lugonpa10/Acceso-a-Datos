import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Ejemplo12 {

    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("alonso", 33, 999.9f));

        ArrayList<Persona> personas = new ArrayList<>();
            personas.add(new Persona("Iago",10));
        
        File fichero = new File ("clientes.dat");
        // escribirOjetos(fichero,clientes);
        escribirOjetos(fichero, personas);
    }


    public static void escribirOjetos(File fichero, ArrayList<Persona> clientes){
        FileOutputStream fos = null;
        ObjectOutput out = null;

        try {
            
            fos = new FileOutputStream(fichero);
            if (fichero.length() ==0) out = new ObjectOutputStream(fos);
            else out = new AppendingObjectOutputStream(fos);
                
            for (Persona cliente : clientes) {
                out.writeObject(cliente);
            }

             
            fos.close();
            out.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}