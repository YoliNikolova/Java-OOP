package easterRaces.repositories;

import easterRaces.common.ExceptionMessages;
import easterRaces.entities.racers.Race;
import easterRaces.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class RaceRepository implements Repository<Race> {
    Collection<Race> models;

    public RaceRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Race getByName(String name) {
        Race raceToReturn = null;

        for (Race model : models) {
            if (model.getName().equals(name)) {
                raceToReturn = model;
            }
        }

        if(raceToReturn==null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,name));
        }
        return raceToReturn;
    }

    @Override
    public Collection<Race> getAll() {
        return this.models;
    }

    @Override
    public void add(Race model) {
        for (Race race : models) {
            if(race.getName().equals(model.getName())){
                throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,model.getName()));
            }
        }
        this.models.add(model);
    }

    @Override
    public boolean remove(Race model) {
        Race raceToRemove = null;
        boolean flag = false;
        for (Race race : models) {
            if(race.getName().equals(model.getName())){
                raceToRemove=race;
                flag=true;
            }
        }
        if(!flag){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,model.getName()));
        }
        this.models.remove(raceToRemove);
        return flag;
    }
}
