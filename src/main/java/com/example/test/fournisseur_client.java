package com.example.test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;

public class fournisseur_client  implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Pane fournisseur_client;

    @FXML
    private TextField tid;

    @FXML
    private TextField tnom;

    @FXML
    private TextField ttlf;
    @FXML
    private ComboBox<String> comboville;

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private TextField tadresse;

    @FXML
    private Button btnajouter;



    @FXML
    private Button btnsupprimer;



    @FXML
    private Button btnbackfrcli;

    @FXML
    private Label fournisseurclient;

    @FXML
    private TableView<clients> tvclient;

    @FXML
    private TableColumn<clients, Integer> colid;

    @FXML
    private TableColumn<clients, String> colnom;

    @FXML
    private TableColumn<clients, Integer> coltlf;

    @FXML
    private TableColumn<clients, String> coladress;

    @FXML
    private TableView<Fournisseurs> tvfr;
    @FXML
    private TableColumn<Fournisseurs, Integer> colidfr;

    @FXML
    private TableColumn<Fournisseurs, String> colnomfr;

    @FXML
    private TableColumn<Fournisseurs, String> coltlffr;

    @FXML
    private TableColumn<Fournisseurs, String> coladrfr;

    @FXML
    private TableColumn<Fournisseurs, String> colvillefr;


    @FXML
    private void delete(){
        if (radio1.isSelected()) {
            deleteClient();
        }
        else if (radio2.isSelected()){
            deleteFournisseur();
        }
    }

    @FXML
    public void switchtogeneral(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("General.fxml"));
        stage= (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        showclients();
        showfournisseurs();

        comboville.setItems(List1);
    }

    public static Connection getConnection() {

        try {

            Connection conn;
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java","admin012","O!#osTR2RIsPoGyr(d");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }
    public ObservableList<clients> getclientsList() {
        ObservableList<clients> clientsList = FXCollections.<clients>observableArrayList();
        Connection conn;
        conn = getConnection();
        String query = "Select * from client ";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            clients client;
            while (rs.next()) {
                client = new clients(rs.getInt("NClient"), rs.getString("Nom_Et_Prenom"), rs.getString("Adresse"), rs.getInt("NTelephone"));
                clientsList.add(client);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientsList;

    }



    public void showclients(){
        ObservableList<clients> list = getclientsList();
        colid.setCellValueFactory(new PropertyValueFactory<clients,Integer>("NClient"));
        colnom.setCellValueFactory(new PropertyValueFactory<clients,String>("Nom_Et_Prenom"));
        coladress.setCellValueFactory(new PropertyValueFactory<clients,String>("Adresse"));
        coltlf.setCellValueFactory(new PropertyValueFactory<clients,Integer>("NTelephone"));
        tvclient.setItems(list);
    }
    public ObservableList<Fournisseurs> getfournisseursList() {
        ObservableList<Fournisseurs> fournisseursList = FXCollections.<Fournisseurs>observableArrayList();
        Connection conn;
        conn = getConnection();
        String query = "Select * from fournisseur ";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Fournisseurs fournisseurs;
            while (rs.next()) {
                fournisseurs = new Fournisseurs(rs.getInt("NFR"), rs.getString("Nom_Et_Prenom"), rs.getString("Adresse"), rs.getInt("NTelephone"),rs.getString("Ville"));
                fournisseursList.add(fournisseurs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fournisseursList;

    }
    public void showfournisseurs(){
        ObservableList<Fournisseurs> list = getfournisseursList();
        colidfr.setCellValueFactory(new PropertyValueFactory<Fournisseurs,Integer>("NFR"));
        colnomfr.setCellValueFactory(new PropertyValueFactory<Fournisseurs,String>("Nom_Et_Prenom"));
        coladrfr.setCellValueFactory(new PropertyValueFactory<Fournisseurs,String>("Adresse"));
        coltlffr.setCellValueFactory(new PropertyValueFactory<Fournisseurs, String>("NTelephone"));
        colvillefr.setCellValueFactory(new PropertyValueFactory<Fournisseurs,String>("Ville"));
        tvfr.setItems(list);
    }
    public  void ajouterclient(){

        String query = "INSERT INTO `client`(`NClient`, `Nom_Et_Prenom`, `NTelephone`, `Adresse`) VALUES ('" + tid.getText() + "','" + tnom.getText() + "','" + ttlf.getText() + "','" + tadresse.getText() +  "')";
        executeQuery(query);
        showclients();
    }




    public ObservableList<String> List1 = FXCollections.observableArrayList("Tunis", "Mahdia","Sfax" ,"Sousse","Monastir");
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void ajouterfournisseur(){
        String query = "INSERT INTO `fournisseur`(`NFR`, `Nom_Et_Prenom`, `NTelephone`, `Adresse`, `Ville`) VALUES  ('" + tid.getText() +
                "','" + tnom.getText() + "','" + ttlf.getText() + "','" + tadresse.getText() + "','" + comboville.getValue() +"')";
        executeQuery(query);
        showfournisseurs();
    }


    public void Ajouter(){
        if (radio1.isSelected()) {
            ajouterclient();
        }
        else if (radio2.isSelected()){
            ajouterfournisseur();
        }
    }

    public  void updateclient(){

        String query = "UPDATE `client` SET NClient=tid.getText(),Nom_Prenom`='tnom.getText()',`NTélephone`='ttlf.getText()'," +
                "`Adresse`='tadresse.getText()' WHERE NClient=" + tid.getText();
        executeQuery(query);
        showclients();
    }


    private void deleteFournisseur(){
        String query ="DELETE FROM fournisseur WHERE NFR=" + tid.getText() +"";
        executeQuery(query);
        showfournisseurs();
    }
    private void deleteClient(){
        String query ="DELETE FROM client WHERE NClient=" + tid.getText() +"";
        executeQuery(query);
        showclients();
    }







}



