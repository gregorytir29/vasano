package it2024093.Services;

import it2024093.Models.*;

public class VehicleCreator {
    public Vehicle createVehicle(InputHandler handleInput, Driver driver, String licensePlate){
        //Outputs Menu and handles Input for Vehicle creation

        System.out.println("Select Vehicle Type: 1. Car  2. Motorcycle  3. Truck");
        int choice = handleInput.getIntBetween(1, 3);
        VehicleType vehicleChoice;
        float truckLength = 0;
        TruckUsageType truckUsageType = null;

        if (choice == 1){
            vehicleChoice = VehicleType.CAR;
        }else if(choice == 2){
            vehicleChoice = VehicleType.MOTORCYCLE;
        }else{
            vehicleChoice = VehicleType.TRUCK;
            System.out.print("Provide Truck length:");
            truckLength = handleInput.getFloatBetween(0,-1);//input;
            System.out.print("Select Truck Usage Type:");

            System.out.println("Select Truck Usage Type: \n1. Food Transportation  \n2. Object Transportation  \n3. Delivery");
            System.out.print("Please provide your choice:");
            truckUsageType = switch (handleInput.getIntBetween(1,3)){
                case 2 -> TruckUsageType.OBJECT;
                case 3 -> TruckUsageType.DELIVERY;
                default -> TruckUsageType.FOOD;
            };

        }

        // Ensure fuel type is selected correctly
        System.out.println("Select Fuel Type: 1. Benzine  2. Diesel  3. Electric");

        FuelType fuel = switch (handleInput.getIntBetween(1, 3)) {
            case 2 -> FuelType.DIESEL;
            case 3 -> FuelType.ELECTRIC;
            default -> FuelType.BENZINE; //automatically defaults to benzine
        };

        switch (vehicleChoice){
            case VehicleType.CAR -> {
                return new Car(licensePlate,fuel,driver);
            }

            case VehicleType.MOTORCYCLE -> {
                return new Motorcycle(licensePlate,fuel,driver);
            }

            case VehicleType.TRUCK -> {
                return new Truck(licensePlate,fuel,driver,truckLength,truckUsageType);
            }
        };
        throw new IllegalArgumentException("An error has occurred while creating the Vehicle, exiting...");
    }

    public Driver createDriver(InputHandler handleInput){
        // Create Driver object based on user input
        System.out.println("Enter Driver Details:");
        // Collect driver info (name, surname, phone number)
        System.out.print("Name: ");
        String name = handleInput.noCheckString();

        System.out.print("Surname: ");
        String surname = handleInput.noCheckString();

        String phoneNumber = handleInput.getValidPhoneNumber();

        return new Driver(name,surname,phoneNumber);
    }

}
