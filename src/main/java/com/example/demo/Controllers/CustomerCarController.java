package com.example.demo.Controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.Car;
import com.example.demo.Entities.Customer;
import com.example.demo.Repository.CarRepository;
import com.example.demo.Repository.CustomerRepository;

@RestController
@RequestMapping("api")
public class CustomerCarController {
  
  CarRepository carRepository;
  CustomerRepository customerRepository;

  public CustomerCarController(CarRepository carRepository, CustomerRepository customerRepository){
    this.carRepository = carRepository;
    this.customerRepository = customerRepository;
  }  
  
  @PostMapping("/customer")
  public Customer addCustomer(@RequestBody Customer customer) {
    return customerRepository.save(customer);
  }

  @GetMapping("/customer")
  public Iterable<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @GetMapping("/customer/{id}")
  public Optional<Customer> getAllCustomerByID(@PathVariable("id") Long id) {
    var customer = customerRepository.findById(id);
    if (customer.isPresent()) {
      return customer;
    }
    return null;
  }

  @PostMapping("/customer/{id}/car")
  public Customer addCarToCustomer(@PathVariable("id") Long id, @RequestBody Car car) {
    Optional<Customer> customerRepo = customerRepository.findById(id);
    if (customerRepo.isPresent()) {
      Customer customer = customerRepo.get();
      customer.addCar(car);
      carRepository.save(car);
      return customerRepository.save(customer);
    } else {
      return null;
    }
  }

  @GetMapping("/cars")
  public Iterable<Car> getAllCars() {
    return carRepository.findAll();
  }

  
  @GetMapping("/cars/{id}")
  public Optional<Car> getAllCarsByID(@PathVariable("id") Long id) {
    var car = carRepository.findById(id);
    if (car.isPresent()) {
      return car;
    }
    return null;
  }

}
