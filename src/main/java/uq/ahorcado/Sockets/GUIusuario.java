package uq.ahorcado.Sockets;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uq.ahorcado.MainAhorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;


public class GUIusuario extends Application implements Observer {

    private Stage primaryStage;
    @FXML
    private Button bttEnviar;

    private Observable observable;
    private Object object;

    @FXML
    private Label lbPalabra;

    @FXML
    private TextField txtLetra;
    UsuarioHilo usuarioHilo;
    @FXML
    private Label lblTerminado;



    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        this.primaryStage=stage;
        this.primaryStage.setTitle("VENTA JUGADOR");
        this.mostrarVentanaPrincipal();
    }

    public String mostrar(UsuarioHilo usuarioHilo) throws InterruptedException {
        if (usuarioHilo.getPalabra()!=null){
            String pal=usuarioHilo.getPalabra();
            this.lbPalabra.setText(pal);
            return null;
        }else {
            return null;

        }

    }

    @FXML
    void initialize() throws InterruptedException {
        usuarioHilo=new UsuarioHilo();
        usuarioHilo.addObserver(this);
        new Thread(usuarioHilo).start();


    }

    @FXML
    private void enviarLetra(ActionEvent event) throws IOException {
        String letra=txtLetra.getText();
        char convertir=letra.charAt(0);
        usuarioHilo.enviar(convertir);



    }

    private void limpiar(){
       this.txtLetra.setText(" ");
    }










    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAhorcado.class.getResource("Usuario.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            GUIusuario selectViewController = (GUIusuario) loader.getController();
            Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException var5) {
            var5.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(()->{ this.lbPalabra.setText((String)arg);
        this.lblTerminado.setText(usuarioHilo.getMensaje());});
    }
}
