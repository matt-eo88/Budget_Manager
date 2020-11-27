package budget;

import java.util.*;

import static budget.Options.FOOD;
import static budget.Options.CLOTHES;
import static budget.Options.ENTERTAINMENT;
import static budget.Options.OTHER;

/**
 * This class shows the relevant info to the user
 * it also gets user input and passes it on to the
 * CategoryManager class
 */
public class BudgetManager {

    final static Scanner scan = new Scanner(System.in);
    private CategoriesManager categories = new CategoriesManager();
    private FileManager fileManager = new FileManager(categories);
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
                    startAddPurchaseMenu();
                    break;
                case "3":
                    startListPurchaseMenu();
                    break;
                case "4":
                    showBalance();
                    break;
                case "5":
                    fileManager.saveToFile();
                    break;
                case "6":
                    fileManager.loadFromFile();
                    break;
                case "0":
                    exit();
                    break;
                default:
                    System.out.println("Please choose one of the listed options");
                    System.out.println();
            }
        } while (!exit);
        scan.close();
    }

    private void printMenu() {
        System.out.println("Choose your action:\n" + "1) Add income\n" +
                "2) Add purchase\n" + "3) Show list of purchases\n" +
                "4) Balance\n" + "5) Save\n" + "6) Load\n" + "0) Exit");
    }

    private void printAddPurchaseMenu() {
        System.out.println();
        System.out.println("Choose the type of purchase\n" + "1) Food\n" +
                "2) Clothes\n" + "3) Entertainment\n" + "4) Other\n" +
                "5) Back");
    }

    private void printListPurchaseMenu() {
        System.out.println();
        System.out.println("Choose the type of purchase\n" + "1) Food\n" +
                "2) Clothes\n" + "3) Entertainment\n" + "4) Other\n" +
                "5) All\n" + "6) Back");
    }

    private void addIncome() {
        System.out.println();
        System.out.println("Enter income:");
        try {
            float income = Float.parseFloat(scan.nextLine());
            categories.addBalance(income);
        } catch (NumberFormatException n) {
            System.out.println("Income must be a number");
            return;
        }
        System.out.println("Income was added!");
        System.out.println();
    }

    private void startAddPurchaseMenu() {
        boolean exit = false;
        while (!exit) {
            printAddPurchaseMenu();
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    categories.addPurchase(FOOD);
                    break;
                case "2":
                    categories.addPurchase(CLOTHES);
                    break;
                case "3":
                    categories.addPurchase(ENTERTAINMENT);
                    break;
                case "4":
                    categories.addPurchase(OTHER);
                    break;
                case "5":
                    exit = true;
                    System.out.println();
                    break;
                default:
                    System.out.println("Please choose one of the listed options");
                    System.out.println();
            }
        }
    }

    private void startListPurchaseMenu() {
        boolean exit = false;
        while (!exit) {
            printListPurchaseMenu();
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    categories.listPurchases(FOOD);
                    break;
                case "2":
                    categories.listPurchases(CLOTHES);
                    break;
                case "3":
                    categories.listPurchases(ENTERTAINMENT);
                    break;
                case "4":
                    categories.listPurchases(OTHER);
                    break;
                case "5":
                    categories.printAllPurchases();
                    break;
                case "6":
                    exit = true;
                    System.out.println();
                    break;
                default:
                    System.out.println("Please choose one of the listed options");
                    System.out.println();
            }
        }
    }

    private void showBalance() {
        System.out.println();
        System.out.println("Balance: £" + categories.getBalance());
        System.out.println();
    }

    private void exit() {
        exit = true;
        System.out.println();
        System.out.println("Bye!");
        System.out.println();
    }

}
