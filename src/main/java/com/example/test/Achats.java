package com.example.test;

import java.util.Date;

public class Achats {
    private int N_produit;
    private String Nom_Prénom;
    private String Designation;

    private int Quantité;
    private float Prix_Total;
    private Date Date_achat;

    public Achats(int n_produit, String nom_Prénom,String designation, int quantité, float prix_Total, Date date_achat) {
        this.N_produit = n_produit;
        this.Designation = designation;
        this.Nom_Prénom = nom_Prénom;
        this.Quantité = quantité;
        this.Prix_Total = prix_Total;
        this.Date_achat = date_achat;
    }

    public int getN_produit() {
        return N_produit;
    }

    public String getDesignation() {
        return Designation;
    }

    public String getNom_Prénom() {
        return Nom_Prénom;
    }

    public float getQuantité() {
        return Quantité;
    }

    public Date getDate_achat() {
        return Date_achat;
    }

    public float getPrix_Total() {
        return Prix_Total;
    }
}
