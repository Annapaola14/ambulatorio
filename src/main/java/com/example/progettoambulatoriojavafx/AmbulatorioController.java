package com.example.progettoambulatoriojavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Vector;

/*
* tutti i controlli del funzionamento dell aplicazione
*
*
* */

public class AmbulatorioController {

    private Vector<Paziente> listapazienti = new Vector<>();

    @FXML
    private AnchorPane registrazionePanel;
    @FXML
    private TextField nomeT;
    @FXML
    private TextField cognomeT;
    @FXML
    private TextField etaT;
    @FXML
    private TextField cfT;
    @FXML
    private TextArea patologiaT;

    // fassi che4 la lista dei pazienti sia disponibile in altre finestre

    public void inizializza(Vector<Paziente> pListaPazienti) {

        this.listapazienti = pListaPazienti;
    }

    //apertura della pagina di registrazione

    @FXML
    protected void Registrazione() throws IOException {

        FXMLLoader root = new FXMLLoader(getClass().getResource("AmbulatorioRegistrazione.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root.load(), 600, 600));
        stage.setTitle("Registrazione");
        //stage.getIcons().add(new Image("C:\\Users\\giuse\\Desktop\\gabriele\\informatica\\Progetto Ambulatorio JavaFX 2\\src\\main\\resources\\com\\example\\progettoambulatoriojavafx\\sfondo.png"));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        AmbulatorioController controller = root.getController();
        controller.inizializza(listapazienti);
        stage.show();
    }

    // ti chiede se vuoi uscire e salvare i dati con un apertura di un alert di conferma

    @FXML
    protected void esciRegistrazione() {
        Stage stage;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Esci dalla registrazione");
        alert.setHeaderText("Stai per uscire dalla finestra di registrazione");
        alert.setContentText("Vuoi salvare i dati inseriti prima di uscire?");

        if (alert.showAndWait().orElse(null) == ButtonType.OK) { //get() | .orElse(null) --> sono uguali, ma orElse gestisce i return null
            stage = (Stage) registrazionePanel.getScene().getWindow();
            salvaDati();
            stage.close();
        }
    }



// apre visualliza
    @FXML
    protected void visualizza() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AmbulatorioVisualizza.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(loader.load(), 600, 600));
        //stage.getIcons().add(new Image("C:\\Users\\giuse\\Desktop\\gabriele\\informatica\\Progetto Ambulatorio JavaFX 2\\src\\main\\resources\\com\\example\\progettoambulatoriojavafx\\sfondo.png"));
        stage.setTitle("Visualizzazione");
        stage.setResizable(false);
        VisualizzaController controller = loader.getController();
        controller.inizializza(listapazienti);
        stage.show();


        GestioneFilePazienti gfp = new GestioneFilePazienti();
        boolean esito = gfp.openInRead();
        Paziente paziente;
        do {
            paziente = gfp.leggiPaziente(); //fare un ciclo per leggerli tutti

            if (paziente != null) paziente.stampaPaziente();
        }while(paziente != null);


        gfp.closeFileInReading();
    }
// apre informazioni
    @FXML
    protected void chisiamo() throws IOException {

        FXMLLoader info = new FXMLLoader(getClass().getResource("AmbulatorioInfo.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(info.load(), 600, 400));
        stage.setTitle("Chi siamo");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.show();
    }
// salvataggio
    @FXML
    protected void salvaDati() {

        if (cognomeT.getText().isEmpty() || nomeT.getText().isEmpty() || cfT.getText().isEmpty() || patologiaT.getText().isEmpty() || etaT.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error");
            alert.setHeaderText("erorre di registrazione");
            alert.setContentText("Non tutti i campi sono stati compilati");
            alert.showAndWait();
        } else {
            int etaN = Integer.parseInt(etaT.getText());
            Paziente paziente = new Paziente(nomeT.getText(), cognomeT.getText(), patologiaT.getText(), cfT.getText(), etaN);
            listapazienti.add(paziente);

            GestioneFilePazienti gfp = new GestioneFilePazienti();
            boolean esito = gfp.openInWriting();
            gfp.registraPaziente(paziente);
            gfp.closeFileInWriting();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registrazione avvenuta con successo");
            alert.setContentText("Registrazione avvenuta con successo");
            alert.show();
            cognomeT.setText("");
            nomeT.setText("");
            patologiaT.setText("");
            etaT.setText("");
            cfT.setText("");
        }
    }
        @FXML
        protected void cancellaDati () {
            nomeT.setText("");
            cognomeT.setText("");
            etaT.setText("");
            patologiaT.setText("");
            cfT.setText("");
        }
// elimina il primo pazienta della lista
    @FXML
    protected void dottore() {

        if (listapazienti.size() != 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Entrata dal dottore");
            alert.setHeaderText("Stai per entrare nella stanza del dottore. In questo modo verrai cancellato dalla lista pazienti");
            alert.setContentText("Vuoi entrare dal dottore?");

            if (alert.showAndWait().orElse(null) == ButtonType.OK) {
                listapazienti.remove(0);
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ancora nessun paziente in lista");
            alert.show();
        }

    }

}
