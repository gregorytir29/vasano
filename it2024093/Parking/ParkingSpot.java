
package it2024093.Parking;

import it2024093.Models.Vehicle;

public class ParkingSpot {
    
    private final int spotNumber;
    private boolean isAvailable;
    private Vehicle parkedVehicle;
    private final boolean isElectricOnly;
    private ParkingSession parkingSession;
    
    public ParkingSpot(int spotNumber, boolean isElectricOnly){
        this.spotNumber = spotNumber;
        this.isAvailable = true;
        this.isElectricOnly = isElectricOnly;
        this.parkedVehicle = null;
        this.parkingSession = null;

    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public String getParkedVehicleLP(){
        return parkedVehicle.getLicensePlate();
    }

    public String getDriverPhoneNumber(){
        return parkedVehicle.getDriver().getPhoneNumber();
    }

    public String getDate(){

        return parkingSession.getStartTime();
    }

    public boolean isElectricOnly() {
        return isElectricOnly;
    }

    public void occupy(Vehicle vehicle, ParkingSession parkingSession){ //Vehicle parks in spot(s)
        this.isAvailable = false;
        this.parkedVehicle = vehicle;
        this.parkingSession = parkingSession;
    }
    
    public void vacate(){ //Vehicle leaves, spot(s) are available again
        this.isAvailable = true;
        this.parkedVehicle = null;
        this.parkingSession = null;
    }

    public ParkingSession getParkingSession() {
        return parkingSession;
    }
}