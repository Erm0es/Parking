package com.example.demo.Controllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entities.ParkingLot;
import com.example.demo.Repository.ParkingLotRepository;


@RestController
@RequestMapping("api")
public class ParkingLotController {

  public ParkingLotController(ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  ParkingLotRepository parkingLotRepository;

  public ParkingLotRepository getParkingSpotRepository() {
    return parkingLotRepository;
  }

  public void setParkingSpotRepository(ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  @PostMapping("/parkinglot")
  public ParkingLot points(@RequestBody ParkingLot parkings) {
    return parkingLotRepository.save(parkings);
  }

  
  @GetMapping("/parkingLot/{id}")
  public Optional<ParkingLot> getAllCustomerByID(@PathVariable("id") Long id) {
    var parkingLot = parkingLotRepository.findById(id);
    if (parkingLot.isPresent()) {
      return parkingLot;
    }
    return null;
  }

  @GetMapping("/parkinglot")
  public Iterable<ParkingLot> getAllParkingLots() {
    return parkingLotRepository.findAll();
  }

}