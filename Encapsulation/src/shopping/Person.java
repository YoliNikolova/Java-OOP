package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        if (!Validator.isValidName(name)) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (!Validator.isNonNegativeNum(money)){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money=money;
    }

    public String getName() {
        return name;
    }

    public void buyProduct(Product product) {
        if (product.getCost() < this.money) {
            this.products.add(product);
            this.money -= product.getCost();
        } else {
            throw new IllegalArgumentException(this.name + " can't afford " + product.getName());
        }
    }

    @Override
    public String toString(){
        String productsOutput = this.products.isEmpty()? "Nothing bought"
                : this.products.stream().map(p-> p.getName()).collect(Collectors.joining(", "));
        return name + " - " + productsOutput;
    }
}
