package computers;



import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ComputerManagerTests {
    // TODO: Test ComputerManager
    private Computer computer1;
    private Computer computer2;
    private Computer computer3;
    private ComputerManager computerManager;

    @Before
    public void setUp() {
        computer1 = new Computer("DELL", "XPS", 700);
        computer2 = new Computer("Asus", "ZenBook", 1000);
        computer3 = new Computer("Apple", "MacBook", 2000);
        computerManager = new ComputerManager();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputers() {
        computerManager.getComputers().remove(0);
    }

    @Test
    public void getCountEmptyListTest() {
        assertEquals(0, computerManager.getComputers().size());
    }

    @Test
    public void getCountTest() {
        computerManager.addComputer(computer1);
        assertEquals(1, computerManager.getComputers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullComputerTest() {
        computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingComputerTest() {
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer3);
        Computer computer4 = new Computer("Apple", "MacBook", 2000);
        computerManager.addComputer(computer4);
    }

    @Test
    public void addTest() {
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer3);
        Computer computer4 = new Computer("Apple", "MacBookPro", 5000);
        computerManager.addComputer(computer4);
        assertEquals(3, computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getComputerWithNullValuesTest(){
        computerManager.getComputer(null,"model");
        computerManager.getComputer("test",null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNotExistingComputerTest(){
        computerManager.addComputer(computer3);
        computerManager.getComputer("Asus","VivoBook");
    }

    @Test
    public void getComputerTest(){
        computerManager.addComputer(computer3);
        computerManager.addComputer(computer1);
        Computer returned = computerManager.getComputer("Apple","MacBook");
        assertEquals(computer3.getManufacturer(),returned.getManufacturer());
        assertEquals(computer3.getModel(),returned.getModel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getComputerByManufactureNullTest(){
        computerManager.getComputersByManufacturer(null);
    }

    @Test
    public void getCompByManufactureTest(){
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer3);
        List<Computer> list = computerManager.getComputersByManufacturer(computer3.getManufacturer());
        assertEquals(list.get(0).getManufacturer(),computer3.getManufacturer());
    }

    @Test
    public void removeTest() {
        computerManager.addComputer(computer1);
        computerManager.addComputer(computer3);
        Computer removed = computerManager.removeComputer("Apple","MacBook");
        assertEquals(computer3.getManufacturer(),removed.getManufacturer());
        assertEquals(computer3.getModel(),removed.getModel());
        assertEquals(1,computerManager.getCount());
    }
}