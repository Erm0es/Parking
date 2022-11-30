package com.example.demo.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entities.Parking;


public interface ParkingRepository extends CrudRepository<Parking, Long> {
   @Query("""
            SELECT p FROM ParkingEvent p WHERE NOT (p.isActive) = true
            """)
    List<Parking> filterOnActiveParking();
  }


