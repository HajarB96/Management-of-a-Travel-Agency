package com.example.travelagency.DAO;

import com.example.travelagency.Controller.AdminController;
import com.example.travelagency.Controller.ClientController;
import com.example.travelagency.Controller.UpdateController;
import com.example.travelagency.Model.*;
import javafx.collections.ObservableList;
import lombok.ToString;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.travelagency.Controller.AdminController.isAdmin;

public class DAOImpl implements DAO {
    ConnDB con = new ConnDB();
    AdminController a = new AdminController();

    public DAOImpl() throws SQLException {
    }

    /**
     * Fonction d'affichage les hotels disponibles dans la table hotels
     * @param oblist1
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Hotel> ShowAllHotels(ObservableList<Hotel> oblist1) throws SQLException {
        String req = "SELECT * FROM hotels ";
        ResultSet rs;
        System.out.println("step1");
        try {

            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                oblist1.add(
                        Hotel.builder()
                                .hotel_name(rs.getString("hotel_name"))
                                .Address(rs.getString("address"))
                                .Category(rs.getString("category"))
                                .price_per_night(rs.getDouble("price_per_night"))
                                .build()
                );
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return oblist1;

    }

    /**
     * Fonction d'affichage les clients disponibles dans la table users
     * @param oblist
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<User> ShowAllUsers(ObservableList<User> oblist) throws SQLException {
        String req = "SELECT * FROM users where status=\"client\";";
        ResultSet rs = null;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                oblist.add(
                        User.builder()
                                .fullname(rs.getString("fullname"))
                                .email(rs.getString("email"))
                                .phone(rs.getInt("phone"))
                                .passport_num(rs.getInt("passport_num"))
                                .password(rs.getString("password"))
                                .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }

    /**
     * Obtenir les éléments de Combobox à partir de la base de données
     * @return
     */
    @Override
    public List<String> getDatainComboboxF() throws SQLException {
        String choice;
        if(isAdmin) {
            System.out.println("in Admin");
            choice = AdminController.choice_ComboBox.trim();
        }
        else {
            System.out.println("in Client");
            choice = ClientController.choice_ComboBox.trim();
        }

        System.out.println(choice);
        List<String> options = new ArrayList<>();
        String req="select * from flights" ;
        ResultSet rs;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                options.add(rs.getString(choice));
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return options;
    }

    /**
     * Fonction qui effectue une recherche des vols suivant un critère de recherche particulier de la table flights
     * @param oblist
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Flight> SearchFlight(ObservableList<Flight> oblist) throws SQLException {
       String choice,value;
        if(isAdmin) {
            choice = AdminController.choice_ComboBox;
            value = AdminController.Value_ComboBox;
        }
        else {
            choice = ClientController.choice_ComboBox;
            value = ClientController.Value_ComboBox;
        }
        String req="";
        if(choice.equals("departure_place")) req="SELECT * from flights where departure_place =  '" + value + "'" ;
        else if(choice.equals("arrival_place")) req="SELECT * from flights where arrival_place =  '" + value + "'" ;
        else if(choice.equals("arrival_date")) req="SELECT * from flights where arrival_date =  '" + value + "'" ;
        else if(choice.equals("departure_date")) req="SELECT * from flights where departure_date =  '" + value + "'" ;
        ResultSet rs ;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()){
                oblist.add(
                        Flight.builder()
                                .Company_name(rs.getString("Campany_name"))
                                .departure_date(rs.getDate("departure_date"))
                                .arrival_date(rs.getDate("arrival_date"))
                                .departure_time(rs.getString("departure_time"))
                                .arrival_time(rs.getString("arrival_time"))
                                .departure_place(rs.getString("departure_place"))
                                .arrival_place(rs.getString("arrival_place"))
                                .duration(rs.getString("duration"))
                                .price(rs.getDouble("price"))
                                .build()
                );
            }
            System.out.println("doone");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oblist;
    }
    /**
     * Obtenir les éléments de Combobox à partir de la base de données
     * @return
     */
    @Override
    public List<String> getDatainComboboxH() throws SQLException {
        String choice;
        if(isAdmin) {
            System.out.println("in Admin");
            choice = AdminController.choice_ComboBox.trim();
        }
        else {
            System.out.println("in Client");
            choice = ClientController.choice_ComboBox.trim();
        }

        System.out.println(choice);
        List<String> options = new ArrayList<>();
        String req="select * from hotels" ;
        ResultSet rs;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                options.add(rs.getString(choice));
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return options;
    }

    /**
     *  Fonction qui effectue une recherche d'hotel suivant un critère de recherche particulier de la table hotels
     * @param oblist
     * @return
     * @throws SQLException
     */

    @Override
    public ObservableList<Hotel> SearchHotel(ObservableList<Hotel> oblist) throws SQLException {
        String choice,value;
        if(isAdmin) {
            choice = AdminController.choice_ComboBox;
            value = AdminController.Value_ComboBox;
        }
        else {
            choice = ClientController.choice_ComboBox;
            value = ClientController.Value_ComboBox;
        }

        String req="";
        if(choice.equals("hotel_name")) req="SELECT * from hotels where hotel_name =  '" + value + "'" ;
        else if(choice.equals("address")) req="SELECT * from hotels where address =  '" + value + "'" ;
        ResultSet rs ;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()){
                oblist.add(
                        Hotel.builder()
                                .hotel_name(rs.getString("hotel_name"))
                                .Address(rs.getString("address"))
                                .Category(rs.getString("category"))
                                .price_per_night(rs.getDouble("price_per_night"))
                                .build()
                );
            }
            System.out.println("doone");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oblist;
    }
    /**
     * Obtenir les éléments de Combobox à partir de la base de données
     * @return
     */
    @Override
    public List<String> getDatainComboboxC() throws SQLException {
        String choice = AdminController.choice_ComboBox.trim();
        System.out.println(choice);
        List<String> options = new ArrayList<>();
        String req="select * from users where status=\"client\"" ;
        ResultSet rs;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                options.add(rs.getString(choice));
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return options;
    }

    /**
     * Fonction qui effectue une recherche des clients suivant un critère de recherhce particulier de la table users
     * @param oblist
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<User> SearchClient(ObservableList<User> oblist) throws SQLException {
        String choice = AdminController.choice_ComboBox;
        String value = AdminController.Value_ComboBox;
        String req="";
        if(choice.equals("fullname")) req="SELECT * from users where hotel_name =  '" + value + "'" ;
        else if(choice.equals("email")) req="SELECT * from users where email =  '" + value + "'" ;
        else if(choice.equals("phone")) req="SELECT * from users where phone =  '" + value + "'" ;
        else if(choice.equals("passport_num")) req="SELECT * from users where passport_num =  '" + value + "'" ;
        ResultSet rs ;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()){
                oblist.add(
                        User.builder()
                                .fullname(rs.getString("fullname"))
                                .email(rs.getString("email"))
                                .phone(rs.getInt("phone"))
                                .passport_num(rs.getInt("passport_num"))
                                .password(rs.getString("password"))
                                .build()
                );
            }
            System.out.println("doone");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oblist;
    }

    /**
     * Fonction d'ajout d'un vol dans la table flights
     * @param f
     * @throws SQLException
     */
    @Override
    public void saveFlight(Flight f) throws SQLException {
        String req = "INSERT INTO flights(Campany_name,departure_date,arrival_date,departure_time,arrival_time,departure_place,arrival_place,duration,price) values(?,?,?,?,?,?,?,?,?);";
        int r = 0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req);
            prst.setString(1,f.getCompany_name());
            prst.setDate(2,f.getDeparture_date());
            prst.setDate(3,f.getArrival_date());
            prst.setString(4,f.getArrival_time());
            prst.setString(5,f.getDeparture_time());
            prst.setString(6,f.getArrival_place());
            prst.setString(7,f.getDeparture_place());
            prst.setString(8,f.getDuration());
            prst.setDouble(9,f.getPrice());
            r= prst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(r);
    }

    /**
     * Fonction de mise à jour d'un vol dans la table flights
     * @param f
     * @throws SQLException
     */

    @Override
    public void UpdateFlight(Flight f) throws SQLException {

        String name= UpdateController.F1.getCompany_name();
        String req1="SELECT id from flights where Campany_name=  '" + name + "'" ;
        ResultSet rs= con.getSt().executeQuery(req1);
        int countResult = 0;
        if(rs.next()){
            countResult = rs.getInt("id");
            System.out.println(countResult);
        }
        else System.out.println("Empty");

        String req2="Update flights set Campany_name=?,departure_date=?,arrival_date=?,departure_time=?,arrival_time=?,departure_place=?,arrival_place=?,duration=?,price=? where id="+countResult;
        int r2=0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req2);
            prst.setString(1,f.getCompany_name());
            prst.setDate(2,f.getDeparture_date());
            prst.setDate(3,f.getArrival_date());
            prst.setString(4,f.getArrival_time());
            prst.setString(5,f.getDeparture_time());
            prst.setString(6,f.getArrival_place());
            prst.setString(7,f.getDeparture_place());
            prst.setString(8,f.getDuration());
            prst.setDouble(9,f.getPrice());
            r2= prst.executeUpdate();
            System.out.println("Update Flight done !!!");
            System.out.println(r2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Fonction de mise à jour d'un hotel dans la table hotels
     * @param H
     * @throws SQLException
     */
    @Override
    public void UpdateHotel(Hotel H) throws SQLException {
        String name= UpdateController.h1.getHotel_name();
        String req1="SELECT id from hotels where hotel_name=  '" + name + "'" ;
        ResultSet rs= con.getSt().executeQuery(req1);
        int countResult = 0;
        if(rs.next()){
            countResult = rs.getInt("id");
            System.out.println(countResult);
        }
        else System.out.println("Empty");
        String req2="Update hotels set hotel_name=?,address=?,category=?,price_per_night=? where id="+countResult;

        int r2=0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req2);
            prst.setString(1,H.getHotel_name());
            prst.setString(2,H.getAddress());
            prst.setString(3,H.getCategory());
            prst.setDouble(4,H.getPrice_per_night());
            r2= prst.executeUpdate();
            System.out.println("Update Hotel done !!!");
            System.out.println(r2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Fonction de  mise à jour d'un client dans la table users
     * @param c
     * @throws SQLException
     */
    @Override
    public void UpdateClient(User c) throws SQLException {
        String name= UpdateController.U1.getFullname();
        String req1="SELECT id from users where fullname=  '" + name + "'" ;
        ResultSet rs= con.getSt().executeQuery(req1);
        int countResult = 0;
        if(rs.next()){
            countResult = rs.getInt("id");
            System.out.println(countResult);
        }
        else System.out.println("Empty");
        String req2="Update users set fullname=?,email=?,phone=?,passport_num=?,password=? where id="+countResult;

        int r2=0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req2);
            prst.setString(1,c.getFullname());
            prst.setString(2,c.getEmail());
            prst.setInt(3,c.getPhone());
            prst.setInt(4,c.getPassport_num());
            prst.setString(5,c.getPassword());
            r2= prst.executeUpdate();
            System.out.println("Update Client done !!!");
            System.out.println(r2);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Fonction d'affichage des vols disponibles de la table flights
     * @param oblist
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Flight> ShowAllFlights(ObservableList<Flight> oblist) throws SQLException {
        String req = "SELECT * FROM flights ";
        ResultSet rs = null;
        try {

            rs = con.getSt().executeQuery(req);
            while (rs.next()){
                oblist.add(
                        Flight.builder()
                                .Company_name(rs.getString("Campany_name"))
                                .departure_date(rs.getDate("departure_date"))
                                .arrival_date(rs.getDate("arrival_date"))
                                .departure_time(rs.getString("departure_time"))
                                .arrival_time(rs.getString("arrival_time"))
                                .departure_place(rs.getString("departure_place"))
                                .arrival_place(rs.getString("arrival_place"))
                                .duration(rs.getString("duration"))
                                .price(rs.getDouble("price"))
                                .build()
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oblist;

    }

    /**
     * Fonction d'ajout d'un client dans la table users
     * @param u
     * @throws SQLException
     */
    @Override
    public void saveClient(User u) throws SQLException {
        String req = "INSERT INTO Users(fullname,email,phone,passport_num,password,status) values(?,?,?,?,?,'client');";
        int r = 0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req);
            prst.setString(1,u.getFullname());
            prst.setString(2,u.getEmail());
            prst.setInt(3,u.getPhone());
            prst.setInt(4,u.getPassport_num());
            prst.setString(5,u.getPassword());
            r= prst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(r);
    }

    /**
     * Fonction d'ajout d'un hotel dans la table hotels
     * @param h
     * @throws SQLException
     */
    @Override
    public void saveHotel(Hotel h) throws SQLException {
        String req = "INSERT INTO hotels(hotel_name,address,category,price_per_night) values(?,?,?,?);";
        int r = 0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req);
            prst.setString(1,h.getHotel_name());
            prst.setString(2,h.getAddress());
            prst.setString(3,h.getCategory());
            prst.setDouble(4,h.getPrice_per_night());
            r= prst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(r);
    }

    /**
     * Fonction de suppression d'un hotel de la table hotels
     * @throws SQLException
     */
    @Override
    public void delete_hotel() throws SQLException {
        String name = AdminController.h1.getHotel_name();
        String req1 = "SELECT id from hotels where hotel_name=  '" + name + "'";
        ConnDB conn = new ConnDB();
        ResultSet rs = conn.getSt().executeQuery(req1);
        int countResult = 0;
        if (rs.next()) {
            countResult = rs.getInt("id");
            System.out.println(countResult);
        } else System.out.println("Empty");
        System.out.println(name);


        String req = "delete FROM hotels where id='"+countResult+"'";

        try {
            PreparedStatement prst = conn.getCon().prepareStatement(req);

            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Fonction de suppression d'un vol de la table flights
     * @throws SQLException
     */
    @Override
    public void delete_flight() throws SQLException {
        String name = AdminController.f1.getCompany_name();
        String req1 = "SELECT id from flights where campany_name=  '" + name + "'";
        ConnDB conn = new ConnDB();
        ResultSet rs = conn.getSt().executeQuery(req1);
        int countResult = 0;
        if (rs.next()) {
            countResult = rs.getInt("id");
            System.out.println(countResult);
        } else System.out.println("Empty");
        System.out.println(name);
        String req = "delete FROM flights where id='"+countResult+"'";
        try {
            PreparedStatement prst = conn.getCon().prepareStatement(req);

            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     *  Fonction de suppression d'un client de la tables users
     * @throws SQLException
     */
    @Override
    public void delete_client() throws SQLException {
        String name = AdminController.u1.getFullname();
        String req1 = "SELECT id from Users where fullname=  '" + name + "'";
        ConnDB conn = new ConnDB();
        ResultSet rs = conn.getSt().executeQuery(req1);
        int countResult = 0;
        if (rs.next()) {
            countResult = rs.getInt("id");
            System.out.println(countResult);
        } else System.out.println("Empty");
        System.out.println(name);


        String req = "delete FROM Users where id='"+countResult+"'";

        try {
            PreparedStatement prst = conn.getCon().prepareStatement(req);

            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fonction d'affichage des réservations de vols de la table flightbooking
     * @param oblist
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<FlightsBooking> ShowAllFlightsBooking(ObservableList<FlightsBooking> oblist) throws SQLException {
        String req = "SELECT * FROM flightsbooking;";
        ResultSet rs = null;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                oblist.add(
                        FlightsBooking.builder()
                                .Full_name(rs.getString("Full_name"))
                                .N_passport(rs.getString("N_passport"))
                                .CampanyName(rs.getString("CampanyName"))
                                .dep_time(rs.getString("dep_time"))
                                .Arr_time(rs.getString("Arr_time"))
                                .Dep_place(rs.getString("Dep_place"))
                                .Arr_place(rs.getString("Arr_place"))
                                .price(rs.getDouble("price"))
                                .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oblist;
    }

    /**
     * Fonction d'ajout d'une réservation de vol dans la table flightbooking
     * @param n1
     * @param n2
     * @throws SQLException
     */
    @Override
    public void saveFlightsBooking(String n1,String n2) throws SQLException {
        String name,departDate,arrivalDate,depPlace,ArrPlace;
        Double Price;
        if(isAdmin){
            name=AdminController.f1.getCompany_name();
            departDate=AdminController.f1.getDeparture_date()+" à " + AdminController.f1.getDeparture_time();
            arrivalDate=AdminController.f1.getArrival_date() +" à " + AdminController.f1.getArrival_time();
            depPlace=AdminController.f1.getDeparture_place();
            ArrPlace=AdminController.f1.getArrival_place();
            Price=AdminController.f1.getPrice();
        }
        else{
            name=ClientController.f1.getCompany_name();
            departDate=ClientController.f1.getDeparture_date()+" à " + ClientController.f1.getDeparture_time();
            arrivalDate=ClientController.f1.getArrival_date() +" à " + ClientController.f1.getArrival_time();
            depPlace=ClientController.f1.getDeparture_place();
            ArrPlace=AdminController.f1.getArrival_place();
            Price=ClientController.f1.getPrice();
        }
        String req = "INSERT INTO flightsbooking(Full_name,N_passport,CampanyName,dep_time,Arr_time,Dep_place,Arr_place,price) values(?,?,?,?,?,?,?,?);";
        int r = 0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req);
            prst.setString(1,n1);
            prst.setString(2,n2);
            prst.setString(3,name);
            prst.setString(4,departDate);
            prst.setString(5,arrivalDate);
            prst.setString(6,depPlace);
            prst.setString(7,ArrPlace);
            prst.setDouble(8,Price);
            r= prst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(r);

    }
    /**
     * Obtenir les éléments de Combobox à partir de la base de données
     * @return
     */
    @Override
    public List<String> getDatainComboboxBF1() throws SQLException {
        List<String> options = new ArrayList<>();
        String req="select * from flights" ;
        ResultSet rs;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                options.add(rs.getString("departure_place"));
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return options;
    }
    /**
     * Obtenir les éléments de Combobox à partir de la base de données
     * @return
     */
    @Override
    public List<String> getDatainComboboxBF2() throws SQLException {
        List<String> options = new ArrayList<>();
        String req="select * from flights";
        ResultSet rs;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                options.add(rs.getString("arrival_place"));
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return options;
    }
    /**
     * Fonction qui effectue une recherche des vols disponibles suivant un critère de recherche particulier de la table flights
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<Flight> SearchBF(ObservableList<Flight> oblist) throws SQLException {
        String choice1 ;
        String choice2 ;
        if(isAdmin){
            choice1 = AdminController.Arr_ComboBox;
            choice2 = AdminController.Dep_ComboBox;
        }
        else{
            choice1 = ClientController.Arr_ComboBox;
            choice2 = ClientController.Dep_ComboBox;
        }

        String req;
        if(choice1==null && choice2==null) req="SELECT * FROM flights ";
        else if(choice1 == null ) req= "SELECT * FROM flights where departure_place = '" + choice2 + "'";
        else if(choice2 == null ) req= "SELECT * FROM flights where arrival_place = '" + choice1 + "'";
        else req= "SELECT * FROM flights where arrival_place = '" + choice1 + "' and departure_place = '" + choice2+"'" ;
        ResultSet rs ;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()){
                oblist.add(
                        Flight.builder()
                                .Company_name(rs.getString("Campany_name"))
                                .departure_date(rs.getDate("departure_date"))
                                .arrival_date(rs.getDate("arrival_date"))
                                .departure_time(rs.getString("departure_time"))
                                .arrival_time(rs.getString("arrival_time"))
                                .departure_place(rs.getString("departure_place"))
                                .arrival_place(rs.getString("arrival_place"))
                                .duration(rs.getString("duration"))
                                .price(rs.getDouble("price"))
                                .build()
                );
            }
            System.out.println("doone");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(oblist);
        for (Flight f : oblist) System.out.println(f);
        return oblist;
    }

    /**
     * Fonction d'ajout d'une réservation d'hotel dans la table hotelbooking
     * @param m1
     * @param m2
     * @param m3
     * @param m4
     * @throws SQLException
     */
    @Override
    public void saveHotelsBooking(String m1,String m2,String m3,String m4) throws SQLException {
        String req = "INSERT INTO hotelbooking(client_name,Hotel_name,Address,Arr_time,N_passport,Nbr_rooms) values(?,?,?,?,?,?);";
        String  HotelN,Address;
        if(isAdmin){
            HotelN=AdminController.h1.getHotel_name();
            Address=AdminController.h1.getAddress();
        }
        else{
            HotelN=ClientController.h1.getHotel_name();
            Address=ClientController.h1.getAddress();
        }
        int r = 0;
        try{
            PreparedStatement prst = con.getCon().prepareStatement(req);
            prst.setString(1,m1);
            prst.setString(2,HotelN);
            prst.setString(3,Address);
            prst.setString(4,m2);
            prst.setString(5,m3);
            prst.setInt(6, Integer.parseInt(m4));
            r= prst.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(r);
    }

    /**
     * Fonction d'affichage des réservations d'hotels de la table hotelbooking
     * @param oblist1
     * @return
     * @throws SQLException
     */
    @Override
    public ObservableList<HotelsBooking> ShowAllHotelsBooking(ObservableList<HotelsBooking> oblist1) throws SQLException {
        String req = "SELECT * FROM hotelbooking";
        ResultSet rs;
        try {
            rs = con.getSt().executeQuery(req);
            while (rs.next()) {
                oblist1.add(
                        HotelsBooking.builder()
                                .full_name(rs.getString("client_name"))
                                .hotel_name(rs.getString("Hotel_name"))
                                .address(rs.getString("Address"))
                                .date(rs.getString("Arr_time"))
                                .passport_num(rs.getString("N_passport"))
                                .nbr_rooms(rs.getInt("Nbr_rooms"))
                                .build()
                );
            }
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }

        return oblist1;

    }

    /**
     * Fonction d'annulation d'une réservation de vol de la table flightbooking
     * @throws SQLException
     */
    @Override
    public void CancelBookingFlight() throws SQLException {
        String name = AdminController.fb.getFull_name();
        String req1 = "SELECT id from flightsbooking where Full_name=  '" + name + "'";
        ConnDB conn = new ConnDB();
        ResultSet rs = conn.getSt().executeQuery(req1);
        int Result = 0;
        if (rs.next()) {
            Result = rs.getInt("id");
        } else System.out.println("Empty");
        System.out.println(name);
        String req = "delete FROM flightsbooking where id='"+Result+"'";
        try {
            PreparedStatement prst = conn.getCon().prepareStatement(req);
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * Fonction d'annulation d'une réservation de vol de la table hotelbooking
     * @throws SQLException
     */
    @Override
    public void CancelBookingHotel() throws SQLException {
        String name = AdminController.hb.getFull_name();
        String req1 = "SELECT id from hotelbooking where client_name=  '" + name + "'";
        ConnDB conn = new ConnDB();
        ResultSet rs = conn.getSt().executeQuery(req1);
        int Result = 0;
        if (rs.next()) {
            Result = rs.getInt("id");
        } else System.out.println("Empty");
        System.out.println(name);
        String req = "delete FROM hotelbooking where id='"+Result+"'";
        try {
            PreparedStatement prst = conn.getCon().prepareStatement(req);
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }
}


