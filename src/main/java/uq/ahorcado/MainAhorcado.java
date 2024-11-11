package uq.ahorcado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uq.ahorcado.Sockets.GUIServer;


import java.io.IOException;

public class MainAhorcado extends Application {

    private Stage primaryStage;

    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Ventana Principal");
        this.mostrarVentanaPrincipal();
    }

    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAhorcado.class.getResource("Ahorcado.fxml"));
            AnchorPane rootLayout = (AnchorPane)loader.load();
            GUIServer selectViewController = (GUIServer) loader.getController();
            Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            GUIServer server =new GUIServer();

        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}

