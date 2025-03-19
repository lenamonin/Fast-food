package menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 *
 * @author conte
 */
public class MenuDoubleFactoryTest  extends MenuFactoryTest{
    @Override
    protected MenuFactory getMenuFactory() {
        return new MenuDoubleFactory(); 
    }
    @Test
    public void testMenuDoubleSpecific() {
        Menu menu = getMenuFactory().getMenu();
        assertEquals("Menu double", menu.getName());
        assertEquals(15.0, menu.getPrice(), 0.01);
        assertEquals(4, menu.getProducts().size());

        assertTrue(menu.getProducts().get(0) instanceof product.burger.Burger);
        assertTrue(menu.getProducts().get(1) instanceof product.burger.Burger);
        assertTrue(menu.getProducts().get(2) instanceof product.drink.Drink);
        assertTrue(menu.getProducts().get(3) instanceof product.dessert.Dessert);
    }
}
