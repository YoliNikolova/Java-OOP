package store;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StoreTests {
    private Product product1;
    private Product product2;
    private Product product3;
    private Store store;

    @Before
    public void setUp() {
        product1 = new Product("cake", 2, 20.50);
        product2 = new Product("coffee", 0, 1.80);
        product3 = new Product("chocolate", 5, 5.80);
        store = new Store();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getProductsTest(){
        store.getProducts().remove(0);
    }

    @Test
    public void getCountTest(){
        store.addProduct(product1);
        store.addProduct(product3);
        assertEquals(2,store.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullProduct(){
        store.addProduct(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void addProductWithNegativeQuantity(){
        store.addProduct(product2);
    }

    @Test
    public void addProductTest(){
        store.addProduct(product1);
        assertEquals(1,store.getCount());
        assertEquals(product1.getName(),store.getProducts().get(0).getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyNullProductTest(){
        store.addProduct(product1);
        store.buyProduct("water",1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void buyProductWithInsufficientlyQuantityTest(){
        store.addProduct(product1);
        store.buyProduct("cake",3);
    }

    @Test
    public void buyProductTest(){
        store.addProduct(product1);
        double finalPrice = store.buyProduct("cake",1);
        assertEquals(20.50,finalPrice,0);
        assertEquals(1,product1.getQuantity());
    }

    @Test
    public void getMostExpensiveProductTest1(){
        store.addProduct(product1);
        store.addProduct(product3);
        assertEquals(product1.getName(),store.getTheMostExpensiveProduct().getName());
    }

    @Test
    public void getMostExpensiveProductTest2(){
   assertNull(store.getTheMostExpensiveProduct());
    }
}