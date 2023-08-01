package com.gl.GloElectronics.repository;

import com.gl.GloElectronics.model.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer> findAll(Sort sort);
}
