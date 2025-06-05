package it2024093.Models;

public abstract class Vehicle {

    private final VehicleType type;
    private final String licensePlate;
    private final int parkingSize; //1 or 2
    private final FuelType fuel;
    private final Driver driver;


    public Vehicle(VehicleType type, String licensePlate, int parkingSize, FuelType fuel, Driver driver) {
        this.type = type;
        this.licensePlate = licensePlate;
        this.parkingSize = parkingSize;
        this.fuel = fuel;
        this.driver = driver;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getParkingSize() {
        return parkingSize;
    }

    public Driver getDriver() {
        return driver;
    }

    public boolean isElectric(){ //Returns true if vehicle is electric.
        return fuel == FuelType.ELECTRIC;
    }

    public FuelType getFuel(){
        return fuel;
    }
}
