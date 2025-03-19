package menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author conte
 */
public abstract class MenuFactoryTest {
    protected abstract MenuFactory getMenuFactory();

    @Test
    public void testGetMenu() {
        MenuFactory menuFactory = getMenuFactory();
        Menu menu = menuFactory.getMenu();
        assertNotNull("Le nom du menu ne doit pas être nul", menu.getName());
        assertTrue(menu.getPrice() > 0);
        assertTrue( menu.getProducts().size() > 0);
    }
}
