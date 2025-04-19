package com.customer.util;

import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;
import com.customer.entity.Customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CustomerUtil {

    public static Customer requestToEntityConverter(CustomerRequest request) {
        return Customer.builder()
                .customerId(UUID.randomUUID().toString())  // Custom ID generation
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .state(request.getState())
                .city(request.getCity())
                .pinCode(request.getPinCode())
                .registrationDate(new Date().toString())
                .landmark(request.getLandmark())
                .build();
    }

    public static CustomerResponse entityToResponseConverter(Customer customer) {
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .registrationDate(customer.getRegistrationDate())
                .email(customer.getEmail())
                .state(customer.getState())
                .city(customer.getCity())
                .pinCode(customer.getPinCode())
                .landmark(customer.getLandmark())
                .build();
    }

    public static List<CustomerResponse> entitiesToResponseList(List<Customer> customers) {
        List<CustomerResponse> responseList = null;
        if (!customers.isEmpty()) {
            responseList = new ArrayList<>();
            for (Customer customer : customers) {
                CustomerResponse response = new CustomerResponse();
                response.setCustomerId(customer.getCustomerId());
                response.setFirstName(customer.getFirstName());
                response.setLastName(customer.getLastName());
                response.setRegistrationDate(customer.getRegistrationDate());
                response.setEmail(customer.getEmail());
                response.setState(customer.getState());
                response.setCity(customer.getCity());
                response.setPinCode(customer.getPinCode());
                response.setLandmark(customer.getLandmark());
                responseList.add(response);
            }
        }
        return responseList;
    }
}