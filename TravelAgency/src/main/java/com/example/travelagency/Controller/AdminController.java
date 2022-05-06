package com.example.travelagency.Controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
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

import static com.example.travelagency.Controller.HelloController.infoBox;

public class AdminController implements Initializable {
    public static boolean isAdmin=true;
    public static String choice_ComboBox,Arr_ComboBox,Dep_ComboBox;
    public static String Value_ComboBox;
    HelloApplication m = new HelloApplication();
    public static Hotel h1  = new Hotel();
    public static User u1 = new User();
    public static Flight f1 = new Flight();
    public static FlightsBooking fb  = new FlightsBooking();
    public static HotelsBooking hb  = new HotelsBooking();
    boolean isDone=false;


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
        URL af= null;
        URL bb= null,bf=null,bh=null;
        /**
         *::::::::::::::: REMARQUE:::::::::::
         * Afin que l'application fonctionne sans erreurs , il faut changer les liens ci-dessous et mettre à leurs places
         * le chemin du dossier "Travel Agency" où vous avez mis ce projet localement sur votre machine
         *
         */
        try {
            ff= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/FlightsManagement.fxml");
            hh= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/HotelsMangement.fxml");
            cc= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/ClientManagement.fxml");
            af= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/AddFlight.fxml");
            bb= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/ManageBookingFirst.fxml");
            bf= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/ManageBookingF.fxml");
            bh= new URL("file:/C:/Users/hp/TravelAgency/target/classes/com/example/travelagency/ManageBookingH.fxml");

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        if(url.equals(ff)) {
            System.out.println("in Flights Management");
            choice.getItems().addAll("arrival_place" ,"arrival_date", "departure_place", "departure_date");
            choice.getSelectionModel().select("arrival_place");
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
                choice_ComboBox="arrival_place";
                choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxF()));
                FillTableFlights(1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else if(url.equals(af)){
            depDate.setValue(LocalDate.now());
            ArrDate.setValue(LocalDate.now());
        }
        else if(url.equals(hh)){
            System.out.println("in Hotels Management");
            choice.getItems().addAll("hotel_name" ,"address");
            choice.getSelectionModel().select("hotel_name");
            h1.setHotel_name(null);
            h1.setCategory(null);
            h1.setAddress(null);
            h1.setPrice_per_night(0.0);

            try {
                choice_ComboBox="hotel_name";
                choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxH()));
                FillTableHotels(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else if(url.equals(cc)){
            System.out.println("in Clients Management");
            choice.getItems().addAll("fullname" ,"passport_num","phone","email");
            choice.getSelectionModel().select("fullname");
            u1.setFullname(null);
            u1.setEmail(null);
            u1.setPassport_num(0);
            u1.setPhone(0);
            u1.setPassword(null);

            try {
                choice_ComboBox="fullname";
                choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxC()));
                FillTableUsers(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else if(url.equals(bb)){
            try {
                fb.setFull_name(null);
                fb.setN_passport(null);
                fb.setCampanyName(null);
                fb.setDep_place(null);
                fb.setArr_place(null);
                fb.setDep_time(null);
                fb.setPrice(null);
                fb.setDep_place(null);
                hb.setFull_name(null);
                hb.setHotel_name(null);
                hb.setAddress(null);
                hb.setDate(null);
                hb.setNbr_rooms(0);
                hb.setPassport_num(null);
                FillTableFlightsBooking();
                FillTablehb();
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
    private TableColumn<FlightsBooking,String> Full_name;

    @FXML
    private TableColumn<FlightsBooking,String>  N_passport;

    @FXML
    private TableColumn<FlightsBooking,String>  CampanyName;

    @FXML
    private TableColumn<FlightsBooking,String>  dep_time;

    @FXML
    private TableColumn<FlightsBooking,String>  Arr_time;

    @FXML
    private TableColumn<FlightsBooking,String> Dep_place;

    @FXML
    private TableColumn<FlightsBooking,String>  Arr_place;
    @FXML
    private TableColumn<FlightsBooking, String> priceF;
    @FXML
    private TableView<FlightsBooking> Tab_flightsB;
    ObservableList<FlightsBooking> obListFlightsBooking= FXCollections.observableArrayList();
    public void FillTableFlightsBooking() throws SQLException {
        ObservableList<FlightsBooking>  L3 = new DAOImpl().ShowAllFlightsBooking(obListFlightsBooking);
        if(Full_name != null && N_passport !=null && CampanyName!= null && dep_time !=null && Arr_time!=null && Dep_place !=null && Arr_place!=null ){
            Full_name.setCellValueFactory(new PropertyValueFactory<>("Full_name"));
            N_passport.setCellValueFactory(new PropertyValueFactory<>("N_passport"));
            CampanyName.setCellValueFactory(new PropertyValueFactory<>("CampanyName"));
            dep_time.setCellValueFactory(new PropertyValueFactory<>("dep_time"));
            Arr_time.setCellValueFactory(new PropertyValueFactory<>("Arr_time"));
            Dep_place.setCellValueFactory(new PropertyValueFactory<>("Dep_place"));
            Arr_place.setCellValueFactory(new PropertyValueFactory<>("Arr_place"));
            priceF.setCellValueFactory(new PropertyValueFactory<>("price"));
            Tab_flightsB.setItems(L3);
            for (FlightsBooking f : L3) System.out.println(f);
        }


    }
    @FXML
    void AddFlightBooking(ActionEvent event) throws IOException {
        m.changeScene("ManageBookingF.fxml",400,500);
    }

    @FXML
    void AddHotelBooking(ActionEvent event) throws IOException, SQLException {
        m.changeScene("ManageBookingH.fxml",400,500);

    }

    @FXML
    void chooseF(MouseEvent event) {
        Flight f = tbl_Flights.getSelectionModel().getSelectedItem();
        AdminController.f1=f;
        total.setText(String.valueOf(f1.getPrice()));
    }
    @FXML
    private TextField Namee,passp;
    @FXML
    private ComboBox<String> arr;
    @FXML
    private ComboBox<String> dep;
    @FXML
    private Label warning2;

    @FXML
    void Searchbf(ActionEvent event) throws SQLException {
        Arr_ComboBox=arr.getValue();
        Dep_ComboBox=dep.getValue();
        FillTableFlights(3);

    }
    @FXML
    void Confirmf(ActionEvent event) throws SQLException {
        if( Namee.getText().isBlank() || passp.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please , Make sure to fill all the fields !");
            alert.showAndWait();
        }
        else if(Objects.equals(AdminController.f1.toString(), "Flight(Company_name=null, departure_place=null, arrival_place=null, departure_time=null, arrival_time=null, departure_date=null, arrival_date=null, duration=null, price=0.0)")){
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
    /**********Hotels Booking************/
    @FXML
    private TextField ArrDateBH;

    @FXML
    private TextField NameClient;

    @FXML
    private TextField Nbr_of_rooms;

    @FXML
    private TextField Numpass;
    @FXML
    private TableColumn<HotelsBooking, String> col1;

    @FXML
    private TableColumn<HotelsBooking, String> col2;

    @FXML
    private TableColumn<HotelsBooking, String> col3;

    @FXML
    private TableColumn<HotelsBooking, String> col4;

    @FXML
    private TableColumn<HotelsBooking, String> col5;

    @FXML
    private TableColumn<HotelsBooking, String> col6;
    @FXML
    private TableView<HotelsBooking> Tab_HotelsB;
    @FXML
    void ConfirmH(ActionEvent event) throws SQLException {
        if( NameClient.getText().isBlank() || ArrDateBH.getText().isBlank() || Numpass.getText().isBlank() || Nbr_of_rooms.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please,Make sure to fill all the fields !");
            alert.showAndWait();
        }
        else if(Objects.equals(AdminController.h1.toString(), "Hotel(hotel_name=null, Category=null, Address=null, price_per_night=0.0)")){
            warning.setText("you need to select a line in the table to choose which hotel you want to book");
        }
        else{
            new DAOImpl().saveHotelsBooking(NameClient.getText(), ArrDateBH.getText(),Numpass.getText(),Nbr_of_rooms.getText());
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
    ObservableList<HotelsBooking> obListHotelsBooking= FXCollections.observableArrayList();
    public void FillTablehb() throws SQLException {
       ObservableList<HotelsBooking>  L1 = new DAOImpl().ShowAllHotelsBooking(obListHotelsBooking);
        if(col1 != null && col2 != null && col3!= null && col4 != null && col5 !=null && col6 !=null){
            col1.setCellValueFactory(new PropertyValueFactory<>("full_name"));
            col2.setCellValueFactory(new PropertyValueFactory<>("passport_num"));
            col3.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
            col4.setCellValueFactory(new PropertyValueFactory<>("address"));
            col5.setCellValueFactory(new PropertyValueFactory<>("date"));
            col6.setCellValueFactory(new PropertyValueFactory<>("nbr_rooms"));
            Tab_HotelsB.setItems(L1);
        }
    }
    @FXML
    void chooseH(MouseEvent event) {
        Hotel h = hotel_table.getSelectionModel().getSelectedItem();
        AdminController.h1=h;
        total.setText(String.valueOf(Integer.parseInt(Nbr_of_rooms.getText())*h1.getPrice_per_night()));

    }
    @FXML
    void selectRowBF(MouseEvent event) {
        FlightsBooking fb1 = Tab_flightsB.getSelectionModel().getSelectedItem();
        AdminController.fb=fb1;

    }

    @FXML
    void selectRowBH(MouseEvent event) {
        HotelsBooking fb1 = Tab_HotelsB.getSelectionModel().getSelectedItem();
        AdminController.hb=fb1;

    }


    @FXML
    void Cancel1(ActionEvent event) throws SQLException, IOException {
        System.out.println(AdminController.hb.toString());
        if(!Objects.equals(AdminController.hb.toString(), "HotelsBooking(full_name=null, hotel_name=null, address=null, date=null, passport_num=null, nbr_rooms=0)")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you sure you want to cancel this hotel booking ?? ", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                new DAOImpl().CancelBookingHotel();

            }
            m.changeScene("ManageBookingFirst.fxml",400,500);

        }
        else{
            warning.setText("you need to select a line in the table to choose which hotel booking you want to cancel");

        }

    }

    @FXML
    void Cancel2(ActionEvent event) throws SQLException, IOException {
        System.out.println(AdminController.fb.toString());
        if(!Objects.equals(AdminController.fb.toString(), "FlightsBooking(Full_name=null, N_passport=null, CampanyName=null, dep_time=null, Arr_time=null, Dep_place=null, Arr_place=null, price=null)")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you sure you want to cancel this flight booking ?? ", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                new DAOImpl().CancelBookingFlight();

            }
            m.changeScene("ManageBookingFirst.fxml",400,500);
        }
        else{
            warning2.setText("you need to select a line in the table to choose which flight booking you want to cancel");

        }

    }
    @FXML
    private ImageView logo;
    @FXML
    private ImageView flight;

    @FXML
    private ImageView hotel;

    @FXML
    private ImageView client;

    @FXML
    private ImageView reserver;
    @FXML
    private DatePicker ArrDate;

    @FXML
    private TextField ArrPlace;

    @FXML
    private TextField ArrTime;

    @FXML
    private TextField Cname;
    @FXML
    private DatePicker depDate;
    @FXML
    private TextField depPlace;

    @FXML
    private TextField depTime;
    @FXML
    private TextField duration;
    @FXML
    private TextField price;
    @FXML
    private Label warning;
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

    @FXML
    private TableView<Hotel> hotel_table;
    @FXML
    private TableColumn<Hotel,String> Hotel_name;

    @FXML
    private TableColumn<Hotel,String> Address;

    @FXML
    private TableColumn<Hotel,String> Category;

    @FXML
    private TableColumn<Hotel,Double> Price;

    /**************Show hotel table*********************/
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
    private TableView<User> users_table;

    @FXML
    private TableColumn<User, String> Fullname;

    @FXML
    private TableColumn<User, String> Email;

    @FXML
    private TableColumn<User,Integer> Phone;

    @FXML
    private TableColumn<User, String> Passport;

    @FXML
    private TableColumn<User, String> Password;


    ObservableList<User> obListUsers= FXCollections.observableArrayList();
    public void FillTableUsers(boolean checking) throws SQLException {
        users_table.getItems().clear();
        if(checking){
            ObservableList<User> L1 = new DAOImpl().ShowAllUsers(obListUsers);
            for (User U : L1) System.out.println(U);
            if(Fullname != null && Email != null && Phone != null && Passport != null && Password !=null){
                Fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
                Email.setCellValueFactory(new PropertyValueFactory<>("email"));
                Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                Passport.setCellValueFactory(new PropertyValueFactory<>("passport_num"));
                Password.setCellValueFactory(new PropertyValueFactory<>("password"));
                users_table.setItems(L1);
                for (User U : L1) System.out.println(U);
            }
        }
        else{
            ObservableList<User> L1 = new DAOImpl().SearchClient(obListUsers);
            for (User U : L1) System.out.println(U);
            if(Fullname != null && Email != null && Phone != null && Passport != null && Password !=null){
                Fullname.setCellValueFactory(new PropertyValueFactory<>("fullname"));
                Email.setCellValueFactory(new PropertyValueFactory<>("email"));
                Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                Passport.setCellValueFactory(new PropertyValueFactory<>("passport_num"));
                Password.setCellValueFactory(new PropertyValueFactory<>("password"));
                users_table.setItems(L1);
                for (User U : L1) System.out.println(U);
            }
        }



    }

    @FXML
    private ComboBox<String> choice;

    @FXML
    private ComboBox<String> choice_value;

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
    void ChooseCategoryC(ActionEvent event) throws SQLException {
        System.out.println(choice.getValue());
        choice_ComboBox= choice.getValue();
        choice_value.setItems(FXCollections.observableArrayList(new DAOImpl().getDatainComboboxC()));
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
    void SearchC(ActionEvent event) throws SQLException {
        choice_ComboBox=choice.getValue();
        Value_ComboBox=choice_value.getValue();
        System.out.println(Value_ComboBox);
        System.out.println(choice_ComboBox);
        FillTableUsers(false);
    }
    @FXML
    void SearchH(ActionEvent event) throws SQLException {
        choice_ComboBox=choice.getValue();
        Value_ComboBox=choice_value.getValue();
        System.out.println(Value_ComboBox);
        System.out.println(choice_ComboBox);
        FillTableHotels(false);
    }


    @FXML
    void move_clients(ActionEvent event) throws IOException, SQLException {
        m.changeScene("ClientManagement.fxml",400,500);

    }
    @FXML
    void move_booking(ActionEvent event) throws IOException {
        m.changeScene("ManageBookingFirst.fxml",400,500);

    }

    @FXML
    void move_flights(ActionEvent event) throws IOException, SQLException {
        m.changeScene("FlightsManagement.fxml",400,500);

    }

    @FXML
    void move_hotels(ActionEvent event) throws IOException, SQLException {
        m.changeScene("HotelsMangement.fxml",400,500);

    }
    void clearH(){
        usernameTextField1.setText(null);
        usernameTextField11.setText(null);
        usernameTextField111.setText(null);
        usernameTextField13.setText(null);

    }
    @FXML
    void clearHotel(ActionEvent event) {
       clearH();
    }
    void clearC(){
        fullname.setText(null);
        phone.setText(null);
        password.setText(null);
        passport.setText(null);
        email.setText(null);

    }
    @FXML
    void clearClient(ActionEvent event) {
       clearC();
    }

    void clearF(){
        Cname.setText(null);
        price.setText(null);
        duration.setText(null);
        depTime.setText(null);
        depPlace.setText(null);
        ArrPlace.setText(null);
        ArrTime.setText(null);
        ArrDate.setValue(LocalDate.now());
        depDate.setValue(LocalDate.now());

    }
    @FXML
    void clearFlight(ActionEvent event) {
       clearF();
    }

    @FXML
    void selectFlights(MouseEvent event) {
        Flight f = tbl_Flights.getSelectionModel().getSelectedItem();
        System.out.println(f.getCompany_name());
        UpdateController.F1=f;
        AdminController.f1=f;
        UpdateController.update_Flight_CompanyName=f.getCompany_name();
        UpdateController.update_Flight_DepDate=f.getDeparture_date();
        UpdateController.update_Flight_ArrDate=f.getArrival_date();
        UpdateController.update_Flight_ArrTime=f.getArrival_time();
        UpdateController.update_Flight_DepTime=f.getDeparture_time();
        UpdateController.update_Flight_ArrPlace=f.getArrival_place();
        UpdateController.update_Flight_DepPlace=f.getDeparture_place();
        UpdateController.update_Flight_duration=f.getDuration();
        UpdateController.update_Flight_Price=f.getPrice();

    }
    @FXML
    void SelectHotels(MouseEvent event) {

        Hotel Y=hotel_table.getSelectionModel().getSelectedItem();
        System.out.println(Y.getHotel_name());
        UpdateController.h1=Y;
        AdminController.h1=Y;
        UpdateController.update_Hotel_Name=Y.getHotel_name();
        UpdateController.update_Hotel_Address=Y.getAddress();
        UpdateController.update_Hotel_Category=Y.getCategory();
        UpdateController.update_Hotel_Price=Y.getPrice_per_night();


    }
    @FXML
    void SelectClients(MouseEvent event) {
        User U= users_table.getSelectionModel().getSelectedItem();
        UpdateController.U1=U;
        AdminController.u1=U;
        UpdateController.update_Client_Name=U.getFullname();
        UpdateController.update_Client_Email=U.getEmail();
        UpdateController.update_Client_Password=U.getPassword();
        UpdateController.update_Client_Phone=U.getPhone();
        UpdateController.update_Client_Passport=U.getPassport_num();


    }

    @FXML
    void AddFlight(ActionEvent event) throws IOException {
        m.changeScene("AddFlight.fxml",400,500);

    }
    @FXML
    void UpdateFlight(ActionEvent event) throws IOException, SQLException {
        System.out.println(UpdateController.F1);
        System.out.println(UpdateController.F1.toString());
        if(!Objects.equals(AdminController.f1.toString(), "Flight(Company_name=null, departure_place=null, arrival_place=null, departure_time=null, arrival_time=null, departure_date=null, arrival_date=null, duration=null, price=0.0)")){
            m.changeScene("UpdateFlight.fxml",400,500);

        }
        else{
            System.out.println("SELECT A LINE");
            warning.setText("you need to select a line in the table to choose which flight you want to update");
        }
    }

    @FXML
    void DeleteFlight(ActionEvent event) throws IOException, SQLException {
        if(!Objects.equals(AdminController.f1.toString(), "Flight(Company_name=null, departure_place=null, arrival_place=null, departure_time=null, arrival_time=null, departure_date=null, arrival_date=null, duration=null, price=0.0)")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you sure you want to delete this flight ?? ", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                new DAOImpl().delete_flight();

            }
            m.changeScene("FlightsManagement.fxml",400,500);

        }
        else{
            System.out.println("SELECT A LINE");
            warning.setText("you need to select a line in the table to choose which flight you want to delete");

        }
    }
    @FXML
    void AddF(ActionEvent event) throws SQLException {
        if(Cname.getText()=="" || depTime.getText()=="" || ArrTime.getText()=="" || depPlace.getText()=="" || ArrPlace.getText() =="" || duration.getText() =="" || price.getText() ==""){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please , Make sure to fill all the fields !");
            alert.showAndWait();
        }
        else{
            new DAOImpl().saveFlight(
                    Flight.builder().Company_name(Cname.getText())
                            .departure_date(Date.valueOf(depDate.getValue()))
                            .arrival_date(Date.valueOf(ArrDate.getValue()))
                            .departure_time(depTime.getText())
                            .arrival_time(ArrTime.getText())
                            .departure_place(depPlace.getText())
                            .arrival_place(ArrPlace.getText())
                            .duration((duration.getText()))
                            .price(Double.parseDouble(price.getText()))
                            .build()
            );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("New Flight added Successfully !");
            alert.showAndWait();
            clearF();
        }


    }


    @FXML
    void backF(ActionEvent event) throws IOException, SQLException {
        move_flights(event);

    }

    /**************** Clients ********************/

    @FXML
    void AddClient(ActionEvent event) throws IOException {
        m.changeScene("AddClient.fxml",400,500);
    }

    @FXML
    void UpdateClient(ActionEvent event) throws IOException {
        System.out.println(UpdateController.U1);
        System.out.println(UpdateController.U1.toString());
        if(!Objects.equals(UpdateController.U1.toString(), "User(fullname=null, email=null, password=null, phone=0, passport_num=0)")){
            m.changeScene("UpdateClient.fxml",400,500);

        }
        else{
            System.out.println("SELECT A LINE");
            warning.setText("you need to select a line in the table to choose which client you want to update");

        }
    }
    @FXML
    void DeleteClient(ActionEvent event) throws IOException, SQLException {
        if(!Objects.equals(AdminController.u1.toString(), "User(fullname=null, email=null, password=null, phone=0, passport_num=0)")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you sure you want to delete this client ?? ", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                new DAOImpl().delete_client();

            }
            m.changeScene("ClientManagement.fxml",400,500);
        }
        else{
            System.out.println("SELECT A LINE");
            warning.setText("you need to select a line in the table to choose which client you want to delete");
        }
    }
    @FXML
    private TextField fullname;

    @FXML
    private TextField email;

    @FXML
    private TextField passport;

    @FXML
    private TextField password;

    @FXML
    private TextField phone;
    @FXML
    void AddC(ActionEvent event) throws SQLException {
        if(email.getText()=="" || fullname.getText()=="" || phone.getText()=="" || passport.getText()=="" || password.getText() ==""){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please , Make sure to fill all the fields !");
            alert.showAndWait();
        }
        else{
            new DAOImpl().saveClient(
                    User.builder().fullname(fullname.getText())
                            .email(email.getText())
                            .phone(Integer.parseInt(phone.getText()))
                            .passport_num(Integer.parseInt(passport.getText()))
                            .password(password.getText())
                            .build()
            );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("new Client added Successfully !");
            alert.showAndWait();
            clearC();
        }
    }

    @FXML
    void backC(ActionEvent event) throws IOException, SQLException {
        move_clients(event);
    }

    @FXML
    void AddHotel(ActionEvent event) throws IOException {
        m.changeScene("AddHotel.fxml",400,500);

    }
    @FXML
    void UpdateHotel(ActionEvent event) throws IOException {
        System.out.println(UpdateController.h1);
        System.out.println(UpdateController.h1.toString());
        if(!Objects.equals(UpdateController.h1.toString(), "Hotel(hotel_name=null, Category=null, Address=null, price_per_night=0.0)")){
            m.changeScene("UpdateHotel.fxml",400,500);


        }
        else{
            System.out.println("SELECT A LINE");
            warning.setText("you need to select a line in the table to choose which hotel you want to update");

        }


    }
    @FXML
    void DeleteHotel(ActionEvent event) throws SQLException, IOException {
        if(!Objects.equals(AdminController.h1.toString(), "Hotel(hotel_name=null, Category=null, Address=null, price_per_night=0.0)")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " Are you sure you want to delete this hotel ?? ", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                new DAOImpl().delete_hotel();
            }
            m.changeScene("HotelsMangement.fxml",400,500);

        }
        else{
            System.out.println("SELECT A LINE");
            warning.setText("you need to select a line in the table to choose which hotel you want to delete");

        }




    }

    @FXML
    private TextField usernameTextField1;

    @FXML
    private TextField usernameTextField11;

    @FXML
    private TextField usernameTextField111;

    @FXML
    private TextField usernameTextField13;
    @FXML
    void AddH(ActionEvent event) throws SQLException {
        if(usernameTextField1.getText()=="" || usernameTextField11.getText()=="" || usernameTextField111.getText()=="" || usernameTextField13.getText()==""  ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please , Make sure to fill all the fields !");
            alert.showAndWait();
        }
        else{
            new DAOImpl().saveHotel(
                    Hotel.builder().hotel_name(usernameTextField1.getText())
                            .Address(usernameTextField11.getText())
                            .Category(usernameTextField111.getText())
                            .price_per_night(Double.parseDouble(usernameTextField13.getText()))
                            .build()
            );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("New Hotel added Successfully !");
            alert.showAndWait();
            clearH();
        }


    }

    @FXML
    void backH(ActionEvent event) throws IOException, SQLException {
        move_hotels(event);

    }

    @FXML
    private TextField total;

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