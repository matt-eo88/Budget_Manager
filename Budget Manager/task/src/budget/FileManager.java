package budget;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class saves/loads data from/to file
 */
public class FileManager {

    private CategoriesManager categories;
    private List<Options> options = new ArrayList<>(Arrays.asList(Options.FOOD,
            Options.CLOTHES, Options.ENTERTAINMENT, Options.OTHER));

    FileManager(CategoriesManager categoriesManager) {
        this.categories = categoriesManager;
    }

    public void saveToFile() {
        saveBalance(); // saves the balance first
        for (Options opt : options) {

            String path = determinePath(opt); // creates file name based on category
            Map<String, Float> val = categories.getPertinentList(opt); // gets the pertinent in memory data
            if (val.isEmpty()) {
                continue;
            }

            try {
                File file = new File("purchases/" + path + ".txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter buffer = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(buffer);

                val.forEach((k, v) -> {
                    printWriter.println(k + ":" + v);
                });

                printWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("Purchases were saved!\n");
    }

    public void loadFromFile() {
        loadBalance(); // loads balance first
        for (Options opt : options) {
            String path = determinePath(opt); // creates file name based on category
            Map<String, Float> val = categories.getPertinentList(opt); // returns pertinent list

            try {

                File file = new File("purchases/" + path + ".txt");
                if (file.exists()) {
                    BufferedReader buffer = new BufferedReader(new FileReader(file));

                    String content;
                    while ((content = buffer.readLine()) != null) {
                        String[] parts = content.split(":");
                        String key = parts[0];
                        float value = Float.parseFloat(parts[1]);
                        val.put(key, value);
                    }

                    buffer.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("Purchases were loaded!\n");
    }

    /**
     * Creates file name based on passed category
     *
     * @param opt = enum
     * @return = String
     */
    private String determinePath(Options opt) {
        String path;
        if (opt == Options.FOOD) {
            path = "food_expenses";
            return path;
        }
        if (opt == Options.CLOTHES) {
            path = "clothes_expenses";
            return path;
        }
        if (opt == Options.ENTERTAINMENT) {
            path = "entertainment_expenses";
            return path;
        }
        if (opt == Options.OTHER) {
            path = "other_expenses";
            return path;
        }
        return "";
    }

    private void saveBalance() {
        try {
            File file = new File("purchases/balance.txt");
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(categories.getBalance());
            printWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBalance() {
        try {
            File file = new File("purchases/balance.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String content = bufferedReader.readLine();
            float balance = Float.parseFloat(content);
            categories.setBalance(balance);
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
