package budget;

import java.util.*;

import static budget.BudgetManager.scan;

public class CategoriesManager {

    private Map<String, Float> food = new HashMap<>();
    private Map<String, Float> clothes = new HashMap<>();
    private Map<String, Float> entertainment = new HashMap<>();
    private Map<String, Float> other = new HashMap<>();
    private List<Map<String, Float>> allPurchases = new ArrayList<>();

    private float balance = 0.0f;
    private String header;

    public CategoriesManager() {
        allPurchases.add(food);
        allPurchases.add(clothes);
        allPurchases.add(entertainment);
        allPurchases.add(other);
    }

    public void addPurchase(Options val) {
        Map<String, Float> purchases = getPertinentList(val);

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
    }

    public void listPurchases(Options val) {
        Map<String, Float> purchases = getPertinentList(val);

        System.out.println();
        if (purchases.isEmpty()) {
            System.out.println("Purchase list is empty");
            return;
        }
        System.out.println(header);
        purchases.forEach((k, v) -> System.out.println(k + " $" + v));
        System.out.println("Total sum: $" + getTotalSum(purchases.values()));

    }

    public Map<String, Float> getPertinentList(Options val) {
        if (val == Options.FOOD) {
            header = "Food:";
            return food;
        }
        if (val == Options.CLOTHES) {
            header = "Clothes:";
            return clothes;
        }
        if (val == Options.ENTERTAINMENT) {
            header = "Entertainment:";
            return entertainment;
        }
        if (val == Options.OTHER) {
            header = "Other:";
            return other;
        }

        return new HashMap<>();
    }

    public void printAllPurchases() {
        System.out.println();
        System.out.println("All:");
        float total = 0.0f;
        for (Map<String, Float> val : allPurchases) {
            if (!val.isEmpty()) {
                val.forEach((k, v) -> System.out.println(k + " $" + v));
            }
            total += getTotalSum(val.values());
        }
        System.out.println("Total sum: $" + total);
    }

    private float getTotalSum(Collection<Float> amounts) {
        float total = 0.0f;
        for (float f : amounts) {
            total += f;
        }
        return total;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float val) {
        balance = val;
    }

    public void addBalance(float income) {
        this.balance += income;
    }

}
