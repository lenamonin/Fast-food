/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KitchenUI;

import java.util.ArrayList;
import javax.swing.JFrame;
import order.Order;
import order.OrderListManager;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JPanelFixture;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.Product;

/**
 *
 * @author lenam
 */
public class OrderListFrameTest {
    
    // Classe concrète temporaire pour les tests
    class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price, "test_image.jpg");
        }

        @Override
        public String getDescription() {
            return this.name;
        }
    }

    private Order order1, order2;
    private TestProduct product1, product2;
    private OrderListManager orderListManager;
    private MainKitchenUI mainKitchenUI;
    private OrderListFrame orderListFrame;
    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        product1 = new TestProduct("Test Product 1", 10.0);
        product2 = new TestProduct("Test Product 2", 20.0);
        order1 = new Order();
        order2 = new Order();
        order1.addProduct(product1);
        order2.addProduct(product2);

        orderListManager = new OrderListManager(new ArrayList<>());
        orderListManager.addOrder(order1);
        orderListManager.addOrder(order2);

        mainKitchenUI = new MainKitchenUI(orderListManager);
        orderListFrame = new OrderListFrame(orderListManager, mainKitchenUI);

        // l'interface graphique
        JFrame frame = new JFrame("Test OrderListFrame");
        frame.add(orderListFrame);
        frame.pack();
        frame.setVisible(true);
        window = new FrameFixture(frame);
        window.show();
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testConstructor() {
        assertTrue(orderListFrame.isVisible());
        window.label("jLabel1").requireText("Commandes");
    }
    
    @Test
    public void testAddOrder() {
        // Ajout d'une commande et appele de la méthode update pour mettre à jour la liste des commandes
        Order newOrder = new Order();
        newOrder.addProduct(product2);
        orderListManager.addOrder(newOrder);
        orderListFrame.update(orderListManager.getOrderList());
        JPanelFixture panel = window.panel("orderListPanel");
        panel.requireVisible();
        assertEquals(orderListManager.getOrderList().size(), panel.target().getComponentCount());
    }
    
    @Test
    public void testRemoveOrder() {
        // On supprime une commande et on appele la méthode update pour mettre à jour la liste des commandes
        orderListManager.removeOrder(order1);
        orderListFrame.update(orderListManager.getOrderList());
        JPanelFixture panel = window.panel("orderListPanel");
        panel.requireVisible();
        assertEquals(orderListManager.getOrderList().size(), panel.target().getComponentCount());
    }
    
    @Test
    public void testUpdate() {
        // on appele la méthode update pour mettre à jour la liste des commandes
        orderListFrame.update(orderListManager.getOrderList());
        JPanelFixture panel = window.panel("orderListPanel");
        panel.requireVisible();
        assertEquals(orderListManager.getOrderList().size(), panel.target().getComponentCount());
    }
    
    @Test
    public void testUpdateListOrderEmpty() {
        // on vide la liste des commandes
        orderListManager.removeOrder(order1);
        orderListManager.removeOrder(order2);
        // on met à jour l'interface
        orderListFrame.update(orderListManager.getOrderList());
        JPanelFixture panel = window.panel("orderListPanel");
        panel.requireVisible();
        // on vérifie qu'il n'y a aucune commande affichée
        assertEquals(0, panel.target().getComponentCount());
    }
}
