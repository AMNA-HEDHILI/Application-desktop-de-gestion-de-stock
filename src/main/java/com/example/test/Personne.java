package com.example.test;

public abstract class Personne {
    private String Nom_Prenom;
    private int NTelephone;
    private String Adresse;


    public Personne(String nom_prenom, int nTélephone, String adresse) {
    }

    public abstract String getNom_Prenom();

    public abstract int getNTelephone();

    public abstract String getAdresse();
}
