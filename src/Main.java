import dao.UserDAO;
import model.User;
import util.DatabaseInitializer;
import java.util.Scanner;

public class Main {
    private static UserDAO userDAO = new UserDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseInitializer.initializeDatabase();
        
        while (true) {
            System.out.println("\n=== Emergency Contact Alert System ===");
            System.out.println("1. Register New User");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerNewUser(scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void registerNewUser(Scanner scanner) {
        System.out.println("\n-- Register New User --");
        String name = "";
        while (name.isEmpty() || !name.matches("[a-zA-Z ]{3,50}")) {
            System.out.print("Name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty() || !name.matches("[a-zA-Z ]{3,50}")) {
                System.out.println("Error: Name must be 3-50 characters long and contain only letters");
            }
        }

        int age = -1;
        while (age < 0 || age > 120) {
            System.out.print("Age: ");
            try {
                age = Integer.parseInt(scanner.nextLine().trim());
                if (age < 0 || age > 120) {
                    System.out.println("Error: Age must be between 0 and 120");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for age");
            }
        }

        String bloodGroup = "";
        while (bloodGroup.isEmpty() || !bloodGroup.matches("^(A|B|AB|O)[+-]$")) {
            System.out.print("Blood Group (e.g., A+, B-, AB+, O-): ");
            bloodGroup = scanner.nextLine().trim().toUpperCase();
            if (bloodGroup.isEmpty() || !bloodGroup.matches("^(A|B|AB|O)[+-]$")) {
                System.out.println("Error: Blood group must be in the format A+, B-, AB+, or O-");
            }
        }

        int heartRate = -1;
        while (heartRate < 40 || heartRate > 200) {
            System.out.print("Heart Rate (bpm): ");
            try {
                heartRate = Integer.parseInt(scanner.nextLine().trim());
                if (heartRate < 40 || heartRate > 200) {
                    System.out.println("Error: Heart rate must be between 40 and 200 bpm");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for heart rate");
            }
        }

        String bloodPressure = "";
        while (bloodPressure.isEmpty() || !bloodPressure.matches("^\\d{2,3}/\\d{2,3}$")) {
            System.out.print("Blood Pressure (e.g., 120/80): ");
            bloodPressure = scanner.nextLine().trim();
            if (bloodPressure.isEmpty() || !bloodPressure.matches("^\\d{2,3}/\\d{2,3}$")) {
                System.out.println("Error: Blood pressure must be in the format systolic/diastolic (e.g., 120/80)");
            } else {
                String[] parts = bloodPressure.split("/");
                int systolic = Integer.parseInt(parts[0]);
                int diastolic = Integer.parseInt(parts[1]);
                if (systolic < 60 || systolic > 250 || diastolic < 40 || diastolic > 150) {
                    System.out.println("Error: Systolic must be 60-250 and diastolic must be 40-150");
                    bloodPressure = "";
                }
            }
        }

        String emergencyContact = "";
        while (emergencyContact.isEmpty() || !emergencyContact.matches("^\\d{10}$")) {
            System.out.print("Emergency Contact (10 digits): ");
            emergencyContact = scanner.nextLine().trim();
            if (emergencyContact.isEmpty() || !emergencyContact.matches("^\\d{10}$")) {
                System.out.println("Error: Emergency contact must be 10 digits");
            }
        }

        User user = new User(name, age, bloodGroup, heartRate, bloodPressure, emergencyContact);
        if (userDAO.addUser(user)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Failed to register user.");
        }
    }
}
