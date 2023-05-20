module com.example.progettoambulatoriojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progettoambulatoriojavafx to javafx.fxml;
    exports com.example.progettoambulatoriojavafx;
}