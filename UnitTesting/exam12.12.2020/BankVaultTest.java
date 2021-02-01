package bankSafe;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class BankVaultTest {
    private Item item;
    private Item item2;
    private BankVault vault;

    @Before
    public void setUp() {
        item = new Item("Ivan", "987");
        item2 = new Item("Todor", "123");
        vault = new BankVault();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getTest() {
        vault.getVaultCells().remove(null);
    }

    @Test
    public void constructorTest() {
        assertEquals(12, vault.getVaultCells().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTest1() throws OperationNotSupportedException {
        vault.addItem("D1", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTest2() throws OperationNotSupportedException {
        vault.addItem("A1", item);
        vault.addItem("A1", item);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addTest3() throws OperationNotSupportedException {
        vault.addItem("A1", item);
        vault.addItem("B1", item);
    }

    @Test
    public void addTest() throws OperationNotSupportedException {
        String s = "Item:987 saved successfully!";
        String output = vault.addItem("A1", item);
        assertEquals(item, vault.getVaultCells().get("A1"));
        assertEquals(s, output);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTest1() throws OperationNotSupportedException {
        vault.removeItem("D1", item);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeTest2() throws OperationNotSupportedException {
        vault.addItem("A1", item2);
        vault.removeItem("A1", item);
    }

    @Test
    public void removeTest() throws OperationNotSupportedException {
        String s = "Remove item:987 successfully!";
        vault.addItem("B1", item);
        String output = vault.removeItem("B1", item);
        assertNull(vault.getVaultCells().get("B1"));
        assertEquals(s, output);
    }

}