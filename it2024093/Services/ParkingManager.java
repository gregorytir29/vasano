package it2024093.Services;

import it2024093.Models.Driver;
import it2024093.Parking.*;
import it2024093.Models.Vehicle;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;

public class ParkingManager {

    private final ParkingLot parkingLot;

    private final HashMap<String,Vehicle> vehicleHistory; //History of Vehicles (Key = licensePlate)

    private final HashMap<String, Driver> driverHistory; //History of drivers (Key = phoneNumber)

    private HashMap<String, List<ParkingSession>> parkingHistory;

    private final List<ParkingSession> parkingSessions;

    public ParkingManager(){

        parkingLot = new ParkingLot();
        vehicleHistory = new HashMap<>();
        driverHistory = new HashMap<>();
        parkingSessions = new ArrayList<>();
        parkingHistory = new HashMap<>();
    }

    //Park a vehicle.
    public boolean parkVehicle(Vehicle vehicle, double duration) {

        //Add Vehicle and Driver to History
        vehicleHistory.putIfAbsent(vehicle.getLicensePlate(), vehicle);
        driverHistory.putIfAbsent(vehicle.getDriver().getPhoneNumber(),vehicle.getDriver());

        //Get the needed spots
        int neededSpots = vehicle.getParkingSize();

        if (!vehicle.isElectric()) {
            //Vehicle is normal, search only for normal spots.
            for (ParkingSpot spot : parkingLot.getSpots()) {
                if (!spot.isElectricOnly() && spot.isAvailable()) {
                    if (neededSpots == 1) {

                        //Crate new ParkingSession and occupy the spot
                        ParkingSession parkingSession = new ParkingSession(vehicle,vehicle.getDriver(),List.of(spot.getSpotNumber()),LocalDateTime.now(),duration);
                        spot.occupy(vehicle,parkingSession);
                        parkingHistory
                                .computeIfAbsent(vehicle.getLicensePlate(), licensePlate -> new ArrayList<>())
                                .add(parkingSession);

                        return true;
                    } else if (tryOccupyTwoSpots(spot, vehicle,duration, false)) {
                        //new ParkingSession and spot.occupy is done automatically.
                        return true;
                    }
                }
            }
        } else {
            //Vehicle is electric, search firstly for electric-only spots
            for (ParkingSpot spot : parkingLot.getSpots()) {
                if (spot.isElectricOnly() && spot.isAvailable()) {
                    if (neededSpots == 1) {
                        ParkingSession parkingSession = new ParkingSession(vehicle,vehicle.getDriver(),List.of(spot.getSpotNumber()),LocalDateTime.now(),duration);
                        spot.occupy(vehicle,parkingSession);
                        parkingHistory
                                .computeIfAbsent(vehicle.getLicensePlate(), licensePlate -> new ArrayList<>())
                                .add(parkingSession);
                        return true;
                    } else if (tryOccupyTwoSpots(spot, vehicle,duration, true)) {
                        return true;
                    }
                }
            }
            //No electric-only spot found, fallback to any normal spot
            for (ParkingSpot spot : parkingLot.getSpots()) {
                if (!spot.isElectricOnly() && spot.isAvailable()) {
                    if (neededSpots == 1) {
                        ParkingSession parkingSession = new ParkingSession(vehicle,vehicle.getDriver(),List.of(spot.getSpotNumber()),LocalDateTime.now(),duration);
                        spot.occupy(vehicle,parkingSession);
                        parkingHistory
                                .computeIfAbsent(vehicle.getLicensePlate(), licensePlate -> new ArrayList<>())
                                .add(parkingSession);
                        return true;
                    } else if (tryOccupyTwoSpots(spot, vehicle,duration, false)) {
                        return true;
                    }
                }
            }
        }
        return false; // No suitable spot found
    }

    //if needed spots are 2
    private boolean tryOccupyTwoSpots(ParkingSpot first, Vehicle vehicle,double duration, boolean mustBeElectric) {

        ParkingSpot next = getNextSpot(first); //Get the next parking spot
        if (next == null) return false; //Null check

        boolean validPair =
                next.isAvailable() && //first and foremost, the second spot must be available
                        (mustBeElectric && (first.isElectricOnly() && next.isElectricOnly()) //both the spots must be electric
                                || !mustBeElectric && (!first.isElectricOnly() && !next.isElectricOnly() //both the spots must be normal
                        )
                        );

        if (validPair) { //If the pair is valid, occupy both spots

            //new ParkingSession and spot.occupy is done automatically.
            List<Integer> occupiedSpots = List.of(first.getSpotNumber(),getNextSpot(first).getSpotNumber());
            ParkingSession parkingSession = new ParkingSession(vehicle,vehicle.getDriver(),occupiedSpots,LocalDateTime.now(),duration);
            first.occupy(vehicle,parkingSession);
            next.occupy(vehicle,parkingSession);
            parkingHistory
                    .computeIfAbsent(vehicle.getLicensePlate(), licensePlate -> new ArrayList<>())
                    .add(parkingSession);

            return true;
        }
        return false;
    }

    private ParkingSpot getNextSpot(ParkingSpot currentSpot) {
        int currentIndex = parkingLot.getSpots().indexOf(currentSpot);  // Find the index of the current spot
        if (currentIndex >= 0 && currentIndex < parkingLot.getSpots().size() - 1) {
            return parkingLot.getSpots().get(currentIndex + 1);  // Return the next spot
        } else {
            return null;  // No next spot if we're at the last spot
        }
    }

    public boolean unparkVehicle(Vehicle vehicle){
        for (ParkingSpot spot : parkingLot.getSpots()){
            if (vehicle == spot.getParkedVehicle()){ //Found the vehicle
                if (vehicle.getParkingSize()==1){
                    System.out.println("Cost= "+ spot.getParkingSession().calculateCost());
                    spot.vacate();
                    return true;
                }else {
                    System.out.println("Cost= "+ spot.getParkingSession().calculateCost());
                    spot.vacate();
                    if (getNextSpot(spot)!= null) { //Not really necessary, but better safe than sorry
                        getNextSpot(spot).vacate();
                        return true;
                    }

                }

            }
        }
        return false;
    }

    public void printStatus(){
        System.out.println(parkingLot.getNormalSpots());
        System.out.println(parkingLot.getElectricSpots());
    }

    public List<ParkingSession> getParkingHistory(String licensePlate){
        return parkingHistory.get(licensePlate);
    }

    public Vehicle searchbyLicensePlate(String licensePlate){


        return null;
    }

    public HashMap<String, Vehicle> getVehicleHistory() {
        return vehicleHistory;
    }

    public HashMap<String, Driver> getDriverHistory() {
        return driverHistory;
    }

    public void createtxt() throws IOException {
        parkingLot.statustxt();
    }

}