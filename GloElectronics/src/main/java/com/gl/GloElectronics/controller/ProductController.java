package com.gl.GloElectronics.controller;

import com.gl.GloElectronics.model.Customer;
import com.gl.GloElectronics.model.Product;
import com.gl.GloElectronics.service.CustomerService;
import com.gl.GloElectronics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "addProduct")
    public ResponseEntity<Product> addCustomer(@RequestBody Product product) {
        return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
    }
}
