package com.example.travelagency.Model;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelsBooking {
    private String full_name, hotel_name, address ,date,passport_num;
    private int nbr_rooms;
}