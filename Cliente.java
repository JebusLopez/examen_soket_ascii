import CadenaMayusMinus.*;
import java.awt.Frame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Cliente {

    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PORT = 5000;
        DataInputStream in;
        DataOutputStream out;
        
        try {
            //Socket para conectar con el servidor
            Socket sc = null; 
            int opc;
            sc = new Socket(HOST, PORT);
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            Scanner entrada = new Scanner(System.in);
            String data = "";
            System.out.println("CONTADOR ASCII DE CADENAS/ 'salir' para finalizar");
            
            do{
                System.out.println("Ingresa la cadena: ");
                data = entrada.nextLine();
                out.writeUTF(data);
                String mensaje = in.readUTF();
                System.out.println("Total del contador ASCII: " + mensaje+"\n");
            }while(data != "salir");
            
            out.close();
            in.close();
            sc.close();
            
        }catch (IOException ex) {
            Logger.getLogger(CadenaMayusMinus.Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
