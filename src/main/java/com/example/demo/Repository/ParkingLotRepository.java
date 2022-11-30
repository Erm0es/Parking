package com.example.demo.Repository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.Entities.ParkingLot;


public interface ParkingLotRepository extends CrudRepository<ParkingLot,Long>{
  
}


