package easterRaces.repositories;

import easterRaces.common.ExceptionMessages;
import easterRaces.entities.drivers.Driver;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class DriverRepository implements Repository<Driver> {

    Collection<Driver> models;

    public DriverRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Driver getByName(String name) {
        Driver driver = null;
        for (Driver model : models) {
            if (model.getName().equals(name)) {
                driver = model;
            }
        }

        if(driver==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,name));
        }

        return driver;
    }

    @Override
    public Collection<Driver> getAll() {
        return this.models;
    }

    @Override
    public void add(Driver model) {
        for (Driver driver : models) {
            if(driver.getName().equals(model.getName())){
                throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS,model.getName()));
            }
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Driver model) {
        Driver d = null;
        boolean flag = false;
        for (Driver driver : models) {
            if(driver.getName().equals(model.getName())){
                d=driver;
                flag=true;
            }
        }
        this.models.remove(d);
        return flag;
    }
}
