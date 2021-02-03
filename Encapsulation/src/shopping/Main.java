package shopping;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Person> people = new LinkedHashMap<>();
        String[] tokens = scanner.nextLine().split(";");
        for (String token : tokens) {
            String[] personInfo = token.split("=");
            try {
                Person person = new Person(personInfo[0], Double.parseDouble(personInfo[1]));
                people.put(person.getName(), person);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }


        Map<String, Product> products = new LinkedHashMap<>();
        String[] tokens2 = scanner.nextLine().split(";");
        for (String token : tokens2) {
            String[] productInfo = token.split("=");
            try {
                Product product = new Product(productInfo[0], Double.parseDouble(productInfo[1]));
                products.put(product.getName(), product);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }

        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String[] tokens3 = input.split("\\s+");

            String person = tokens3[0];
            String productName = tokens3[1];

            try {
                people.get(person).buyProduct(products.get(productName));
                System.out.println(person + " bought " + productName);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return;
            }
            input = scanner.nextLine();
        }
        for (Person value : people.values()) {
            System.out.println(value.toString());
        }
    }
}
