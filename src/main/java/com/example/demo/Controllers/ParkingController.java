package com.example.demo.Controllers;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ValidateTime;
import com.example.demo.Entities.Car;
import com.example.demo.Entities.Parking;
import com.example.demo.Entities.ParkingLot;
import com.example.demo.Repository.CarRepository;
import com.example.demo.Repository.ParkingLotRepository;
import com.example.demo.Repository.ParkingRepository;

@RestController
@RequestMapping("api")
public class ParkingController {

  CarRepository carRepository;
  ParkingLotRepository parkingLotRepository;
  ParkingRepository parkingRepository;
  ValidateTime validateTime;

  public ParkingController(CarRepository carRepository, ParkingLotRepository parkingLotRepository,
      ParkingRepository parkingRepository, ValidateTime validateTime) {
    this.carRepository = carRepository;
    this.parkingLotRepository = parkingLotRepository;
    this.parkingRepository = parkingRepository;
    this.validateTime = validateTime;
  }

  @PostMapping("/parking")
  public Parking startParking(@RequestBody Parking parking) {
    var stoptime = parking.getstopTime();
    if (validateTime.isInFuture(stoptime)) {
      Long carID = parking.getCar().getId();
      var carOptional = carRepository.findById(carID);
      if (carOptional.isPresent()) {
        Car car = carOptional.get();
        car.addParking(parking);
        carRepository.save(car);
      }
      Long parkingLotID = parking.getParkingLot().getId();
      var parkingLotOptional = parkingLotRepository.findById(parkingLotID);
      if (parkingLotOptional.isPresent()) {
        ParkingLot parkingLot = parkingLotOptional.get();
        parkingLot.addParking(parking);
        parkingLotRepository.save(parkingLot);
        return parkingRepository.save(parking);
      }

    }
    return null;
  }

  @GetMapping("/parking")
  public Iterable<Parking> getAllParking() {
    return parkingRepository.findAll();
  }

  @GetMapping("/parking/{id}")
  public Optional<Parking> getAllParkingByID(@PathVariable("id") Long id) {
    var parking = parkingRepository.findById(id);
    if (parking.isPresent()) {
      return parking;
    }
    return null;
  }

  @PatchMapping("/parking/{id}")
  public Parking updateStopTime(@PathVariable("id") Long id, @RequestBody Parking parking) {

    var parkingOptional = parkingRepository.findById(id);
    LocalDateTime newStopTime = parking.getstopTime();
    if (parkingOptional.isPresent() && validateTime.isInFuture(newStopTime)) {
      Parking parkings = parkingOptional.get();
      parkings.setstopTime(newStopTime);
      return parkingRepository.save(parkings);
    } else
      return null;
  }

  public CarRepository getCarRepository() {
    return carRepository;
  }

  public void setCarRepository(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public ParkingLotRepository getParkingLotRepository() {
    return parkingLotRepository;
  }

  public void setParkingLotRepository(ParkingLotRepository parkingLotRepository) {
    this.parkingLotRepository = parkingLotRepository;
  }

  public ParkingRepository getParkingRepository() {
    return getParkingRepository();
  }

  public void setParkingRepository(ParkingRepository parkingRepository) {
    this.parkingRepository = parkingRepository;
  }

}
