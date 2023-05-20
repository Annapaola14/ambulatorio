package com.example.progettoambulatoriojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Vector;

public class VisualizzaController {

    @FXML AnchorPane visualizza;

    // ci fa vedere la lista dei pazienti registrati nel coso

    @FXML
    protected void esci() {
        Stage stage;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Esci dalla registrazione");
        alert.setHeaderText("Stai per uscire dalla finestra di registrazione");

        stage = (Stage) visualizza.getScene().getWindow();
        stage.close();
    }

    @FXML private ListView<String> lista;
    private Vector<Paziente> listaPazienti;

    public void inizializza(Vector<Paziente> listaPazienti){
        ObservableList<String> items = FXCollections.observableArrayList();
        for(Paziente paziente : listaPazienti){
            items.add(paziente.getCognome() + " " + paziente.getNome());
        }

        lista.setItems(items);
    }

    public void setListaPazienti(Vector<Paziente> listaPazienti){
        this.listaPazienti = listaPazienti;
    }

    public ListView<String> getLista() {
        return lista;
    }

    public void setLista(ListView<String> lista) {
        this.lista = lista;
    }

    public Vector<Paziente> getListaPazienti() {
        return listaPazienti;
    }



}
