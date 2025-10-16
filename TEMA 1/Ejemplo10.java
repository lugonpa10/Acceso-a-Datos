import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejemplo10 {
    public static void main(String[] args)  throws IOException { // leer tipos primitivos
        try(DataInputStream din = new DataInputStream(new FileInputStream("clientes.dat"))) {

            while (true) {
                System.out.println("NOMBRE: " + din.readUTF() + "numero de compras: " +  din.readInt()+ "CREDITO " + din.readFloat() );
            }
            
        } catch (EOFException e) {
          System.out.println("FIN FICHERO");
        }
    }
}
