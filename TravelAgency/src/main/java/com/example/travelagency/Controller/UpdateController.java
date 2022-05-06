package com.example.travelagency.Controller;
import com.example.travelagency.DAO.DAOImpl;
import com.example.travelagency.HelloApplication;
import com.example.travelagency.Model.Flight;
import com.example.travelagency.Model.Hotel;
import com.example.travelagency.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    HelloApplication m = new HelloApplication();
    public static Flight F1=new Flight();
    public static User U1  = new User();
    public static Hotel h1  = new Hotel();

    @FXML
    private TextField Address;

    @FXML
    private TextField Category;

    @FXML
    private TextField Name;

    @FXML
    private ImageView client;

    @FXML
    private ImageView flight;

    @FXML
    private ImageView hotel;

    @FXML
    private ImageView logo;

    @FXML
    private TextField price;

    @FXML
    private ImageView reserver;
    public static String update_Client_Name,update_Client_Email,update_Client_Password,update_Hotel_Name,update_Hotel_Address,update_Hotel_Category;
    public static int update_Client_Phone,update_Client_Passport;
    public static Double update_Hotel_Price;
    public static String update_Flight_ArrTime,update_Flight_DepTime,update_Flight_ArrPlace,update_Flight_DepPlace, update_Flight_duration,update_Flight_CompanyName;
    public static Date update_Flight_ArrDate,update_Flight_DepDate;
    public static Double update_Flight_Price;
    @FXML
    void move_clients(ActionEvent event) throws IOException {
        m.changeScene("ClientManagement.fxml",400,500);
        System.out.println("ClientManagement");
    }
    @FXML
    void move_booking(ActionEvent event) throws IOException {
        m.changeScene("bookingMangement.fxml",400,500);
    }

    @FXML
    void move_flights(ActionEvent event) throws IOException, SQLException {
        m.changeScene("FlightsManagement.fxml",400,500);

    }

    @FXML
    void move_hotels(ActionEvent event) throws IOException {
        m.changeScene("HotelsMangement.fxml",400,500);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("../../../../../Images/Around_The_World-removebg-preview.png");
        Image brandingImage= new Image(brandingFile.toURI().toString());
        logo.setImage(brandingImage);

        File File1 = new File("../../../../../Images/Flight.png");
        Image im1= new Image(File1.toURI().toString());
        flight.setImage(im1);

        File File2 = new File("../../../../../Images/Hotel.png");
        Image im2= new Image(File2.toURI().toString());
        hotel.setImage(im2);

        File File3= new File("../../../../../Images/booking.png");
        Image im3= new Image(File3.toURI().toString());
        reserver.setImage(im3);

        File File4 = new File("../../../../../Images/Client.png");
        Image im4= new Image(File4.toURI().toString());
        client.setImage(im4);
        URL ff = null;
        URL hh= null;
        URL cc= null;
        try {
            ff= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/UpdateFlight.fxml");
            hh= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/UpdateHotel.fxml");
            cc= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/UpdateClient.fxml");

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        if(url.equals(ff)) {
            COMPANYname.setText(update_Flight_CompanyName);
            depTime.setText(update_Flight_DepTime);
            depPlace.setText(update_Flight_DepPlace);
            depDate.setValue(update_Flight_DepDate.toLocalDate());
            ArrDate.setValue(update_Flight_ArrDate.toLocalDate());

            ArrTime.setText(update_Flight_ArrTime);
            ArrPlace.setText(update_Flight_ArrPlace);
            duration.setText(update_Flight_duration);
            price1.setText(String.valueOf(update_Flight_Price));


        }
        else if(url.equals(hh)){
            Name.setText(update_Hotel_Name);
            Address.setText(update_Hotel_Address);
            Category.setText(update_Hotel_Category);
            price.setText(String.valueOf(update_Hotel_Price));
        }
        else if(url.equals(cc)){
            CFName.setText(update_Client_Name);
            Cemail.setText(update_Client_Email);
            Cpassport.setText(String.valueOf(update_Client_Passport));
            Cpassword.setText(update_Client_Password);
            Cphone.setText(String.valueOf(update_Client_Phone));

        }
        else{
            System.out.println("NO");
        }


    }
    /******************Hotels***********************/

    @FXML
    void UpdateH(ActionEvent event) throws SQLException {
        System.out.println(h1.getHotel_name());
        new DAOImpl().UpdateHotel(
                Hotel.builder().hotel_name(Name.getText())
                        .Address(Address.getText())
                        .Category(Category.getText())
                        .price_per_night(Double.parseDouble(price.getText()))
                        .build()
        );
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Hotel Updated Succcessfully !");
        alert.showAndWait();
        System.out.println("Hotel Updated Succcessfully !!!!");
        h1.setHotel_name(null);
        h1.setCategory(null);
        h1.setAddress(null);
        h1.setPrice_per_night(0.0);
        System.out.println("After : "+h1.toString());

    }

    @FXML
    void backH(ActionEvent event) throws IOException {
        move_hotels(event);


    }

    @FXML
    void clear(ActionEvent event) {

    }


    /*************** Clients *******************/

    @FXML
    private TextField Cemail;

    @FXML
    private TextField CFName;

    @FXML
    private TextField Cpassport;

    @FXML
    private TextField Cpassword;

    @FXML
    private TextField Cphone;
    @FXML
    void UpdateC(ActionEvent event) throws SQLException {
        System.out.println(U1.getFullname());
        new DAOImpl().UpdateClient(
                User.builder().fullname(CFName.getText())
                        .email(Cemail.getText())
                        .phone(Integer.parseInt((Cphone.getText())))
                        .passport_num(Integer.parseInt(Cpassport.getText()))
                        .password(Cpassword.getText())
                        .build()
        );
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Client Updated Succcessfully  !");
        alert.showAndWait();
        System.out.println("Client Updated Succcessfully !!!!");
        U1.setFullname(null);
        U1.setEmail(null);
        U1.setPassport_num(0);
        U1.setPhone(0);
        U1.setPassword(null);
        System.out.println("AFTER :"+U1.toString());
    }

    @FXML
    void backC(ActionEvent event) {

    }
    /************ fLIGHTS********************/
    @FXML
    private DatePicker ArrDate;

    @FXML
    private TextField ArrPlace;

    @FXML
    private TextField ArrTime;

    @FXML
    private TextField COMPANYname;

    @FXML
    private DatePicker depDate;

    @FXML
    private TextField depPlace;

    @FXML
    private TextField depTime;

    @FXML
    private TextField duration;


    @FXML
    private TextField price1;

    @FXML
    void UpdateF(ActionEvent event) throws SQLException {
        new DAOImpl().UpdateFlight(
                Flight.builder().Company_name(COMPANYname.getText())
                        .departure_date(Date.valueOf(depDate.getValue()))
                        .arrival_date(Date.valueOf(ArrDate.getValue()))
                        .departure_time(depTime.getText())
                        .arrival_time(ArrTime.getText())
                        .departure_place(depPlace.getText())
                        .arrival_place(ArrPlace.getText())
                        .duration(duration.getText())
                        .price(Double.parseDouble(price1.getText()))
                        .build()
        );
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Flight Updated Succcessfully  !");
        alert.showAndWait();
        System.out.println("Flight Updated Succcessfully !!!!");
        F1.setCompany_name(null);
        F1.setDuration(null);
        F1.setPrice(0.0);
        F1.setDeparture_date(null);
        F1.setArrival_date(null);
        F1.setDeparture_time(null);
        F1.setArrival_time(null);
        F1.setArrival_place(null);
        F1.setDeparture_place(null);
        System.out.println("After "+F1.toString());
    }

    @FXML
    void backF(ActionEvent event) throws SQLException, IOException {
        move_flights(event);

    }
}
