import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ejemplo14 {
    public static void main(String[] args) throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream("clientes.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {

            Persona persona;

            while (true) {
               Object object = ois.readObject();   


               if (Object.getClass() == Persona.class){
                persona = (Persona) object;
                                System.out.println(persona.getNombre() + " - " + persona.getEdad());

               }
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}