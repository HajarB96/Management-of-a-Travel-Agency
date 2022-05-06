package com.example.travelagency.Model;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Flight {
    private String Company_name,departure_place,arrival_place,departure_time,arrival_time;
    private Date departure_date,arrival_date;
    private String duration;
    private double price;

    public Flight() {
        super();
    }
}
