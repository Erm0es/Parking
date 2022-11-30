package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.Entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
}