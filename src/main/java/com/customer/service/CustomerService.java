package com.customer.service;

import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;
import com.customer.dto.UpdateCustomerRequest;
import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.util.CustomerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public CustomerResponse registerCustomer(CustomerRequest request) {
        CustomerResponse response = null;
        try {
            Customer customer = repository.save(CustomerUtil.requestToEntityConverter(request));
            response = CustomerUtil.entityToResponseConverter(customer);
            response.setStatus("Customer registered successfully .....!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<CustomerResponse> getCustomers() {
        List<CustomerResponse> responseList = null;
        try {
            List<Customer> entity = repository.findAll();
            responseList = CustomerUtil.entitiesToResponseList(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public CustomerResponse getCustomersById(String id) {
        CustomerResponse response = null;
        try {
            Optional<Customer> customer = repository.findByCustomerId(id);
            if (customer.isPresent()) {
                response = CustomerUtil.entityToResponseConverter(customer.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Transactional
    public Boolean deleteById(String id) {
        try {
            if (!repository.existsByCustomerId(id)) {
                return false;
            }
            repository.deleteByCustomerId(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public CustomerResponse updateCustomer(UpdateCustomerRequest request) {
        CustomerResponse response = null;
        try {
            Optional<Customer> availableCustomer = repository.findByCustomerId(request.getCustomerId());
            if (availableCustomer.isPresent()) {
                Customer existingCustomer = updateCustomer(request, availableCustomer);

                Customer customer = repository.save(existingCustomer);
                response = CustomerUtil.entityToResponseConverter(customer);
                response.setStatus(customer.getCustomerId() +": Customer updated successfully.....!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private static Customer updateCustomer(UpdateCustomerRequest request, Optional<Customer> availableCustomer) {
        Customer existingCustomer = availableCustomer.get();

        existingCustomer.setCustomerId(request.getCustomerId());
        existingCustomer.setFirstName(request.getFirstName());
        existingCustomer.setLastName(request.getLastName());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setState(request.getState());
        existingCustomer.setCity(request.getCity());
        existingCustomer.setPinCode(request.getPinCode());
        existingCustomer.setLandmark(request.getLandmark());
        return existingCustomer;
    }
}
