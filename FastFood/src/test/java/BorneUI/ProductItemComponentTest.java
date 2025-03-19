package BorneUI;

/**
 *
 * @author conte
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import order.OrderManager;
import product.Product;
import product.burger.Burger;

import javax.swing.*;
import order.Order;

public class ProductItemComponentTest {

    private FrameFixture window;
    private OrderManager order; 
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Burger("Cheeseburger", 5.00);
        order=new OrderManager(new Order());
        ProductItemComponent component = GuiActionRunner.execute(() -> new ProductItemComponent(product, order));
        JFrame frame = GuiActionRunner.execute(() -> {
            JFrame testFrame = new JFrame();
            testFrame.add(component);
            testFrame.pack();
            return testFrame;
        });

        window = new FrameFixture(frame);
        window.show(); 
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp(); 
    }

    @Test
    public void testProductDisplay() {
        String expectedText = "Cheeseburger() 5,00€";
        window.label().requireText(expectedText);
    }

    @Test
    public void testRemoveProductButton() {
        window.button(JButtonMatcher.withText("Supprimer")).click();
        assertEquals(0, order.getOrder().getProducts().size(), "Le produit devrait être supprimé après le clic.");
    }
}
