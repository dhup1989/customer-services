package com.customer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String state;
    private String city;
    private int pinCode;
    private String landmark;
}
