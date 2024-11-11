package uq.ahorcado.Sockets;

import javafx.beans.InvalidationListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

public class UsuarioHilo extends Observable implements Runnable  {


    private DataOutputStream out;

    boolean formada=true;

    String palabra;
    Socket user;
    String mensaje=" ";

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
public UsuarioHilo(Socket user){
        this.user=user;
}
    public String getPalabra() {
        return palabra;
    }


    public UsuarioHilo(DataInputStream in, DataOutputStream out) {

        this.out = out;
    }

    public UsuarioHilo(){
    }

    @Override
    public void run(){
        try {
            user = new Socket("localHost", 5000);
            DataInputStream sv = new DataInputStream(user.getInputStream());
            DataOutputStream out = new DataOutputStream(user.getOutputStream());
            System.out.println(sv.readUTF());
            while (formada) {
                palabra = sv.readUTF();
                System.out.println(palabra);
                this.setChanged();
                this.notifyObservers(palabra);
                this.clearChanged();
                formada=sv.readBoolean();
                if(!formada){
                     mensaje=sv.readUTF();
                     sv.close();
                     out.close();
                     user.close();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
    }


    }

    public void enviar(char letra) throws IOException {
        out=new DataOutputStream(user.getOutputStream());
        out.writeChar(letra);
    }



}
