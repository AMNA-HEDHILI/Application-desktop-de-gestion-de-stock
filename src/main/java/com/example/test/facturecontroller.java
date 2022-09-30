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
import java.util.ResourceBundle;

public class facturecontroller  implements Initializable {

    @FXML
    private TextField IDfacturefield;

    @FXML
    private TextField montantàpayerfield;

    @FXML
    private TextField montantàverserfield;
    @FXML
    private TextField montantrestfiled;

    @FXML
    private Button btnajouterfacture;

    @FXML
    private Button btnmodifierfacture;

    @FXML
    private Button btnsupprimerfacture;

    @FXML
    private ComboBox<String> boxetat;

    @FXML
    private ComboBox<String> combofr;

    @FXML
    private TableView<Facture> tabviewfacture;

    @FXML
    private TableColumn<Facture, Integer> Nfacture;

    @FXML
    private TableColumn<Facture, String> Nomprenomfournisseurfacture;

    @FXML
    private TableColumn<Facture, Float> montantàpayer;

    @FXML
    private TableColumn<Facture, Float> montantversé;

    @FXML
    private TableColumn<Facture, Float> montantrestant;

    @FXML
    private TableColumn<Facture, Date> dateversement;

    @FXML
    private TableColumn<Facture, String> etat;

    @FXML
    private Button btnbacktfact;
    @FXML
    private DatePicker datevr;

    @FXML
    void switchtogeneral(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("General.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void supprimerfacture() {

        String query = "DELETE FROM facture WHERE NFacture=" + IDfacturefield.getText() + "";
        executeQuery(query);
        showfacture();
    }
    @FXML
    void hundlebuttonaction(ActionEvent event) {
        if (event.getSource() == btnmodifierfacture){
            modifierfacture();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showfacture();
        boxetat.setItems(List1);

        combofr.setItems(List2);


    }

    public static Connection getConnection() {

        try {

            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java", "admin012", "O!#osTR2RIsPoGyr(d");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }


    private static ObservableList<Facture> getfacturelist() {
        ObservableList<Facture> factureList = FXCollections.<Facture>observableArrayList();
        Connection conn;
        conn = getConnection();
        String query = "Select * from facture ";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Facture facture;
            while (rs.next()) {
                facture = new Facture(rs.getInt("NFacture"), rs.getString("Nom_Et_PrenomFR"), rs.getFloat("Montant_à_payer"), rs.getFloat("Montant_à_verser"), rs.getFloat("Montant_reste"), rs.getString("Etat"), rs.getDate("Date_versement"));
                factureList.add(facture);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return factureList;

    }

    public void showfacture() {
        ObservableList<Facture> list = getfacturelist();
        Nfacture.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("NFacture"));
        Nomprenomfournisseurfacture.setCellValueFactory(new PropertyValueFactory<Facture, String>("Nom_Et_PrenomFR"));
        montantàpayer.setCellValueFactory(new PropertyValueFactory<Facture, Float>("Montant_à_payer"));
        montantversé.setCellValueFactory(new PropertyValueFactory<Facture, Float>("Montant_à_verser"));
        montantrestant.setCellValueFactory(new PropertyValueFactory<Facture, Float>("Montant_reste"));
        dateversement.setCellValueFactory(new PropertyValueFactory<Facture, Date>("Date_versement"));
        etat.setCellValueFactory(new PropertyValueFactory<Facture, String>("Etat"));
        tabviewfacture.setItems(list);

    }


    public ObservableList<String> List1 = FXCollections.observableArrayList("servir", "non servir");
    public ObservableList<String> List2 = FXCollections.observableArrayList("Amna", "Dalia","Mohamed" );







    public LocalDate getDate() {
        LocalDate date = datevr.getValue();
        return date;
    }

    public void ajouterfacture(){
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();

        Integer NFacture = Integer.valueOf(IDfacturefield.getText());
        String Nom_Et_PrenomFR = (String) combofr.getValue();
        Float Montant_à_payer = Float.valueOf(montantàpayerfield.getText());
        Float Montant_à_verser = Float.valueOf(montantàverserfield.getText());
        Float Montant_reste =(Montant_à_payer - Montant_à_verser);

        LocalDate Date_versement = getDate();
        String Etat = boxetat.getValue();

        String insertFields = "INSERT INTO facture (NFacture, Nom_Et_PrenomFR, Montant_à_payer, Montant_à_verser, " +
                "Montant_reste, Date_versement, Etat) VALUES ('";
        String insertValues =  NFacture + "','" + Nom_Et_PrenomFR + "','" + Montant_à_payer + "','" + Montant_à_verser + "','"+ Montant_reste +
                "','" + Date_versement + "','" + Etat + "')";
        String insertToRegister =insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }
        showfacture();
    }
    public void modifierfacture(){
        String query = "UPDATE facture SET Nom_Et_PrenomFR= '"+combofr.getValue() + "',Montant_à_payer ="+ montantàpayerfield.getText()+
                ",Montant_à_verser ="+ montantàverserfield.getText() +
                ",Date_versement='"+  datevr.getValue()+"',Etat ='"+boxetat.getValue()+
                "' WHERE NFacture = " + IDfacturefield.getText()+"" ;

        executeQuery(query);
        showfacture();
    }
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


}
