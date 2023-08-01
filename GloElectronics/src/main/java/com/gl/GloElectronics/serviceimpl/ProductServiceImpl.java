package com.gl.GloElectronics.serviceimpl;

import com.gl.GloElectronics.model.Product;
import com.gl.GloElectronics.repository.ProductRepository;
import com.gl.GloElectronics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
