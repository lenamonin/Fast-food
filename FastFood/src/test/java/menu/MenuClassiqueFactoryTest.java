package menu;
/**
 *
 * @author conte
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MenuClassiqueFactoryTest extends MenuFactoryTest {

    @Override
    protected MenuFactory getMenuFactory() {
        return new MenuClassiqueFactory();
    }

    @Test
    public void testMenuClassiqueSpecific() {
        Menu menu = getMenuFactory().getMenu();
        assertEquals("Menu classique", menu.getName(), "Le nom du menu ne correspond pas");
        assertEquals(10.0, menu.getPrice(), 0.01, "Le prix du menu ne correspond pas");
        assertEquals(3, menu.getProducts().size(), "Le nombre de produits dans le menu est incorrect");

        assertTrue(menu.getProducts().get(0) instanceof product.burger.Burger, "Le premier produit n'est pas un hamburger");
        assertTrue(menu.getProducts().get(1) instanceof product.drink.Drink, "Le deuxième produit n'est pas une boisson");
        assertTrue(menu.getProducts().get(2) instanceof product.dessert.Dessert, "Le troisième produit n'est pas un dessert");
    }
}

