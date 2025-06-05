package it2024093;

import it2024093.Models.*;
import it2024093.Services.*;

import java.io.IOException;

public class It2024093 {

    public static void main(String[] args) {

        // Initialize ParkingManager, InputHandler and VehicleCreator
        ParkingManager parkingManager = new ParkingManager();
        InputHandler handleInput = new InputHandler();
        VehicleCreator create = new VehicleCreator();

        //Let's assume the first spot is taken
        Driver d1 = new Driver("Tzimakos", "Panousis", "6912345678");
        Vehicle v1 = new Car("ABC123", FuelType.BENZINE, d1);
        parkingManager.parkVehicle(v1,5); //park the vehicle

        boolean loop;
        String licensePlate = null;
        FuelType fuel;
        int Fchoice=0;
        do {

            Fchoice =  handleInput.printIntroMenu(1); //Print 1st menu of choices

            int Schoice = 0;
            if (Fchoice == 3){

                Schoice = handleInput.printIntroMenu(2); //Print 2nd menu of choices
            }

            if (Fchoice < 3 || Schoice == 3){
                //All these choices need the same input
                System.out.println("Enter Vehicle Details:");

                System.out.print("License Plate: ");

                licensePlate = handleInput.noCheckString();
            }

            if (Fchoice == 1){

                Vehicle vehicle;
                Driver driver;
                if (parkingManager.getVehicleHistory().get(licensePlate) == null) {
                    //The entry is new (assuming the driver is linked to the vehicle)

                    // Create new Driver object
                    driver = create.createDriver(handleInput);

                    // Create new Vehicle object based on the above input
                    vehicle = create.createVehicle(handleInput,driver, licensePlate);
                }else{
                    // Vehicle already exists
                    vehicle = parkingManager.getVehicleHistory().get(licensePlate);
                    driver = vehicle.getDriver();
                }

                System.out.print("Parking Duration: ");
                double duration = handleInput.getFloatBetween(0, 24);

                if (parkingManager.parkVehicle(vehicle,duration)) {
                    System.out.println("Parking Success!");
                } else {
                    System.out.println("No available Spot!");
                }

            } else if (Fchoice == 2) {
                if(parkingManager.getVehicleHistory().get(licensePlate) == null){
                    System.out.println("Vehicle doesn't exist!");
                }else{
                    if(parkingManager.unparkVehicle(parkingManager.getVehicleHistory().get(licensePlate))) {System.out.println("Vehicle was unparked succesfully!");}
                    //...
                }
            } else if (Fchoice == 3) {
                if (Schoice == 1){
                    String phoneNumber = handleInput.getValidPhoneNumber();


                }else if (Schoice == 2){
                    parkingManager.printStatus();
                }else{
                   if (parkingManager.getParkingHistory(licensePlate)==null){
                       System.out.println("History could not be found");
                   }else{
                       if (parkingManager.getParkingHistory(licensePlate) == null){
                           System.out.println("Driver has no history.");
                       }else{
                           System.out.println(parkingManager.getParkingHistory(licensePlate).toString());
                       }
                   }
                }

            }else if (Fchoice == 4){
                //Create the txt file
                try {
                    parkingManager.createtxt();
                    System.out.println(".txt file has been generated!");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } while (Fchoice != 5);
        System.out.println("Exiting... Goodbye!");
        handleInput.close();
    }
}