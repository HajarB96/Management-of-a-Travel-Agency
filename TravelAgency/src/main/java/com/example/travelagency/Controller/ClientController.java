package com.example.travelagency.Controller;

import com.example.travelagency.DAO.DAOImpl;
import com.example.travelagency.HelloApplication;
import com.example.travelagency.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    HelloApplication m = new HelloApplication();
    public static String choice_ComboBox,Arr_ComboBox,Dep_ComboBox;
    public static String Value_ComboBox;
    public static Hotel h1  = new Hotel();
    public static Flight f1 = new Flight();
    public static FlightsBooking fb  = new FlightsBooking();
    public static HotelsBooking hb  = new HotelsBooking();
    boolean isDone=false;
    public static String NameClient;
    @FXML
    private TextField total;
    @FXML
    private ComboBox<String> choice_value;
    @FXML
    private ComboBox<String> choice;
    @FXML
    private Label name;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView flight;

    @FXML
    private ImageView hotel;

    @FXML
    private ImageView reserver1;

    @FXML
    private ImageView reserver;

    @FXML
    private ImageView user;
    @FXML
    private TextField Namee,passp;
    @FXML
    private ComboBox<String> arr;
    @FXML
    private ComboBox<String> dep;
    @FXML
    private Label warning;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        AdminController.isAdmin=false;
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
        reserver.setImage(im3); reserver1.setImage(im3);

        File File= new File("../../../../../Images/user.png");
        Image im= new Image(File.toURI().toString());
        user.setImage(im);
        name.setText(NameClient);
        URL ff = null;
        URL hh= null,bf=null,bh=null;

        try {
            ff= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/flights.fxml");
            hh= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/Hotels.fxml");
            bf= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/booking_F.fxml");
            bh= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/booking_H.fxml");



        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        if(url.equals(ff)) {
            choice.getItems().addAll("arrival_place" ,"arrival_date", "departure_place", "departure_date");
            choice.getSelectionModel().select("arrival_place");
            try {
                choice_ComboBox="arrival_place";
                choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxF()));
                FillTableFlights(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(url.equals(hh)){
            choice.getItems().addAll("hotel_name" ,"address");
            choice.getSelectionModel().select("hotel_name");
            try {
                choice_ComboBox="hotel_name";
                choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxH()));
                FillTableHotels(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(url.equals(bf)){
            f1.setCompany_name(null);
            f1.setDuration(null);
            f1.setPrice(0.0);
            f1.setDeparture_date(null);
            f1.setArrival_date(null);
            f1.setDeparture_time(null);
            f1.setArrival_time(null);
            f1.setArrival_place(null);
            f1.setDeparture_place(null);
            try {
                dep.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxBF1()));
                arr.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxBF2()));
                FillTableFlights(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if(url.equals(bh)){
            h1.setHotel_name(null);
            h1.setCategory(null);
            h1.setAddress(null);
            h1.setPrice_per_night(0.0);
            try {
                FillTableHotels(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void move_bookingfights(ActionEvent event) throws IOException {
        m.changeScene("booking_F.fxml",794,640);
        System.out.println("flights");
    }
    @FXML
    void move_bookinghotels(ActionEvent event) throws IOException {
        m.changeScene("booking_H.fxml",794,640);
        System.out.println("hotels");
    }

    @FXML
    void move_hotels(ActionEvent event) throws IOException {
        m.changeScene("Hotels.fxml",794,640);
    }

    @FXML
    void move_flights(ActionEvent event) throws IOException {
        m.changeScene("flights.fxml",794,640);
    }

    @FXML
    void moreInfos(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ClientsInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 291,439);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Client's Information !");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void chooseF(MouseEvent event) {
        Flight f = tbl_Flights.getSelectionModel().getSelectedItem();
        ClientController.f1=f;
        total.setText(String.valueOf(f1.getPrice()));
    }
    @FXML
    void chooseH(MouseEvent event) {
        Hotel h = hotel_table.getSelectionModel().getSelectedItem();
        ClientController.h1=h;
        total.setText(String.valueOf(Integer.parseInt(Nbr_of_rooms.getText())*h1.getPrice_per_night()));

    }
    /************************ Flights ************************/
    @FXML
    private TableView<Flight> tbl_Flights;
    @FXML
    private TableColumn<Flight, String> Campany_name;
    @FXML
    private TableColumn<Flight, Date> arrival_date;
    @FXML
    private TableColumn<Flight, Date> departure_date;
    @FXML
    private TableColumn<Flight, String> arrival_place;

    @FXML
    private TableColumn<Flight, String> arrival_time;
    @FXML
    private TableColumn<Flight, String> departure_place;

    @FXML
    private TableColumn<Flight, String> departure_time;

    @FXML
    private TableColumn<Flight, Integer> duree;
    @FXML
    private TableColumn<Flight, Double> prix;

    ObservableList<Flight> obListFlights= FXCollections.observableArrayList();
    public void FillTableFlights(int checking) throws SQLException {
        tbl_Flights.getItems().clear();
        if(checking==1) {
            ObservableList<Flight>  L1 = new DAOImpl().ShowAllFlights(obListFlights);
            if(Campany_name != null && departure_date !=null && arrival_date != null && departure_time !=null && arrival_time!=null && departure_place !=null && arrival_place!=null && duree!=null && prix!= null){
                Campany_name.setCellValueFactory(new PropertyValueFactory<>("Company_name"));
                departure_date.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
                arrival_date.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
                departure_time.setCellValueFactory(new PropertyValueFactory<>("departure_time"));
                arrival_time.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
                departure_place.setCellValueFactory(new PropertyValueFactory<>("departure_place"));
                arrival_place.setCellValueFactory(new PropertyValueFactory<>("arrival_place"));
                duree.setCellValueFactory(new PropertyValueFactory<>("duration"));
                prix.setCellValueFactory(new PropertyValueFactory<>("price"));
                tbl_Flights.setItems(L1);
                for (Flight f : L1) System.out.println(f);
            }

        }
        else if(checking ==2) {
            ObservableList<Flight>  L1 = new DAOImpl().SearchFlight(obListFlights);
            if(Campany_name != null && departure_date !=null && arrival_date != null && departure_time !=null && arrival_time!=null && departure_place !=null && arrival_place!=null && duree!=null && prix!= null){
                Campany_name.setCellValueFactory(new PropertyValueFactory<>("Company_name"));
                departure_date.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
                arrival_date.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
                departure_time.setCellValueFactory(new PropertyValueFactory<>("departure_time"));
                arrival_time.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
                departure_place.setCellValueFactory(new PropertyValueFactory<>("departure_place"));
                arrival_place.setCellValueFactory(new PropertyValueFactory<>("arrival_place"));
                duree.setCellValueFactory(new PropertyValueFactory<>("duration"));
                prix.setCellValueFactory(new PropertyValueFactory<>("price"));
                tbl_Flights.setItems(L1);
                for (Flight f : L1) System.out.println(f);
            }

        }
        else{
            ObservableList<Flight>  L1 = new DAOImpl().SearchBF(obListFlights);
            if(Campany_name != null && departure_date !=null && arrival_date != null && departure_time !=null && arrival_time!=null && departure_place !=null && arrival_place!=null && duree!=null && prix!= null){
                Campany_name.setCellValueFactory(new PropertyValueFactory<>("Company_name"));
                departure_date.setCellValueFactory(new PropertyValueFactory<>("departure_date"));
                arrival_date.setCellValueFactory(new PropertyValueFactory<>("arrival_date"));
                departure_time.setCellValueFactory(new PropertyValueFactory<>("departure_time"));
                arrival_time.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
                departure_place.setCellValueFactory(new PropertyValueFactory<>("departure_place"));
                arrival_place.setCellValueFactory(new PropertyValueFactory<>("arrival_place"));
                duree.setCellValueFactory(new PropertyValueFactory<>("duration"));
                prix.setCellValueFactory(new PropertyValueFactory<>("price"));
                tbl_Flights.setItems(L1);
                for (Flight f : L1) System.out.println(f);
            }
        }
    }
    /******************* Hotels**************************/
    @FXML
    private TableView<Hotel> hotel_table;

    @FXML
    private TableColumn<Hotel, String > Hotel_name;

    @FXML
    private TableColumn<Hotel, String> Address;

    @FXML
    private TableColumn<Hotel, String> Category;

    @FXML
    private TableColumn<Hotel, Double> Price;

    ObservableList<Hotel> obListHotels= FXCollections.observableArrayList();
    public void FillTableHotels(boolean checking) throws SQLException {
        hotel_table.getItems().clear();
        if(checking){
            ObservableList<Hotel> L1= new DAOImpl().ShowAllHotels(obListHotels);
            for (Hotel h : L1) System.out.println(h);
            if(Address!=null && Hotel_name!=null && Category!=null && Price!=null){
                Address.setCellValueFactory(new PropertyValueFactory<>("address"));
                Hotel_name.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
                Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
                Price.setCellValueFactory(new PropertyValueFactory<>("price_per_night"));
                hotel_table.setItems(L1);
            }
        }
        else {
            ObservableList<Hotel> L1= new DAOImpl().SearchHotel(obListHotels);
            for (Hotel h : L1) System.out.println(h);
            if(Address!=null && Hotel_name!=null && Category!=null && Price!=null){
                Address.setCellValueFactory(new PropertyValueFactory<>("address"));
                Hotel_name.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
                Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
                Price.setCellValueFactory(new PropertyValueFactory<>("price_per_night"));
                hotel_table.setItems(L1);
            }

        }

    }

    @FXML
    void ChooseCategoryF(ActionEvent event) throws SQLException {
        System.out.println(choice.getValue());
        choice_ComboBox= choice.getValue();
        choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxF()));
    }

    @FXML
    void ChooseCategoryH(ActionEvent event) throws SQLException {
        System.out.println(choice.getValue());
        choice_ComboBox= choice.getValue();
        choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxH()));
    }


    @FXML
    void SearchF(ActionEvent event) throws SQLException {
        choice_ComboBox=choice.getValue();
        Value_ComboBox=choice_value.getValue();
        System.out.println(Value_ComboBox);
        System.out.println(choice_ComboBox);
        FillTableFlights(2);
    }

    @FXML
    void SearchH(ActionEvent event) throws SQLException {
        choice_ComboBox=choice.getValue();
        Value_ComboBox=choice_value.getValue();
        System.out.println(Value_ComboBox);
        System.out.println(choice_ComboBox);
        FillTableHotels(false);
    }

    /*****************Booking Flights *********************/
    @FXML
    void SearchBF(ActionEvent event) throws SQLException {
        Arr_ComboBox=arr.getValue();
        Dep_ComboBox=dep.getValue();
        FillTableFlights(3);
    }
    @FXML
    void ConfirmF(ActionEvent event) throws SQLException {
        if( Namee.getText().isBlank() || passp.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please , Make sure to fill all the fields !");
            alert.showAndWait();
        }
        else if(Objects.equals(ClientController.f1.toString(), "Flight(Company_name=null, departure_place=null, arrival_place=null, departure_time=null, arrival_time=null, departure_date=null, arrival_date=null, duration=null, price=0.0)")){
            warning.setText("you need to select a line in the table to choose which flight you want to book");
        }
        else{
            new DAOImpl().saveFlightsBooking(Namee.getText(),passp.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("New Flight Booking added Successfully !");
            alert.showAndWait();
            isDone=true;
            f1.setCompany_name(null);
            f1.setDuration(null);
            f1.setPrice(0.0);
            f1.setDeparture_date(null);
            f1.setArrival_date(null);
            f1.setDeparture_time(null);
            f1.setArrival_time(null);
            f1.setArrival_place(null);
            f1.setDeparture_place(null);
        }
    }

    /******************Booking Hotels ******************/
    @FXML
    private TextField ArrDateBH;

    @FXML
    private TextField nameeClient;

    @FXML
    private TextField Nbr_of_rooms;

    @FXML
    private TextField NumPass;
    @FXML
    void ConfirmH(ActionEvent event) throws SQLException {
        if( nameeClient.getText().isBlank() || ArrDateBH.getText().isBlank() || NumPass.getText().isBlank() || Nbr_of_rooms.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please , Make sure to fill all the fields !");
            alert.showAndWait();

        }
        else {
            if(Objects.equals(ClientController.h1.toString(), "Hotel(hotel_name=null, Category=null, Address=null, price_per_night=0.0)")){
                warning.setText("you need to select a line in the table to choose which hotel you want to book");
            }
            else{
                new DAOImpl().saveHotelsBooking(nameeClient.getText(), ArrDateBH.getText(),NumPass.getText(),Nbr_of_rooms.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("New Hotel Booking added Successfully !");
                alert.showAndWait();
                isDone=true;
                h1.setHotel_name(null);
                h1.setCategory(null);
                h1.setAddress(null);
                h1.setPrice_per_night(0.0);
            }
        }

    }

    @FXML
    void payNow(ActionEvent event) throws IOException {
        if(!isDone){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please , Make sure to Confirm your choices before paying !!!");
            alert.showAndWait();
        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("payment.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600,400);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Payment !");
            stage.setScene(scene);
            stage.show();
        }
    }
}