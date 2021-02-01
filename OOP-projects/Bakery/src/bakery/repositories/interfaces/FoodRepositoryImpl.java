package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.tables.interfaces.BaseTable;

import java.util.ArrayList;
import java.util.Collection;

public class FoodRepositoryImpl implements FoodRepository<BakedFood> {

    private Collection<BakedFood> models;

    public FoodRepositoryImpl(){
        this.models=new ArrayList<>();

    }

    @Override
    public BakedFood getByName(String name) {
        BakedFood foodToReturn= null;
        for (BakedFood model : models) {
            if(model.getName().equals(name)){
                foodToReturn=model;
            }
        }
        return foodToReturn;
    }

    @Override
    public Collection<BakedFood> getAll() {
        return null;
    }

    @Override
    public void add(BakedFood bakedFood) {
models.add(bakedFood);
    }
}
