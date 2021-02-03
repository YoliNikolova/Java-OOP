package pizza;

import java.util.Map;

public class Dough {
    private static final Map<String,Double> TYPE_DOUGH = Map.of("White",1.5,"Wholegrain",1.0);
    private static final Map<String,Double> TYPE_ADDITION = Map.of("Crispy",0.9,"Chewy",1.1,"Homemade",1.0);
    private String flourType;
    private String backingTechnique;
    private double weight;

    public Dough(String flourType,String backingTechnique,double weight){
        this.setFlourType(flourType);
        this.setBakingTechnique(backingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        if(!TYPE_DOUGH.containsKey(flourType)){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType=flourType;
    }

    private void setBakingTechnique(String backingTechnique) {
        if(!TYPE_ADDITION.containsKey(backingTechnique)){
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.backingTechnique=backingTechnique;
    }

    private void setWeight(double weight) {
        if(weight<1 || weight>200){
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight=weight;
    }

    public double calculateCalories(){
        return (2*weight)*TYPE_ADDITION.get(this.backingTechnique)*TYPE_DOUGH.get(this.flourType);
    }
}
