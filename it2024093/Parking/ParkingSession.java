package it2024093.Parking;

import it2024093.Models.Driver;
import it2024093.Models.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParkingSession {
    private final Vehicle vehicle;
    private final Driver driver;

    // Περιέχει τις θέσεις στάθμευσης
    private final List<Integer> spotNumbers;

    private final LocalDateTime startTime;
    private final double timeUsed;

    public ParkingSession(Vehicle vehicle, Driver driver, List<Integer> spotNumbers, LocalDateTime startTime, double timeUsed) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.spotNumbers = spotNumbers;
        this.startTime = startTime;
        this.timeUsed = timeUsed;
    }

    public int calculateCost(){
        if (timeUsed<=0){
            return 0;
        }else if ( timeUsed <= 3){
            return 5;
        }else if (timeUsed <= 8){
            return 8;
        }else if (timeUsed <= 23){
            return 12;
        }else{
            return 15;
        }
    }

    public Vehicle getVehicle() { return vehicle; }
    public Driver getDriver() { return driver; }
    public List<Integer> getSpotNumbers() { return spotNumbers; }
    public String getStartTime() {
        String pattern = "yyyy/dd/MM \t HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return startTime.format(formatter);
    }
    public double getTimeUsed() { return timeUsed; }
    public void endSession(){

    }

    @Override
    public String toString() {
        return  "Πινακίδα: " + vehicle.getLicensePlate() +
                ", Οδηγός: " + driver.toString() +
                ", Θέση: " + spotNumbers +
                ", Ώρα: " + startTime +
                ", Cost: " + "calculateCost()";
    }


}
