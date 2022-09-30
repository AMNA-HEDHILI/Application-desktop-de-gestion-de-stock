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
import java.util.Date;
import java.util.ResourceBundle;

public class produitcontroller implements Initializable {

    @FXML
    private Label designationfieldAchat;



    @FXML
    private TextField IDfieldACHAT;

    @FXML
    private ComboBox<String> nomprenomfieldACHAT;

    @FXML
    private TextField designationACHAT;

    @FXML
    private TextField quantitefieldACHAT;

    @FXML
    private Button btnajouterACHAT;

    @FXML
    private Button btnmodifierAchat;

    @FXML
    private Button btnsupprimerAchat;



    @FXML
    private TextField prixtotalfieldACHAT;

    @FXML
    private TableView<Achats> tvachat;

    @FXML
    private TableColumn<Achats, Integer> Nproduit;

    @FXML
    private TableColumn<Achats, String> NomprenomAchat;

    @FXML
    private TableColumn<Achats, String> Designation;

    @FXML
    private TableColumn<Achats, Integer> Quantité;

    @FXML
    private TableColumn<Achats, Float> prixtotal;
    @FXML
    private TableColumn<Achats, Date> dateachat;

    @FXML
    private DatePicker datachat;

    @FXML
    private Button btnbackprod;

    @FXML
    void switchtogeneral(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("General.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void handlebuttonaction(ActionEvent event) {
        if (event.getSource() == btnmodifierAchat){
            modifierachat();
        }

    }
    @FXML
    private void supprimerAchat() {

        String query = "DELETE FROM `achat` WHERE N_produit =" + IDfieldACHAT.getText() + "";
        executeQuery(query);
        showachat();
    }
    @FXML

    public void ajouterachat() {

        String query = "INSERT INTO `achat`(N_produit, Nom_Prénom, Designation, Quantité, Prix_Total, Date_achat) VALUES " +
                "('" + IDfieldACHAT.getText() + "','" +  nomprenomfieldACHAT.getValue()+ "','" + designationACHAT.getText() +
                "','" + quantitefieldACHAT.getText() + "','" + prixtotalfieldACHAT.getText() + "','" + datachat.getValue() + "')";
        executeQuery(query);
        showachat();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showachat();
        nomprenomfieldACHAT.setItems(List);



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


    private static ObservableList<Achats> getachatlist() {
        ObservableList<Achats> achatList = FXCollections.<Achats>observableArrayList();
        Connection conn;
        conn = getConnection();
        String query = "Select * from achat ";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);

            Achats achat;
            while (rs.next()) {
                achat = new Achats(rs.getInt("N_produit"),  rs.getString("Nom_Prénom"),rs.getString("Designation") ,rs.getInt("Quantité"), rs.getFloat("Prix_Total"), rs.getDate("Date_achat"));
                achatList.add(achat);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return achatList;

    }

    public void showachat() {
        ObservableList<Achats> list = getachatlist();
        Nproduit.setCellValueFactory(new PropertyValueFactory<Achats, Integer>("N_produit"));
        NomprenomAchat.setCellValueFactory(new PropertyValueFactory<Achats, String>("Nom_Prénom"));
        Designation.setCellValueFactory(new PropertyValueFactory<Achats, String>("Designation"));
        Quantité.setCellValueFactory(new PropertyValueFactory<Achats, Integer>("Quantité"));
        prixtotal.setCellValueFactory(new PropertyValueFactory<Achats, Float>("Prix_Total"));
        dateachat.setCellValueFactory( new PropertyValueFactory<Achats, Date>("Date_achat"));
        tvachat.setItems(list);

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
    public ObservableList<String> List = FXCollections.observableArrayList("Amna", "Dalia","Mohamed" );


    public void modifierachat(){
        String query = "UPDATE achat SET Nom_Prénom= '"+nomprenomfieldACHAT.getValue() + "',Designation ='"+ designationACHAT.getText() +
                "',Quantité ="+quantitefieldACHAT.getText()+
                ",Prix_Total ="+prixtotalfieldACHAT.getText()+",Date_achat='"+ datachat.getValue()+
                "' WHERE N_produit = " + IDfieldACHAT.getText()+"" ;

        executeQuery(query);
        showachat();
    }


}
