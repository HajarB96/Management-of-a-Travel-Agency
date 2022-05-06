package com.example.travelagency.Model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString

public class User {
    private String fullname,email ,password;
    private int phone , passport_num ;
    public User(){
        super();
    }
}
