package com.example.travelagency.Model;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Hotel {

    private String hotel_name,Category,Address;
    private double price_per_night;

    public Hotel() {
        super();
    }
}
