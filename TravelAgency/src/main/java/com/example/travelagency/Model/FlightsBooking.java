package com.example.travelagency.Model;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class FlightsBooking {
    private String Full_name, N_passport,CampanyName,dep_time,Arr_time,Dep_place,Arr_place;
    private Double price;
}
