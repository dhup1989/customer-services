package com.customer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String registrationDate;
    private String status;
    private String state;
    private String city;
    private int pinCode;
    private String landmark;
}
