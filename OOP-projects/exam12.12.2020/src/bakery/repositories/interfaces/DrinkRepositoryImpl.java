package bakery.repositories.interfaces;

import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {
    private Collection<Drink> models;

    public DrinkRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        Drink drinkToReturn = null;
        for (Drink model : models) {
            if (model.getName().equals(drinkName) && model.getBrand().equals(drinkBrand)) {
                drinkToReturn = model;
            }
        }
        return drinkToReturn;
    }

    @Override
    public Collection<Drink> getAll() {
        return this.models;
    }

    @Override
    public void add(Drink drink) {
        models.add(drink);
    }
}
