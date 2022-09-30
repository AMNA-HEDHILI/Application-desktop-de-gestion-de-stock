package com.example.test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class logincontroller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView I;

    @FXML
    private PasswordField Password;

    @FXML
    private Button btnaccount;

    @FXML
    private Button btnback;

    @FXML
    private Circle btnclose;

    @FXML
    private Button btnsignin;

    @FXML
    private Pane pnisignin;

    @FXML
    private Pane pnisignup;

    @FXML
    private StackPane pnistack;

    @FXML
    private PasswordField signuppass;

    @FXML
    private TextField signupuserID;

    @FXML
    private TextField signupusername;

    @FXML
    private Label textlabel;
    @FXML
    private Label textlabel1;

    @FXML
    private TextField username;

    @FXML
    private Label signeduplabel;


    @FXML
    void handlebuttonaction(ActionEvent event) {
        if (event.getSource().equals(btnaccount)) {
            pnisignup.toFront();
        }
    }


    @FXML
    public void switchtogeneral(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("General.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchtomain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Loginfxml.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void validatelogin (ActionEvent event){
        DatabaseConnection Connect = new DatabaseConnection();
        Connection connectDB=  Connect.getConnection();
        String verify ="SELECT id_User,`Password` FROM user WHERE id_User= ' " + username.getText()+
                "'AND Password= '" + Password.getText() + "'" ;

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryresult = statement.executeQuery(verify);
            while (queryresult.next()){
                if (queryresult.getInt(1)==1) {

                    root = FXMLLoader.load(getClass().getResource("General.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    textlabel1.setText("Invalid login");
                    pnisignup.toFront();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void RegisterUser(){
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDB = connect.getConnection();


        String id_User = signupuserID.getText();
        String User = signupusername.getText();
        String Password = signuppass.getText();

        String insertFields = "INSERT INTO `user`(`id_User`, `User`, `Password`) VALUES ('";
        String insertValues = User + "','" + id_User + "','" + Password + "')";
        String insertToRegister =insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            signeduplabel.setText("Registration done !");
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();

        }
    }
}