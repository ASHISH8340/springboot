package com.gl.GloElectronics.controller;

import com.gl.GloElectronics.model.Customer;
import com.gl.GloElectronics.model.Product;

import com.gl.GloElectronics.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="v1/api")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @PostMapping(value = "addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.addCustomer(customer), HttpStatus.OK);
    }

    @PutMapping(value = "customer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody List<Product> product, @PathVariable Long customerId) {
        return new ResponseEntity<Customer>(customerService.updateCustomerProducts(customerId, product), HttpStatus.OK);
    }

    @DeleteMapping(value = "customer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.OK);
    }

    @GetMapping("customer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.ACCEPTED);
    }



    @GetMapping("/customers")
    public Page<Customer> getAllCustomersSortedByName(
            @RequestParam int pageNumber,
            @RequestParam int pageSize
    ) {
        return customerService.getAllCustomersSortedByName(pageNumber, pageSize);
    }



}
