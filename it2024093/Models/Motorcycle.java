
package it2024093.Models;

public class Motorcycle extends Vehicle {

    public Motorcycle(String licensePlate,FuelType fuel, Driver driver) {
        super(VehicleType.MOTORCYCLE,licensePlate, 1, fuel, driver);
    }

}
