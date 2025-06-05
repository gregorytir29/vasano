package it2024093.Parking;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private final List<ParkingSpot> spots;

    public ParkingLot() {
        spots = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            boolean electricOnly = i > 80;
            spots.add(new ParkingSpot(i, electricOnly));
        }
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public String getNormalSpots(){
        int free=0,occupied=0;
        for (ParkingSpot spot : spots){
            if (!spot.isElectricOnly()) {
                if (spot.isAvailable()) {
                    free++;
                } else {
                    occupied++;
                }
            }
        }
        return "Normal Spots: Free= " + free + " Taken= " + occupied;
    }

    public String getElectricSpots(){
        int free=0,occupied=0;
        for (ParkingSpot spot : spots){
            if (spot.isElectricOnly()) {
                if (spot.isAvailable()) {
                    free++;
                } else {
                    occupied++;
                }
            }
        }
        return "Electric Spots: Free= " + free + " Taken= " + occupied;


    }

    public void statustxt() throws IOException {
        File f = new File("status.txt");
        PrintWriter writer = new PrintWriter(f);

        for (ParkingSpot spot: spots) {
            if (spot.getParkedVehicle() != null) {
                writer.println(spot.getSpotNumber() + "\t" + spot.getParkedVehicleLP() + "\t" + spot.getDriverPhoneNumber() + "\t" + spot.getDate());
            }else{
                writer.println(spot.getSpotNumber());
            }
        }
        writer.close();
    }
}

