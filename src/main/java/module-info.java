module uq.ahorcado {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens uq.ahorcado to javafx.fxml;
    exports uq.ahorcado;
    exports uq.ahorcado.Sockets;
    opens uq.ahorcado.Sockets to javafx.fxml;

}