package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Generalcontroller {

    @FXML
    private Button btnproduits;

    @FXML
    private Button btnfournisseurs;

    @FXML
    private Button btnventes;

    @FXML
    private Button btnfactures;

    @FXML
    private Button btnachats;

    @FXML
    private Button btnbacksignin;

    @FXML
    void gotoachats(ActionEvent event) throws IOException{
        Object root = FXMLLoader.load(getClass().getResource("produit.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void gotofacture(ActionEvent event) throws IOException{
        Object root = FXMLLoader.load(getClass().getResource("Facture.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void gotofournisseurclient(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("FournisseurClient.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void gotoproduit(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("stock.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void switchtomain(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("Loginfxml.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void gotoventes(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("Vente.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();


    }

}
