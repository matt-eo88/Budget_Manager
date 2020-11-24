package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetManager {

    private final Scanner scan = new Scanner(System.in);
    private List<String> entries = new ArrayList<>();
    private float total;

    public void startSystem() {

        do {
            String input = scan.nextLine();
            entries.add(input);
            String[] parts = input.split("\\$");
            float moneyOut = Float.parseFloat(parts[1]);
            total += moneyOut;

        } while (scan.hasNextLine());

        entries.forEach(System.out::println);

        System.out.println();
        System.out.println("Total: $" + total);
    }
}
