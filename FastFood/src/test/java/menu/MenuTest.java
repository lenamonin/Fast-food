package menu;
/**
 *
 * @author conte
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.Product;
import product.burger.Burger;
import product.dessert.Dessert;
import product.drink.Drink;
import java.util.ArrayList;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu("Menu du Jour", 15.0);
    }

    @Test
    public void testDT1_AddValidProducts() {
        Product drink = new Drink("Coca", 2.5);
        Product burger = new Burger("Burger classique", 7.5);
        Product dessert = new Dessert("Tarte aux pommes", 5.0);

        menu.addProduct(drink);
        menu.addProduct(burger);
        menu.addProduct(dessert);

        assertEquals(3, menu.getProducts().size());
    }

    @Test
    public void testGetName() {
        assertEquals("Menu du Jour", menu.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(15.0, menu.getPrice(), 0.05);
    }


    @Test
    public void testGetDescription() {
        Product drink = new Drink("Coca", 2.5);
        Product burger = new Burger("Burger classique", 7.5);
        Product dessert = new Dessert("Tarte aux pommes", 5.0);

        menu.addProduct(drink);
        menu.addProduct(burger);
        menu.addProduct(dessert);

        String expectedOutput =
            "Menu du Jour (15.0€)\n" +
            "    -Coca\n" +
            "    -Burger classique()\n" +
            "    -Tarte aux pommes\n";

        assertEquals(expectedOutput, menu.getDescription());
    }

    @Test
    public void testGetProducts() {
        Product drink = new Drink("Coca", 2.5);
        Product burger = new Burger("Burger classique", 7.5);
        Product dessert = new Dessert("Tarte aux pommes", 5.0);

        menu.addProduct(drink);
        menu.addProduct(burger);
        menu.addProduct(dessert);

        ArrayList<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(drink);
        expectedProducts.add(burger);
        expectedProducts.add(dessert);

        assertEquals(expectedProducts, menu.getProducts());
    }
}


