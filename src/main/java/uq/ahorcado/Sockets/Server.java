package uq.ahorcado.Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server extends Thread{

    ArrayList<String>palabras;
    public static ArrayList<Socket>jugadores;
   private boolean encontrado=true;

   private  char[]formada;

   private String palabra="";

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getPalabra() {
        return palabra;
    }
    public Server(){

    }


    DataInputStream in;
    DataOutputStream out;

   public Server(char[] formada) {
       this.formada = new char[palabra.length()];
       palabras=new ArrayList<>();
       for(int i=0;i<formada.length;i++){
           formada[i]='*';
       }
   }
    public String palabra(int numero,ArrayList<String>palabras)  {
        for(int i=0;i<jugadores.size();i++){
            try {
                out=new DataOutputStream(jugadores.get(i).getOutputStream());
                in=new DataInputStream(jugadores.get(i).getInputStream());
               palabra=palabras.get(numero);
               setPalabra(palabra);
                String cambio=reemplar(palabra);
                out.writeUTF(cambio);
                int j=0;
                formada=new char[palabra.length()];
               formada=forma(formada);
                while (encontrado){
                    out.writeBoolean(true);
                    if(j<palabra.length()){
                    char letra= in.readChar();
                    boolean ya=resuelto(letra,palabra);
                    if (ya) {
                        String observar = new String(formada);
                        System.out.println(observar);
                        out.writeUTF(observar);
                        j++;
                       if(observar.equals(palabra)) {
                           encontrado = false;
                           out.writeBoolean(false);
                           String mensaje="Palabra hallada";
                           out.writeUTF(mensaje);
                           System.out.println("Encontrada");
                       }
                    }
                    }

                }


            }catch (IOException e){
                System.out.println("FALLA");
            }
        }

        return palabra;
    }

    public void run(){
        try {
            ServerSocket sc = new ServerSocket(5000);
            System.out.println("Servidor iniciado");
            jugadores=new ArrayList<Socket>();

            while (true) {
                Socket usuario=sc.accept();
                jugadores.add(usuario);
                DataInputStream in = new DataInputStream(jugadores.getLast().getInputStream());
                out = new DataOutputStream(usuario.getOutputStream());
                out.writeUTF("Usuario Conectado");
            }
        }catch (IOException i){
            i.printStackTrace();
        }

    }
    public String reemplar(String palabra){
        String cambio=" ";
        for(int i=0;i<palabra.length();i++){
            cambio+=palabra.replace(palabra,"*");

        }
        return cambio;
    }

    public boolean resuelto(char letra,String pa){
       boolean re=false;
       pa=palabra;
       char e;
       for(int i=0;i<palabra.length();i++){
           e=palabra.charAt(i);
           if (e==letra && formada[i]=='*'){
               formada[i]=letra;
           }else {
               pa=pa.replace(e,'*');

           }

       }
        return re=true;
    }

    public char[]forma (char[]forma){
       for (int i=0;i<forma.length;i++){
           forma[i]='*';
       }
       return forma;

    }

    }







