package com.example.travelagency.Model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
@Builder
public class Booking {
    private String client_name, object, departure_place, arrival_place, hotel_name, TypesOfrooms;
    private int passport_num, nbr_rooms;
    private Date booking_date , departure_time , arrival_time;
}
