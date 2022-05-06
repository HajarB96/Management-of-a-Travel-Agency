package com.example.travelagency.Controller;

import com.example.travelagency.DAO.ConnDB;
import com.example.travelagency.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.net.URL;

public class HelloController implements Initializable{
    HelloApplication m = new HelloApplication();
    //AdminController A= new AdminController();
    ClientController C= new ClientController();
    @FXML
    private ImageView brandingImageView;

    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private ImageView lockImageView;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    /*************** LOG IN ***************** */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Images/Around The World.png");
        Image brandingImage= new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("Images/login.png");
        Image lockImage= new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);

    }

    public void loginButtonOnAction(ActionEvent event) throws IOException, SQLException {
        if(!usernameTextField.getText().isBlank() && !enterPasswordField.getText().isBlank()){
            validateLogin();
        } else{
            loginMessageLabel.setText("Please enter username and password");

        }

    }
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void validateLogin() throws SQLException {
        ConnDB conn = new ConnDB();
        String verifyLogin="SELECT count(1) FROM users WHERE fullname = '"+ usernameTextField.getText() + "' and password = '" + enterPasswordField.getText()+ "'";
        ResultSet rs;
        try {
            rs = conn.getSt().executeQuery(verifyLogin);
            rs.next();
                if (rs.getInt(1) == 1) {
                    loginMessageLabel.setText("Congratulations");
                    String req1="SELECT status FROM users WHERE fullname = '"+ usernameTextField.getText() + "' and password = '" + enterPasswordField.getText()+ "'";
                    ResultSet rs1= conn.getSt().executeQuery(req1);
                    rs1.next();
                    String Result = rs1.getString("status");
                    System.out.println(Result);


                   if(Objects.equals(Result, "client")){
                       ClientController.NameClient=usernameTextField.getText();
                       MoreInfos.FullName=usernameTextField.getText();
                       String req2="SELECT email,phone,passport_num FROM users WHERE fullname = '"+ usernameTextField.getText() + "' and password = '" + enterPasswordField.getText()+ "'";
                       ResultSet rs2= conn.getSt().executeQuery(req2);
                       rs2.next();
                       String Email = rs2.getString("email");
                       int Phone = rs2.getInt("phone");
                       int Pass = rs2.getInt("passport_num");
                       MoreInfos.Email=Email;
                       MoreInfos.Telephone=Phone;
                       MoreInfos.Passport=Pass;
                       m.changeScene("flights.fxml",794,640);
                    }
                    else if(Objects.equals(Result, "admin")) m.changeScene("FlightsManagement.fxml",794,640);


                } else {
                    loginMessageLabel.setText("Invalid Login. Please Try Again.");

                }


        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}