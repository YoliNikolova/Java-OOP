package pizza;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String[] pizzaInfo = scanner.nextLine().split("\\s+");
            String pizzaName = pizzaInfo[1];
            int numberOfToppings = Integer.parseInt(pizzaInfo[2]);
            Pizza pizza = new Pizza(pizzaName, numberOfToppings);


            String[] doughInfo = scanner.nextLine().split("\\s+");
            String flourType = doughInfo[1];
            String bakingTechnique = doughInfo[2];
            double weightDough = Double.parseDouble(doughInfo[3]);
            Dough dough = new Dough(flourType, bakingTechnique, weightDough);
            pizza.setDough(dough);

            String command = scanner.nextLine();
            while (!command.equals("END")) {
                String[] toppingsInfo = command.split("\\s+");
                String toppingType = toppingsInfo[1];
                double weightTopping = Double.parseDouble(toppingsInfo[2]);

                Topping topping = new Topping(toppingType, weightTopping);
                pizza.addTopping(topping);

                command = scanner.nextLine();
            }
            System.out.println(pizza.toString());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
