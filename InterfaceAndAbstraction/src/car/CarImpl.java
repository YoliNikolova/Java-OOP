package car;

public abstract class CarImpl implements Car{
    private String model;
    private String color;
    private String countryProduced;
    private Integer horsePower;

    protected CarImpl(String model,String color,Integer horsePower,String countryProduced){
        this.model=model;
        this.color=color;
        this.horsePower=horsePower;
        this.countryProduced=countryProduced;
    }
    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String countryProduced() {
        return this.countryProduced;
    }

    @Override
    public String toString() {
        return "This is " + model + " produced in " + countryProduced + " and have " + Car.TIRES +" tires";
    }
}
