package budget;

import java.util.*;

public class BudgetManager {

    private final Scanner scan = new Scanner(System.in);
    private Map<String, Float> purchases = new HashMap<>();
    private float balance = 0.0F;
    private boolean exit = false;

    public void startSystem() {

        do {
            printMenu();
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    addIncome();
                    break;
                case "2":
                    addPurchase();
                    break;
                case "3":
                    listPurchases();
                    break;
                case "4":
                    showBalance();
                    break;
                case "0":
                    exit();
                    break;
                default:
                    System.out.println("Please choose one of the listed options");
                    System.out.println();
            }



        } while (!exit);
    }

    private void printMenu() {
        System.out.println("Choose your action:\n" + "1) Add income\n" +
                "2) Add purchase\n" + "3) Show list of purchases\n" +
                "4) Balance\n" + "0) Exit");
    }

    private void addIncome() {
        System.out.println();
        System.out.println("Enter income:");
        try {
            float income = Float.parseFloat(scan.nextLine());
            balance += income;
        } catch (NumberFormatException n) {
            System.out.println("Income must be a number");
            return;
        }
        System.out.println("Income was added!");
        System.out.println();
    }

    private void addPurchase() {
        System.out.println();
        System.out.println("Enter purchase name:");
        String purchase = scan.nextLine();
        if (purchase.isEmpty()) {
            System.out.println("Purchase name can't be empty");
            System.out.println();
            return;
        }
        System.out.println("Enter its price:");
        try {
            float price = Float.parseFloat(scan.nextLine());
            purchases.put(purchase, price);
            balance -= price;
        } catch (NumberFormatException n) {
            System.out.println("Price must be a number");
            System.out.println();
            return;
        }
        System.out.println("Purchase was added!");
        System.out.println();
    }

    private void listPurchases() {
        System.out.println();
        if (purchases.isEmpty()) {
            System.out.println("Purchase list is empty");
            System.out.println();
            return;
        }
        purchases.forEach((k, v) ->
                System.out.println(k + "\n" + "Total: $" + v));
        System.out.println();
    }

    private void showBalance() {
        System.out.println();
        System.out.println("Balance: $" + balance);
        System.out.println();
    }

    private void exit() {
        exit = true;
        System.out.println();
        System.out.println("Bye!");
        System.out.println();
    }

}
