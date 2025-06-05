package it2024093.Models;

public class Truck extends Vehicle{

    private final float length;
    private final TruckUsageType usage;

    public Truck(String licensePlate,FuelType fuel, Driver driver, float length, TruckUsageType usage){
        super(VehicleType.TRUCK,licensePlate,2, fuel, driver);
        this.length = length;
        this.usage = usage;
    }

    public TruckUsageType getUsageType() {
        return usage;
    }

    public float getLength() {
        return length;
    }
}