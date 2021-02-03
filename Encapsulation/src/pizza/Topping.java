package pizza;

import java.util.Map;


public class Topping {
    private static final Map<String,Double> ALLOWED_TOPPING_TYPES = Map.of("Meat",1.2,"Cheese",1.1,"Veggies",0.8,"Sauce",0.9);
    private String toppingType;
    private double weight;

    public Topping(String toppingType,double weight){
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String setToppingType){
        if(!ALLOWED_TOPPING_TYPES.containsKey(setToppingType)){
            throw new IllegalArgumentException("Cannot place " + setToppingType +" on top of your pizza.");
        }
        this.toppingType=setToppingType;
    }

    private void setWeight(double setWeight){
        if(setWeight<0 || setWeight>50){
            throw new IllegalArgumentException(setWeight + " weight should be in the range [1..50].");
        }
        this.weight=setWeight;
    }

    public double calculateCalories(){
        return this.weight*2*ALLOWED_TOPPING_TYPES.get(this.toppingType);
    }
}
