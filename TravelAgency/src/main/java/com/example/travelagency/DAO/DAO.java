package com.example.travelagency.DAO;
import com.example.travelagency.Model.*;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;

public interface DAO {
    List<String> getDatainComboboxF() throws SQLException;
    ObservableList<Flight> SearchFlight(ObservableList<Flight> oblist) throws SQLException;
    List<String> getDatainComboboxH() throws SQLException;
    ObservableList<Hotel> SearchHotel(ObservableList<Hotel> oblist) throws SQLException;
    List<String> getDatainComboboxC() throws SQLException;
    ObservableList<User> SearchClient(ObservableList<User> oblist) throws SQLException;
    void saveFlight(Flight f) throws SQLException;
    void saveClient(User c) throws SQLException;
    void saveHotel(Hotel h) throws SQLException;
    void UpdateFlight(Flight f) throws SQLException;
    void UpdateHotel(Hotel H) throws SQLException;
    ObservableList<Hotel> ShowAllHotels(ObservableList<Hotel> oblist1) throws SQLException;
    void UpdateClient(User c) throws SQLException;
    ObservableList<Flight> ShowAllFlights(ObservableList<Flight> oblist) throws SQLException;
    ObservableList<User> ShowAllUsers(ObservableList<User> oblist) throws SQLException;
    void delete_hotel() throws SQLException;
    void delete_flight() throws SQLException;
    void delete_client() throws SQLException;
    ObservableList<FlightsBooking> ShowAllFlightsBooking(ObservableList<FlightsBooking> oblist) throws SQLException;
    void saveFlightsBooking(String n1, String n2) throws SQLException;
    List<String> getDatainComboboxBF1() throws SQLException;
    List<String> getDatainComboboxBF2() throws SQLException;
    ObservableList<Flight> SearchBF(ObservableList<Flight> oblist) throws SQLException;
    void saveHotelsBooking(String m1, String m2, String m3, String m4) throws SQLException;
    ObservableList<HotelsBooking> ShowAllHotelsBooking(ObservableList<HotelsBooking> oblist1) throws SQLException;
    void CancelBookingFlight() throws SQLException;
    void CancelBookingHotel() throws SQLException;
}
