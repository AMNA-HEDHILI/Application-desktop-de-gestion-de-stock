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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Stockcontroller implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableColumn<Stock, String> Designationstock;

    @FXML
    private TableColumn<Stock, Integer> Nproduitstock;

    @FXML
    private ComboBox<String> box1;

    @FXML
    private ComboBox<String> box2;

    @FXML
    private Button btnajouterstock;

    @FXML
    private Button btnbackstock;

    @FXML
    private Button btnmodifierstock;

    @FXML
    private Button btnrechercherstock;

    @FXML
    private Button btnsupprimerstock;

    @FXML
    private TableColumn<Stock, String> categorie;

    @FXML
    private TextField categoriefield;

    @FXML
    private TableColumn<Stock, java.util.Date> dateexpiration;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TableColumn<Stock, Float> gain;

    @FXML
    private TextField idstockfield;

    @FXML
    private TableColumn<Stock, Float> prixunitaire;
     /*@FXML
    private TableColumn<Stock, Float> prixvente;


     */

    @FXML
    private TextField prixunitaireachat;

    @FXML
    private TextField prixventefld;

    @FXML
    private TextField qteVendu;

    @FXML
    private TableColumn<Stock, Integer> qtereste;

    @FXML
    private TableColumn<Stock, Integer> qtetotale;

    @FXML
    private TextField qtetotalefield;

    @FXML
    private TableColumn<Stock, Integer> qtevente;

    @FXML
    private TableView<Stock> tabviewstock;

    @FXML
    private TableColumn<Stock, String> unite;


    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void switchtogeneral(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("General.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public Connection getConnection() {

        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Stock> getStockList() {
        ObservableList<Stock> Stocklist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM stock";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Stock stock;
            while (rs.next()) {
                stock = new Stock(((ResultSet) rs).getInt("N_produit"), rs.getString("Designation"),
                        rs.getString("Categorie"), rs.getString("Unite"), rs.getDate("Date_exp"),
                        rs.getInt("QuantiteTot"), rs.getInt("QuantiteVente"), rs.getInt("QuantiteRestante"),
                        rs.getFloat("PrixUnite"), rs.getFloat("PrixVente"), rs.getFloat("Gain"));
                Stocklist.add(stock);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Stocklist;
    }

    public void showStock() {

        ObservableList<Stock> List2 = getStockList();
        Nproduitstock.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("N_produit"));
        Designationstock.setCellValueFactory(new PropertyValueFactory<Stock, String>("Designation"));
        categorie.setCellValueFactory(new PropertyValueFactory<Stock, String>("Categorie"));
        unite.setCellValueFactory(new PropertyValueFactory<Stock, String>("Unite"));
        dateexpiration.setCellValueFactory(new PropertyValueFactory<Stock, Date>("Date_exp"));
        qtetotale.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("QuantiteTot"));
        qtevente.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("QuantiteVente"));
        qtereste.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("QuantiteRestante"));
        prixunitaire.setCellValueFactory(new PropertyValueFactory<Stock, Float>("PrixUnite"));
        //prixvente.setCellValueFactory(new PropertyValueFactory<Stock, Float>("PrixVente"));
        gain.setCellValueFactory(new PropertyValueFactory<Stock, Float>("Gain"));
        tabviewstock.setItems(List2);
    }

    public ObservableList<String> List = FXCollections.observableArrayList("Kilogramme", "Pièce");
    public ObservableList<String> List1 = FXCollections.observableArrayList("Designation1", "Designation2", "Designation3");

    @FXML
    public LocalDate getDate() {
        LocalDate date = datepicker.getValue();
        return date;
    }

    public void RegisterProduit() {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        String N_produit = idstockfield.getText();
        String Designation = box2.getValue();
        String Categorie = categoriefield.getText();
        String Unite = box1.getValue();
        LocalDate Date_exp = getDate();
        Integer QuantiteTot = Integer.valueOf(Integer.valueOf(qtetotalefield.getText()));
        Integer QuantiteVente = Integer.valueOf(Integer.valueOf(qteVendu.getText()));
        Integer QuantiteRestante = QuantiteTot - QuantiteVente;
        Float PrixVente = Float.valueOf(Float.valueOf(prixventefld.getText()));
        Float PrixUnite = Float.valueOf(Float.valueOf(prixunitaireachat.getText()));
        Float Gain = PrixVente - PrixUnite;

        String insertFields = "INSERT INTO `stock`(N_produit, Designation, Categorie, Unite, Date_exp, QuantiteTot," +
                " QuantiteVente, QuantiteRestante, PrixUnite, PrixVente ,Gain) VALUES ('";
        String insertValues = N_produit + "','" + Designation + "','" + Categorie + "','" + Unite + "','" + Date_exp +
                "','" + QuantiteTot + "','" + QuantiteVente + "','" + QuantiteRestante + "' ,'" + PrixUnite + "','" + PrixVente + "' ,'" + Gain + "')";

        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();

        }
        showStock();
    }

    @FXML
    private void deleteAchat() {
        String query = "DELETE FROM stock WHERE N_produit =" + idstockfield.getText() + "";
        executeQuery(query);
        showStock();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStock();
        box1.setItems(List);
        box2.setItems(List1);

    }

    public void modifierstock() {

        String query = "UPDATE stock SET Designation= '"+box2.getValue() + "',Categorie ='"+ categoriefield.getText() +


                "' WHERE N_produit = " + idstockfield.getText()+"" ;

        executeQuery(query);
        showStock();
    }

    @FXML
    void handlebuttonaction(ActionEvent event) {
        if (event.getSource() == btnmodifierstock) {
            modifierstock();

        }


    }
}