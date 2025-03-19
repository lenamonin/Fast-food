package menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 *
 * @author conte
 */
public class MenuVegetarienTest extends MenuFactoryTest{
    @Override
    protected MenuFactory getMenuFactory() {
        return new MenuVegetarienFactory();
    }
    @Test
    public void testMenuClassiqueSpecific() {
        Menu menu = getMenuFactory().getMenu();
        assertEquals("Menu végétarien", menu.getName());
        assertEquals(11.0, menu.getPrice(), 0.01);
        assertEquals(3, menu.getProducts().size());
        assertTrue(menu.getProducts().get(0) instanceof product.burger.Burger);
        assertTrue(menu.getProducts().get(1) instanceof product.drink.Drink);
        assertTrue(menu.getProducts().get(2) instanceof product.dessert.Dessert);
    }
}
