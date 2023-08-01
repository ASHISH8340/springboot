package com.gl.GloElectronics.service;

import com.gl.GloElectronics.model.Customer;
import com.gl.GloElectronics.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer updateCustomerProducts(Long customerId, List<Product> product);

    void deleteCustomer(Long customerId);

    List<Customer> getAllCustomer();

//    public List<Customer> getCustomers();
 Page<Customer> getAllCustomersSortedByName(int pageNumber, int pageSize);
}
