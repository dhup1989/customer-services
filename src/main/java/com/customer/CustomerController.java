package com.customer;

import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;
import com.customer.dto.UpdateCustomerRequest;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody CustomerRequest request){
        return new ResponseEntity<>(service.registerCustomer(request), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return  ResponseEntity.ok(service.getCustomers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(service.getCustomersById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String id){
        return ResponseEntity.ok(service.deleteById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody UpdateCustomerRequest request){
        return new ResponseEntity<>(service.updateCustomer(request), HttpStatus.OK);
    }
}
