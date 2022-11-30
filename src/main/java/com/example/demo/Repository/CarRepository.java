package com.example.demo.Repository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entities.Car;

public interface CarRepository extends CrudRepository<Car, Long>{
  
}
