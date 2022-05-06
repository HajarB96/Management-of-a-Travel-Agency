package com.example.travelagency.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class PaymentController implements Initializable {
    @FXML
    private ImageView lockImageView;
    @FXML
    private ImageView visaImageView;
    @FXML
    private ImageView palImageView;
    @FXML
    private ImageView masterImageView;
    @FXML
    private Label registrationMessageLabel;

    @FXML
    private Button payButton;

    @FXML
    private PasswordField setPasswordField;
    @FXML
    private Button btn1;

    public void initialize(URL url, ResourceBundle resourceBundle){
        File shieldFile = new File("../../../../../Images/paymentlogo-removebg-preview.png");
        Image shieldImage= new Image(shieldFile.toURI().toString());
        lockImageView.setImage(shieldImage);
        File im1  = new File("../../../../../Images/visa.png");
        Image im11= new Image(im1.toURI().toString());
        visaImageView.setImage(im11);
        File im2 = new File("../../../../../Images/paypal.png");
        Image im22= new Image(im2.toURI().toString());
        palImageView.setImage(im22);
        File im3 = new File("../../../../../Images/mastercard.png");
        Image im33= new Image(im3.toURI().toString());
        masterImageView.setImage(im33);

    }
    public void payButtonOnAction(ActionEvent event){
        registrationMessageLabel.setText("payed successfully!");

    }
    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) btn1.getScene().getWindow();
        stage.close();
    }



}




