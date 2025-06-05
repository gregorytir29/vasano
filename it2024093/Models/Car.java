package it2024093.Models;

public class Car extends Vehicle{

    public Car(String licensePlate, FuelType fuel, Driver driver) {
        super(VehicleType.CAR,licensePlate, 1, fuel, driver);
    }
}

