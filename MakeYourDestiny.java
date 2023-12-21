import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

class TravelPlan {
    private String destination;
    private Map<Integer, List<String>> itinerary;
    private Map<String, Double> expenses;
    
    public void exportToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println("Travel Plan for " + destination);
            writer.println("Itinerary:");
            for (Map.Entry<Integer, List<String>> entry : itinerary.entrySet()) {
                writer.println("Day " + entry.getKey());
                for (String activity : entry.getValue()) {
                    writer.println("- " + activity);
                }
            }
            writer.println("\nExpenses:");
            for (Map.Entry<String, Double> entry : expenses.entrySet()) {
                writer.println("- " + entry.getKey() + ": $" + entry.getValue());
            }
            double total = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
            writer.println("Total expenses: $" + total);
            System.out.println("Travel plan exported to " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }

    public TravelPlan(String destination) {
        this.destination = destination;
        this.itinerary = new HashMap<>();
        this.expenses = new HashMap<>();
    }

    public void addActivity(int day, String activity) {
        itinerary.computeIfAbsent(day, k -> new ArrayList<>()).add(activity);
    }

    public void addExpense(String item, double cost) {
        expenses.put(item, cost);
    }

    public void displayItinerary() {
        System.out.println("Itinerary for " + destination);
        for (Map.Entry<Integer, List<String>> entry : itinerary.entrySet()) {
            System.out.println("Day " + entry.getKey());
            for (String activity : entry.getValue()) {
                System.out.println("- " + activity);
            }
        }
        System.out.println();
    }

    public void displayExpenses() {
        System.out.println("Expenses for " + destination);
        double total = 0;
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println("- " + entry.getKey() + ": $" + entry.getValue());
            total += entry.getValue();
        }
        System.out.println("Total expenses: $" + total);
    }

    public void sharePlan() {
        System.out.println("Sharing travel plan for " + destination);
        System.out.println("Itinerary:");
        displayItinerary();
        System.out.println("Expenses:");
        displayExpenses();
    }
}


public class MakeYourDestiny {
    public static void main(String[] args) {
        Map<String, TravelPlan> travelPlans = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        travelPlans.put("Paris", new TravelPlan("Paris"));
        travelPlans.get("Paris").addActivity(1, "Visit Eiffel Tower");
        travelPlans.get("Paris").addActivity(2, "Louvre Museum tour");
        travelPlans.get("Paris").addExpense("Hotel", 1200.0);
        travelPlans.get("Paris").addExpense("Food", 500.0);
        travelPlans.get("Paris").addActivity(3, "Seine River cruise");

        travelPlans.put("Tokyo", new TravelPlan("Tokyo"));
        travelPlans.get("Tokyo").addActivity(1, "Explore Shibuya");
        travelPlans.get("Tokyo").addActivity(2, "Visit Tokyo Tower");
        travelPlans.get("Tokyo").addExpense("Hotel", 1500.0);
        travelPlans.get("Tokyo").addExpense("Transportation", 600.0);
        travelPlans.get("Tokyo").addActivity(3, "Akihabara district visit");

        travelPlans.put("New York", new TravelPlan("New York"));
        travelPlans.get("New York").addActivity(1, "Central Park visit");
        travelPlans.get("New York").addActivity(2, "Times Square exploration");
        travelPlans.get("New York").addExpense("Hotel", 1800.0);
        travelPlans.get("New York").addExpense("Attractions", 700.0);
        travelPlans.get("New York").addActivity(3, "Statue of Liberty tour");

        travelPlans.put("London", new TravelPlan("London"));
        travelPlans.get("London").addActivity(1, "British Museum tour");
        travelPlans.get("London").addActivity(2, "Visit Buckingham Palace");
        travelPlans.get("London").addExpense("Hotel", 1300.0);
        travelPlans.get("London").addExpense("Food", 600.0);
        travelPlans.get("London").addActivity(3, "London Eye experience");


        while (true) {
            System.out.println("\n===== Travel Planner Menu =====");
            System.out.println("1. Create a new travel plan");
            System.out.println("2. Add activity to a travel plan");
            System.out.println("3. Add expense to a travel plan");
            System.out.println("4. Display itinerary");
            System.out.println("5. Display expenses");
            System.out.println("6. Share travel plan");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();
                    travelPlans.put(destination, new TravelPlan(destination));
                    System.out.println("Travel plan created for " + destination);
                    break;
                case "2":
                    System.out.print("Enter destination: ");
                    destination = scanner.nextLine();
                    if (travelPlans.containsKey(destination)) {
                        System.out.print("Enter day number: ");
                        int day = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter activity: ");
                        String activity = scanner.nextLine();
                        travelPlans.get(destination).addActivity(day, activity);
                    } else {
                        System.out.println("Travel plan doesn't exist for " + destination);
                    }
                    break;
                case "3":
                    System.out.print("Enter destination: ");
                    destination = scanner.nextLine();
                    if (travelPlans.containsKey(destination)) {
                        System.out.print("Enter item name: ");
                        String item = scanner.nextLine();
                        System.out.print("Enter cost: ");
                        double cost = Double.parseDouble(scanner.nextLine());
                        travelPlans.get(destination).addExpense(item, cost);
                    } else {
                        System.out.println("Travel plan doesn't exist for " + destination);
                    }
                    break;
                case "4":
                    System.out.print("Enter destination: ");
                    destination = scanner.nextLine();
                    if (travelPlans.containsKey(destination)) {
                        travelPlans.get(destination).displayItinerary();
                    } else {
                        System.out.println("Travel plan doesn't exist for " + destination);
                    }
                    break;
                case "5":
                    System.out.print("Enter destination: ");
                    destination = scanner.nextLine();
                    if (travelPlans.containsKey(destination)) {
                        travelPlans.get(destination).displayExpenses();
                    } else {
                        System.out.println("Travel plan doesn't exist for " + destination);
                    }
                    break;
                case "6":
                    System.out.print("Enter destination to export travel plan: ");
                    String destinationToExport = scanner.nextLine();
                    if (travelPlans.containsKey(destinationToExport)) {
                        System.out.print("Enter filename (e.g.,mytravelplan): ");
                        String filePath = scanner.nextLine(); // Ask for the file path
                        travelPlans.get(destinationToExport).exportToFile(filePath);
                    } else {
                        System.out.println("Travel plan doesn't exist for " + destinationToExport);
                    }
                    break;
                    
                case "7":
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
    
}
