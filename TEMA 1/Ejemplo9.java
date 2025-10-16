import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Ejemplo9 {

    public static void main(String[] args) { // Escribir datos primitivos
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("clientes.dat"))) {
            ArrayList<Cliente> clientes = new ArrayList<>();
            clientes.add(new Cliente("Lugonpa", 3, 33.3f)); // f = float
            for (Cliente cliente : clientes) {
                dout.writeUTF(cliente.getNombre()); // con utf se escribe string
                dout.writeInt(cliente.getNumCompras());
                dout.writeFloat(cliente.getCredito());
            }


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
