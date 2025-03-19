package BorneUI;

/**
 *
 * @author conte
 */

import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JFrame;
import menu.Menu;
import order.Order;
import order.OrderManager;
import order.OrderListManager;
import static org.assertj.core.api.Assertions.assertThat;
import product.Product;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import product.burger.Burger;
import product.drink.Drink;

public class OrderResumeFrameTest {

    private FrameFixture window;
    private OrderManager orderManager;
    private OrderListManager orderListManager;
    private OrderResumeFrame orderResumeFrame;
    private Product product;
    private Menu menu;

    @BeforeEach
    void setUp() {
        orderListManager = new OrderListManager(new ArrayList<>());
        orderManager = new OrderManager(new Order());
        product = new Burger("Burger 1", 5.0);
        menu = new Menu("Menu 1", 10.0);
        orderManager.addProduct(product);
        orderManager.addMenu(menu);

        JFrame testFrame = GuiActionRunner.execute(() -> {
            JFrame frame = new JFrame();
            orderResumeFrame = new OrderResumeFrame(orderManager, new MainBorneUI(orderManager, orderListManager, "Test User"), orderListManager);
            frame.setContentPane(orderResumeFrame);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            return frame;
        });
        
        window = new FrameFixture(testFrame);
        window.show(); 
    }

    @AfterEach
    void tearDown() {
        window.cleanUp(); 
    }

    @Test
    public void testInitialOrderView() {
        orderResumeFrame.update(orderManager.getOrder());
        window.label("TotalPriceLabel").requireText("Total : 15.0€");
        window.panel("ElementList").requireVisible();
       // assertTrue(orderResumeFrame.ElementList.getComponentCount() > 0, "La liste des éléments ne contient pas d'éléments.");
    }

    @Test
    public void testClearOrderButton() {
        int initialItemCount = orderManager.getOrder().getProducts().size() + orderManager.getOrder().getMenus().size();
        window.button("ClearOrderButton").click();
        int newItemCount = orderManager.getOrder().getProducts().size() + orderManager.getOrder().getMenus().size();
        assertEquals(0, newItemCount, "La commande n'a pas été vidée.");
    }

    @Test
    public void testValidateOrderButton() {
        orderResumeFrame.update(orderManager.getOrder());
        window.label("TotalPriceLabel").requireText("Total : 15.0€");
        window.button("ValidateOrder").click();

        DialogFixture dialog = window.dialog();
        String dialogText = dialog.label("OptionPane.label").text(); 
        assertThat(dialogText).contains("Cela fait un total de 15,00€, passer commande ?");
      
    }

    //tous les boutons dans la boite de dialogue ont le même nom donc on ne peut pas les tester
   /* @Test
    public void testOrderValidationAction() {
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Le produit n'est pas dans la commande.");
        assertEquals(1, orderManager.getOrder().getMenus().size(), "Le menu n'est pas dans la commande.");
        window.button("ValidateOrder").click();
        window.dialog().button().requireText("Yes").click(); // Simuler le clic sur "YES" dans la fenêtre de confirmation
        assertEquals(1, orderListManager.getOrders().size(), "La commande n'a pas été ajoutée à la liste des commandes.");
    }*/

    @Test
    public void testElementListUpdate() throws Exception {
        Product newProduct = new Drink("New Product", 7.0);
        orderManager.addProduct(newProduct);
        Field elementListField = OrderResumeFrame.class.getDeclaredField("ElementList");
        elementListField.setAccessible(true);
        javax.swing.JPanel elementList = (javax.swing.JPanel) elementListField.get(orderResumeFrame);
        assertTrue(elementList.getComponentCount() > 2, "La liste des éléments n'a pas été mise à jour.");
    }
}
