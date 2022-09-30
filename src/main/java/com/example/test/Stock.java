package com.example.test;

import java.util.Date;

public class Stock {

    private int N_produit;
    private String Designation;
    private String Categorie;
    private String Unite;
    private Date Date_exp;
    private int QuantiteTot;
    private int QuantiteVente;
    private int QuantiteRestante;
    private float PrixUnite;
    private float PrixAchat;
    private float Gain;

    public Stock(int n_produit, String designation, String categorie, String unite, java.sql.Date date_exp, int quantiteTotale, int quantiteVente, int quantiteRestante, float prixUnite, float prixAchat, float gain) {
        this.N_produit = n_produit;
        this.Designation = designation;
        this.Categorie = categorie;
        this.Unite = unite;
        this.Date_exp = date_exp;
        this.QuantiteTot =quantiteTotale;
        this.QuantiteVente = quantiteVente;
        this.QuantiteRestante = quantiteRestante;
        this.PrixUnite = prixUnite;
        this.PrixAchat = prixAchat;
        this.Gain = gain;
    }

    public int getN_produit() {
        return N_produit;
    }

    public String getDesignation() {
        return Designation;
    }

    public String getCategorie() {
        return Categorie;
    }

    public String getUnite() {
        return Unite;
    }

    public Date getDate_exp() {
        return Date_exp;
    }

    public int getQuantiteTot() {
        return QuantiteTot;
    }

    public int getQuantiteVente() {
        return QuantiteVente;
    }

    public int getQuantiteRestante() {
        return QuantiteRestante;
    }

    public float getPrixUnite() {
        return PrixUnite;
    }

    public float getPrixAchat() {
        return PrixAchat;
    }

    public float getGain() {
        return Gain;
    }
}
