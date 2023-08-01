package com.gl.GloElectronics.serviceimpl;


import com.gl.GloElectronics.exception.NotFoundException;
import com.gl.GloElectronics.model.Customer;
import com.gl.GloElectronics.model.Product;
import com.gl.GloElectronics.repository.CustomerRepository;
import com.gl.GloElectronics.repository.ProductRepository;
import com.gl.GloElectronics.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getCustomerName() == null || customer.getCustomerName().isEmpty()) {
            throw new NotFoundException("Customer name cannot be empty");
        }else{
            Customer savedCustomer = customerRepository.save(customer);
            return savedCustomer;
        }
    }

    @Override
    public Customer updateCustomerProducts(Long customerId, List<Product> product) {
        if (product == null || product.isEmpty()) {
            throw new NotFoundException("Product details cannot be empty");
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer with ID " + customerId + " does not exist");
        }

        Customer customer = optionalCustomer.get();
        customer.setProduct(product);
        customerRepository.save(customer);
        return customer;


    }





    @Override
    public void deleteCustomer(Long customerId) {
        if(!customerRepository.existsById(customerId)){
            throw new NotFoundException("Customer with ID " + customerId + " does not exist");
        }
        customerRepository.deleteById(customerId);

    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> getAllCustomersSortedByName(int pageNumber, int pageSize) {
            Sort sort = Sort.by(Sort.Direction.ASC, "customerName");
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
            return customerRepository.findAll(pageable);
    }





}
