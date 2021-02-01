package bakery.entities.drinks.interfaces;

import bakery.common.ExceptionMessages;

public abstract class BaseDrink implements Drink {
    private String name;
    private int portion;
    private double price;
    private String brand;

    protected BaseDrink(String name,int portion,double price,String brand){
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
        this.setBrand(brand);
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if(brand == null || brand.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.brand = brand;
    }

    @Override
    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        if(portion<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PORTION);
        }
        this.portion = portion;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %dml - %.2flv",this.name,this.brand,this.portion,this.price);
    }
}
