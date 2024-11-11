package uq.ahorcado.Sockets;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uq.ahorcado.MainAhorcado;

import java.io.IOException;
import java.util.ArrayList;

public class GUIServer extends Application {
    public static ArrayList<String> palabras;

    private static int numero;

    Server server;

    @FXML
    void initialize() throws IOException {
        server=new Server();
    }

    private Stage primaryStage;
    public void start(Stage stage) throws IOException {
      palabras= new ArrayList<>();
        palabras.add("programacion");
        palabras.add("codigo");
        this.primaryStage = stage;
        this.primaryStage.setTitle("Ventana Principal");
        this.mostrarVentanaPrincipal();
        server=new Server();
        new Thread(server).start();
    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAhorcado.class.getResource("interfazServidor.fxml"));
            AnchorPane rootLayout = (AnchorPane)loader.load();
            GUIServer selectViewController = (GUIServer) loader.getController();
            Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    @FXML
    private void tomarPalabra(ActionEvent event) throws IOException {
        System.out.println("ya");
        numero = generarPalabra();
        System.out.println(numero);
        server.palabra(numero,palabras);
    }
    public int generarPalabra(){
            numero = (int) (Math.random()*palabras.size());
        return numero;
    }





}
