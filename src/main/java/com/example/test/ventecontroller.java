package com.example.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ventecontroller {

    @FXML
    private TableView<?> tbViewVente1;

    @FXML
    private TableColumn<?, ?> colNprod;

    @FXML
    private TableColumn<?, ?> colDesign;

    @FXML
    private TableColumn<?, ?> colQteTot;

    @FXML
    private TableColumn<?, ?> colQteVente;

    @FXML
    private TableColumn<?, ?> colQteReste;

    @FXML
    private TableColumn<?, ?> colPrix;

    @FXML
    private TableColumn<?, ?> colGain;

    @FXML
    private TableView<?> tbViewVente2;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colDesignation2;

    @FXML
    private TableColumn<?, ?> colPrix2;

    @FXML
    private TableColumn<?, ?> colGain2;

    @FXML
    private TableView<?> tbViewVente3;

    @FXML
    private TableColumn<?, ?> colGain3;

    @FXML
    private TableColumn<?, ?> colQuantite;

    @FXML
    private TextField LabelDesignation;

    @FXML
    private Button btnRechercher;

    @FXML
    private Button btnPrixTot;

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField labelQuantite;

    @FXML
    private TableView<?> tbViewVente4;

    @FXML
    private TableColumn<?, ?> colNVente;

    @FXML
    private TableColumn<?, ?> colNomprenom;

    @FXML
    private TableColumn<?, ?> colComm;

    @FXML
    private TableColumn<?, ?> colMarchndise;

    @FXML
    private TableColumn<?, ?> colPrixàpayer;

    @FXML
    private TableColumn<?, ?> colBenefice;

    @FXML
    private TableColumn<?, ?> colDateVente;

    @FXML
    private TextField LabelComm;

    @FXML
    private TextField LabelPrixTot;

    @FXML
    private Button btnEnregistrer;

    @FXML
     void switchtogeneral(ActionEvent event) throws IOException {

        Object root = FXMLLoader.load(getClass().getResource("General.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

}
