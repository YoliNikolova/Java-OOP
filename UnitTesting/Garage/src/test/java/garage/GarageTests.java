package garage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private Car car1;
    private Car car2;
    private Car car3;
    private Garage garage;

    @Before
    public void setUp(){
         car1 = new Car("Audi",200,20000);
        car2 = new Car("Mercedes",280,30000);
        car3 = new Car("Honda",180,8000);
        garage = new Garage();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getCarsTest(){
        garage.getCars().remove(0);
    }

    @Test
    public void getCountTest(){
        garage.addCar(car1);
        assertEquals(1,garage.getCount());
    }


    @Test
    public void getCarsWithMaxSpeedTest(){
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> cars = garage.findAllCarsWithMaxSpeedAbove(200);
        assertEquals(1,cars.size());
        assertEquals(car2.getBrand(),cars.get(0).getBrand());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullCarTest(){
        garage.addCar(null);
    }

    @Test
    public void addCarTest(){
        garage.addCar(car1);
    }

    @Test
    public void getMostExpensiveCarTest(){
        garage.addCar(car3);
        garage.addCar(car2);
        garage.addCar(car1);
        Car carToReturn = garage.getTheMostExpensiveCar();
        assertEquals(car2.getBrand(),carToReturn.getBrand());
        assertEquals(car2.getPrice(),carToReturn.getPrice(),0);
    }

    @Test
    public void getMostExpensiveCarFromEmptyGarageTest() {
        Car carToReturn = garage.getTheMostExpensiveCar();
        assertEquals(null,carToReturn);
    }

    @Test
    public void findAllCarsByBrand(){
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);
        List<Car> cars = garage.findAllCarsByBrand("Audi");
        assertEquals(1,cars.size());
        assertEquals(car1.getBrand(),cars.get(0).getBrand());
    }

}