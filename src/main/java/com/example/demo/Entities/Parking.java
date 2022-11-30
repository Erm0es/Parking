package com.example.demo.Entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Parking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  
  @CreationTimestamp
  @Column(name= "timeStamp", nullable = false, updatable = false)
  public LocalDateTime timeStamp;

  @UpdateTimestamp
  public LocalDateTime updated;

  @Column(nullable = false)
  public LocalDateTime stopTime;


  @ColumnDefault("true")
  public Boolean isActive = true;
  
  
  public Boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }

  @ManyToOne
  private Car car;

  @ManyToOne
  private ParkingLot parkingLot;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreated() {
    return timeStamp;
  }

  public void setCreated(LocalDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  public LocalDateTime getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDateTime updated) {
    this.updated = updated;
  }

  public LocalDateTime getstopTime() {
    return stopTime;
  }

  public void setstopTime(LocalDateTime stopTime) {
    this.stopTime = stopTime;
  }


  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public ParkingLot getParkingLot() {
    return parkingLot;
  }

  public void setParkingLot(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }
}
    

