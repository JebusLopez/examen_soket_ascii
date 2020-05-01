import examen_practico.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class OperacionesServidor extends Thread{
    
    Socket sc;
    int contador, asciiCont;
    String data, mensaje;
    DataInputStream in;
    DataOutputStream out;
    
    OperacionesServidor(Socket sc, int contador) {
        this.sc = sc;
        this.contador = contador;
    }
    
    public void run(){
        try {
            convertir();
        } catch (IOException ex) {
            Logger.getLogger(OperacionesServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void convertir() throws IOException {
        System.out.println("Hilo de atencion numero: " + contador);
        System.out.println("Operaciones Iniciadas " + getName() + " " + sc.getInetAddress());
        in = new DataInputStream(sc.getInputStream());
        out = new DataOutputStream(sc.getOutputStream());
        
        do{
            data = in.readUTF();
            mensaje = "\nHilo de atencion: " + contador + "\n";
            for(int i =0; i<data.length(); i++){
                asciiCont += (int)data.charAt(i);
                mensaje += data.charAt(i) + " | " + (int)data.charAt(i)+ "\n";
            }
            out.writeUTF(String.valueOf(asciiCont));
            mensaje += "Total: " + asciiCont + "\n";
            System.out.println(mensaje);
            asciiCont = 0;
        }while(data != "salir");
    }
}
