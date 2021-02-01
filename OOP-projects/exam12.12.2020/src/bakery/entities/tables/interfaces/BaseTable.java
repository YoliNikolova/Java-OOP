package bakery.entities.tables.interfaces;

import bakery.common.ExceptionMessages;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseTable implements Table {
    protected Collection<BakedFood> foodOrders;
    protected Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseTable(int tableNumber, int capacity, double pricePerPerson) {
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
        this.setTableNumber(tableNumber);
        this.setPricePerPerson(pricePerPerson);
        this.setCapacity(capacity);
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }

            setReserved(true);
            this.setNumberOfPeople(numberOfPeople);

    }

    @Override
    public void orderFood(BakedFood food) {
        this.foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        this.drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double sum = 0;
        for (BakedFood foodOrder : foodOrders) {
            sum = sum + foodOrder.getPrice();
        }
        for (Drink drinkOrder : drinkOrders) {
            sum = sum + drinkOrder.getPrice();
        }
        setPrice(pricePerPerson * numberOfPeople);
        sum = sum + getPrice();
        return sum;
    }

    @Override
    public void clear() {
        this.foodOrders.clear();
        this.drinkOrders.clear();
        setReserved(false);
        setNumberOfPeople(0);
        setPrice(0);
    }

    @Override
    public String getFreeTableInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: ").append(getTableNumber()).append(System.lineSeparator());
        sb.append("Type: ").append(getClass().getSimpleName()).append(System.lineSeparator());
        sb.append("Capacity: ").append(getCapacity()).append(System.lineSeparator());
        sb.append("Price per Person: ").append(getPricePerPerson()).append(System.lineSeparator());
        return sb.toString();
    }
}
