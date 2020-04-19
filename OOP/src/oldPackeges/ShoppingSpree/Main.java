package oldPackeges.ShoppingSpree;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split(";");

        Map<String, Person> personMap = new HashMap<>();
        for (String string : input) {
            String[] split = string.split("=");
            String name = split[0];
            double money = Double.parseDouble(split[1]);
            Person person = new Person(name, money);
            personMap.putIfAbsent(name, person);

        }

        String[] inputLine2 = scanner.nextLine().split(";");

        Map<String, Product> productMap = new HashMap<>();
        for (String string : inputLine2) {
            String[] split = string.split("=");
            String name = split[0];
            double cost = Double.parseDouble(split[1]);
            Product product = new Product(name, cost);
            productMap.putIfAbsent(name, product);
        }
        String line = scanner.nextLine();

        while (!"END".equals(line)) {
            String[] tokens = line.split("\\s+");
            String name = tokens[0];
            String product = tokens[1];
            try {
                personMap.get(name).buyProduct(productMap.get(product));
                System.out.println(name + " bought " + product);
            } catch (IllegalStateException ex) {
                System.out.println(ex.getMessage());
            }
            line = scanner.nextLine();
        }

        for (Map.Entry<String, Person> entry : personMap.entrySet()) {
            System.out.println(entry.getValue());
        }

    }
}