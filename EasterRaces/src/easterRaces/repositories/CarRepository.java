package easterRaces.repositories;

import easterRaces.common.ExceptionMessages;
import easterRaces.entities.cars.Car;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class CarRepository implements Repository<Car> {
    private Collection<Car> models;

    public CarRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Car getByName(String name) {
        Car carToReturn = null;

        for (Car model : models) {
            if (model.getModel().equals(name)) {
                carToReturn = model;
            }
        }

        if (carToReturn == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND, name));
        }

        return carToReturn;
    }

    @Override
    public Collection<Car> getAll() {
        return this.models;
    }

    @Override
    public void add(Car model) {

        for (Car car : models) {
            if (car.getModel().equals(model.getModel())) {
                throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model.getModel()));
            }
        }

        this.models.add(model);
    }

    @Override
    public boolean remove(Car model) {
        boolean flag = false;
        Car carToRemove = null;

        for (Car car : models) {
            if (car.getModel().equals(model.getModel())) {
                carToRemove = car;
                flag = true;
                break;
            }
        }

        this.models.remove(carToRemove);
        return flag;
    }
}
