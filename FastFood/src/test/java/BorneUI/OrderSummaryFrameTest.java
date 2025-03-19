package BorneUI;

/**
 *
 * @author conte
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import javax.swing.JTable;

import menu.Menu;
import order.Order;
import order.OrderListManager;
import order.OrderManager;
import org.assertj.swing.core.matcher.JButtonMatcher;
import product.Product;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.burger.Burger;
import product.drink.Drink;

public class OrderSummaryFrameTest {

    private FrameFixture window;
    private Order order;
    private MainBorneUI mainMock; 

    @BeforeEach
    public void setUp() {
        order = new Order();
        Product product1 = new Burger("Burger", 5.00);
        Product product2 = new Burger("Burger2", 3.00);
        order.addProduct(product1);
        order.addProduct(product2);

        Menu menu = new Menu("Menu Maxi", 10.00);
        menu.addProduct(new Drink("Coca", 2.50));
        menu.addProduct(new Burger("Burger3", 4.00));
        order.addMenu(menu);

        OrderManager orderManager = new OrderManager(order); 
        OrderListManager orderListManager = new OrderListManager(new ArrayList<>());
        mainMock = GuiActionRunner.execute(() -> new MainBorneUI(orderManager,orderListManager,"TestUser"));
        OrderSummaryFrame frame = GuiActionRunner.execute(() -> new OrderSummaryFrame(mainMock, order));
        window = new FrameFixture(frame);
        window.show(); 
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp(); 
    }

    @Test
    public void testOrderSummaryFrameDisplaysCorrectly() {
        window.label().requireText("Merci pour votre commande");
        JTable table = window.table().target();
        assertNotNull(table, "Le tableau d'affichage n'a pas été initialisé correctement.");
        assertEquals("Burger()", table.getValueAt(0, 0), "Le premier produit est incorrect.");
        assertEquals("5,00", table.getValueAt(0, 1), "Le prix du premier produit est incorrect.");
        assertEquals("Burger2()", table.getValueAt(1, 0), "Le deuxième produit est incorrect.");
        assertEquals("3,00", table.getValueAt(1, 1), "Le prix du deuxième produit est incorrect.");
        assertEquals("Menu Maxi", table.getValueAt(2, 0), "Le menu est incorrect.");
        assertEquals("10,00", table.getValueAt(2, 1), "Le prix du menu est incorrect.");
        assertEquals("  - Coca", table.getValueAt(3, 0), "Le détail du menu (Coca) est incorrect.");
        assertEquals("2,50", table.getValueAt(3, 1), "Le prix du Coca est incorrect.");
        assertEquals("  - Burger3()", table.getValueAt(4, 0), "Le détail du menu (Nuggets) est incorrect.");
        assertEquals("4,00", table.getValueAt(4, 1), "Le prix des Nuggets est incorrect.");

        assertEquals("Total", table.getValueAt(5, 0), "La ligne total est incorrecte.");
        assertEquals("18,00", table.getValueAt(5, 1), "Le prix total est incorrect.");
    }

    @Test
    public void testCloseButtonClosesFrame() {
        window.button(JButtonMatcher.withText("Fermer")).click();
        assertEquals(false, window.target().isVisible(), "La fenêtre n'a pas été fermée correctement.");
    }
}
