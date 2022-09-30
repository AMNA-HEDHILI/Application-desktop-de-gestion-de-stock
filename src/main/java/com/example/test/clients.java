package com.example.test;

public class clients {
    private  int NClient ;
    private int NTelephone;
    private String Nom_Et_Prenom ;
    private String Adresse;

    public clients(int nClient, String nom_et_prenom, String adresse, int nTelephone) {
        this.NClient = nClient;
        this.NTelephone = nTelephone;
        this.Nom_Et_Prenom = nom_et_prenom;
        this.Adresse = adresse;
    }

    public int getNClient() {
        return NClient;
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


}
