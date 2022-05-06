package com.example.travelagency.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MoreInfos implements Initializable {
    public static String FullName,Email;
    public static int Telephone,Passport;
    @FXML
    private Label Fname;

    @FXML
    private Label mail;

    @FXML
    private Label pass;

    @FXML
    private Label phone;

    @FXML
    private ImageView user;
    @FXML
    private ImageView back;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File File= new File("../../../../../Images/simple-modern-blue-wavy-abstract-background_68486-14.jpg");
        Image im= new Image(File.toURI().toString());
        back.setImage(im);

        File File2= new File("../../../../../Images/profil.png");
        Image im2= new Image(File2.toURI().toString());
        user.setImage(im2);
        Fname.setText(FullName);
        mail.setText(Email);
        phone.setText(String.valueOf(Telephone));
        pass.setText(String.valueOf(Passport));
    }
}
