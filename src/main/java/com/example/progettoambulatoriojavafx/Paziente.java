package com.example.progettoambulatoriojavafx;

public class Paziente {
    private String nome;
    private String cognome;
    private String patologia;
    private int eta;
    private String cf;

    public Paziente(){
        //costruttore vuoto
    }

    // creiamo la classe paziente con attributi e metodi di get e set

    public Paziente(String nome, String cognome, String patologia, String cf, int eta){
        this.eta = eta;
        this.nome = nome;
        this.cf = cf;
        this.cognome = cognome;
        this.patologia = patologia;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCognome() {
        return cognome;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
    }
    public String getPatologia() {
        return patologia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public int getEta() {
        return eta;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public void stampaPaziente(){

        System.out.println(cognome + " " + nome + " " + cf + " " + eta + " " + patologia);
    }
}
