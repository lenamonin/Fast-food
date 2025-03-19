package BorneUI;

/**
 *
 * @author conte
 */

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import order.Order;
import order.OrderListManager;
import order.OrderManager;
import product.Product;
import product.burger.Burger;

import java.util.ArrayList;

public class PaymentFrameTest {

    private FrameFixture window;
    private Order order;
    private MainBorneUI mainMock;

    @BeforeEach
    public void setUp() {
        order = new Order();
        Product product = new Burger("Cheeseburger", 5.00);
        order.addProduct(product);
        OrderManager orderManager = new OrderManager(order);
        OrderListManager orderListManager = new OrderListManager(new ArrayList<>());
        mainMock = GuiActionRunner.execute(() -> new MainBorneUI(orderManager, orderListManager, "TestUser"));

        PaymentFrame frame = GuiActionRunner.execute(() -> new PaymentFrame(mainMock, order));
        window = new FrameFixture(frame);
        window.show(); 
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp(); 
    }

    @Test
    public void testCashPaymentButton() {
        assertTrue(window.target().isVisible(), "PaymentFrame devrait être visible au début.");
        window.button(JButtonMatcher.withText("Especes")).click();

        JOptionPaneFixture dialog = window.optionPane();
        dialog.requireVisible();
        dialog.requireMessage("Paiement effectué avec succès via especes");
        dialog.okButton().click();
        
       // assertTrue(window.robot().finder().find(OrderSummaryFrame.class).isShowing(),
       //         "OrderSummaryFrame devrait être affiché après un paiement.");

        assertFalse(window.target().isShowing(), "PaymentFrame devrait être fermé après un paiement.");
    }

    @Test
    public void testCardPaymentButton() {
        assertTrue(window.target().isVisible(), "PaymentFrame devrait être visible au début.");
        window.button(JButtonMatcher.withText("Carte Bancaire")).click();

        JOptionPaneFixture dialog = window.optionPane();
        dialog.requireVisible();
        dialog.requireMessage("Paiement effectué avec succès via carte");
        dialog.okButton().click();

//        assertTrue(window.robot().finder().find(OrderSummaryFrame.class).isShowing(),
        //        "OrderSummaryFrame devrait être affiché après un paiement.");

        assertFalse(window.target().isShowing(), "PaymentFrame devrait être fermé après un paiement.");
    }
}

