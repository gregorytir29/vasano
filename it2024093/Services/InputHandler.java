package it2024093.Services;

import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler(){
        scanner = new Scanner(System.in);
    }

    public int printIntroMenu(int whichone) {
        if (whichone == 1) {
            //Menu for 1st choice (Fchoice)
            System.out.println("\n\nFunction Menu:");
            System.out.println("1.Park a Vehicle");
            System.out.println("2.Remove a Parked Vehicle");
            System.out.println("3.Additional Information");
            System.out.println("4.Output current Parked Vehicles in a .txt file");
            System.out.println("5. Exit");
            System.out.print("Please select a function:");
            return getIntBetween(1, 5);

        } else {
            //Menu for choice 3 (Schoice)
            System.out.println("\n\nSearch Menu:");
            System.out.println("1.Search Driver history");
            System.out.println("2.Parking Spot Report (Normal or Electric Only)");
            System.out.println("3.Search Vehicle history");
            System.out.print("Please select a function:");
            return getIntBetween(1, 3);
        }
    }

    public int getIntBetween(int min,int max){ //Ensures user int input is between min and max
        int value;
        while (true) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine(); // clear buffer
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Input has to be between " + min + " and " + max + "!");
                }
            } else {
                System.out.println("Input has to be an integer!");
                scanner.next(); // discard invalid input
            }
        }
        
    }
    
    public float getFloatBetween(float min,float max){ //Same as getInt but for floats
        float value;
        while (true) {
            if (scanner.hasNextFloat()) {
                value = scanner.nextFloat();
                scanner.nextLine(); // clear buffer
                if (value >= min && (value <= max || max==-1)){ //Πολύ hardcoded αλλά υγεία
                    return value;
                } else {
                    System.out.println("Input has to be between " + min + " and " + max + "!");
                }
            } else {
                System.out.println("Input has to be a positive float!");
                scanner.next(); // discard invalid input
            }
        }
        
    }
    
    public String getValidPhoneNumber(){
        while (true) {
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            // Check if the phone number is valid
            if (phoneNumber.matches("\\d{10}")) {  // Checks for a 10-digit phone number
                return phoneNumber;
            } else {
                System.out.println("Invalid phone number. Please enter a 10-digit number.");
                scanner.next(); // discard invalid input
            }
        }
    }
    
    public String inputLicensePlate;
    
    public String noCheckString(){
        return scanner.nextLine();
    }


    public void close(){
        scanner.close();
    }
}
