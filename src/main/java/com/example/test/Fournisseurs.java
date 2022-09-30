package com.example.test;

public class Fournisseurs {
    private int NTelephone;
    private String Nom_Et_Prenom ;
    private String Adresse;
    private  int NFR ;
    private String ville ;



    public int getNFR() {
        return NFR;
    }

    public int getNTelephone() {
        return NTelephone;
    }

    public String getNom_Et_Prenom() {
        return Nom_Et_Prenom;
    }

    public String getAdresse() {
        return Adresse;
    }
    public String getVille(){return ville ;}

    public Fournisseurs(int NTelephone, String nom_Et_Prenom, String adresse, int NFR, String ville) {
        this.NTelephone = NTelephone;
        Nom_Et_Prenom = nom_Et_Prenom;
        Adresse = adresse;
        this.NFR = NFR;
        this.ville = ville;
    }
}
