package com.example.progettoambulatoriojavafx;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class GestioneFilePazienti {

    // scrive nomi e legge nomi sul e dal file di testo

    final String nome_File = "GestionePazienti.txt";

    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    FileReader fileReader;
    FileWriter fileWriter;


    public boolean openInRead() {
        boolean esito = false;

        try {
            fileReader = new FileReader(nome_File) ;
            bufferedReader = new BufferedReader(fileReader);
            esito = true;

        } catch (FileNotFoundException eReader) {
            System.out.println("Il file non è stato trovato");


            try {
                fileWriter = new FileWriter(nome_File);

                esito = true;

            } catch (IOException eWriter) {
                System.out.println("Non è possibile creare il file");

                esito = false;
            }
        }

        return esito;
    }

    public boolean openInWriting() {
        boolean esito = false;

        try {
            fileWriter = new FileWriter(nome_File, true);
            esito = true;
        } catch (IOException eWriter) {
            System.out.println("Non è possibile aprire in scrittura il file");
        }

        return esito;
    }



    public boolean closeFileInReading() {
        boolean esito = false;

        try {
            fileReader.close();

            esito = true;
        } catch (IOException e) {
            System.out.println("Non è possibile chiudere il file");
        }

        return esito;
    }

    public boolean closeFileInWriting() {
        boolean esito = false;

        try {
            fileWriter.close();

            esito = true;
        } catch (IOException e) {
            System.out.println("Non è possibile chiudere il file");
        }

        return esito;
    }


    public boolean registraPaziente(Paziente paziente) {
        boolean esito = true;

        String strPaziente = paziente.getCognome() + "; " + paziente.getNome() + "; " + paziente.getCf() + "; " + paziente.getEta() + "; " + paziente.getPatologia() + "\n";

        try {
            fileWriter.write(strPaziente);
        } catch (IOException e) {
            System.out.println("Non è possibile salvare i dati del paziente");
        }

        return esito;
    }

    public Paziente leggiPaziente(){

        Paziente p = new Paziente();
        try{
            String stringaInput = bufferedReader.readLine();
            if (stringaInput == null){

                p = null;
            }else{
                p = stringToPaziente(stringaInput);
            }
        } catch (IOException e){
            System.out.println("errore di lettura");
        }

        return p;
    }

    public Paziente stringToPaziente(String stringaInput){

        StringTokenizer st = new StringTokenizer(stringaInput, ";");

        String cognome = st.nextToken();
        String nome = st.nextToken();

        String CF = st.nextToken();
        String patologia = st.nextToken();
        int eta = Integer.parseInt(st.nextToken());


        Paziente p = new Paziente(nome, cognome, patologia, CF, eta);
        return p;
    }

}
