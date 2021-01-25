package easterRaces.core;


import easterRaces.common.ExceptionMessages;
import easterRaces.common.OutputMessages;
import easterRaces.core.interfaces.Controller;
import easterRaces.entities.cars.Car;
import easterRaces.entities.cars.MuscleCar;
import easterRaces.entities.cars.SportsCar;
import easterRaces.entities.drivers.Driver;
import easterRaces.entities.drivers.DriverImpl;
import easterRaces.entities.racers.Race;
import easterRaces.entities.racers.RaceImpl;
import easterRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Car> motorcycleRepository;
    private Repository<Driver> riderRepository;
    private Repository<Race> raceRepository;

    public ControllerImpl(Repository<Driver> riderRepository,Repository<Car> motorcycleRepository,Repository<Race> raceRepository){
        this.riderRepository =riderRepository;
        this.raceRepository =raceRepository;
        this.motorcycleRepository =motorcycleRepository;
    }

    @Override
    public String createDriver(String driver) {
        Driver driverPerson = new DriverImpl(driver);
       this.riderRepository.add(driverPerson);
       return String.format(OutputMessages.DRIVER_CREATED,driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {
        Car car=null;

        if(type.equals("Muscle")){
           car = new MuscleCar(model, horsePower);
        }else if(type.equals("Sports")){
          car = new SportsCar(model,horsePower);
        }

        this.motorcycleRepository.add(car);
        return String.format(OutputMessages.CAR_CREATED,car.getClass().getSimpleName(),car.getModel());
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {
        this.riderRepository.getByName(driverName).addCar(this.motorcycleRepository.getByName(carModel));
        return String.format(OutputMessages.CAR_ADDED,driverName,carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {
        this.raceRepository.getByName(raceName).addDriver(this.riderRepository.getByName(driverName));
        return String.format(OutputMessages.DRIVER_ADDED,driverName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        if(this.raceRepository.getByName(raceName).getDrivers().size()<3){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,raceName,3));
        }

        Race currRace = this.raceRepository.getByName(raceName);
        List<Driver> list = currRace.getDrivers().stream().sorted((l, r)->Double.compare(r.getCar().calculateRacePoints(currRace.getLaps()),l.getCar().calculateRacePoints(currRace.getLaps()))).collect(Collectors.toList());
        this.raceRepository.remove(this.raceRepository.getByName(raceName));

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.DRIVER_FIRST_POSITION,list.get(0).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_SECOND_POSITION,list.get(1).getName(),raceName)).append(System.lineSeparator());
        sb.append(String.format(OutputMessages.DRIVER_THIRD_POSITION,list.get(2).getName(),raceName)).append(System.lineSeparator());
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);
        return String.format(OutputMessages.RACE_CREATED,name);
    }
}
