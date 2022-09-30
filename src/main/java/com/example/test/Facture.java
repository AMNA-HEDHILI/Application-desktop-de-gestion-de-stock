package com.example.test;

import java.util.Date;

public class Facture {
    private  int NFacture ;
    private String Nom_Et_PrenomFR ;
    private float Montant_à_payer;
    private float Montant_à_verser;
    private float Montant_reste;

    private String Etat;
    private Date Date_versement;




    public Facture(int nFacture, String nom_et_prenomFR, float montant_à_payer, float montant_à_verser, float montant_reste, String etat, java.sql.Date date_versement) {
        this.NFacture = nFacture;
        this.Nom_Et_PrenomFR = nom_et_prenomFR;
        this.Montant_à_payer = montant_à_payer;
        this.Montant_à_verser = montant_à_verser;
        this.Montant_reste =montant_reste;
        this.Etat = etat;
        this.Date_versement = date_versement;
    }


    public int getNFacture() {
        return NFacture;
    }

    public String getNom_Et_PrenomFR() {
        return Nom_Et_PrenomFR;
    }

    public float getMontant_à_payer() {
        return Montant_à_payer;
    }

    public float getMontant_à_verser() {
        return Montant_à_verser;
    }

    public float getMontant_reste() {
        return Montant_reste;
    }

    public String getEtat() {
        return Etat;
    }

    public Date getDate_versement() {
        return Date_versement;
    }
}
