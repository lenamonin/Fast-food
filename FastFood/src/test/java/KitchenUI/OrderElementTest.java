/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KitchenUI;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import order.Order;
import order.OrderListManager;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.Product;

/**
 *
 * @author lenam
 */
public class OrderElementTest {
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

    private TestProduct product1, product2;
    private Order order1;
    private FrameFixture window;
    private OrderElement orderElement;
    private MainKitchenUI mainKitchenUI;

    @BeforeEach
    public void setUp() {
        product1 = new TestProduct("Test Product 1", 10.0);
        product2 = new TestProduct("Test Product 2", 20.0);
        order1 = new Order();
        order1.addProduct(product1);
        order1.addProduct(product2);

        mainKitchenUI = new MainKitchenUI(new OrderListManager(new ArrayList<>()));
        orderElement = new OrderElement(order1, mainKitchenUI);
        
        // l'interface graphique
        JFrame frame = new JFrame("Test OrderElement");
        frame.add(orderElement);
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
        window.label("OrderNameLabel").requireText(order1.getName());
        assertTrue(orderElement.isVisible(), "Le nom de la commande doit être visible.");
    }
    
    @Test
    public void testDetailButton() {
        // Vérifie que le clic sur le bouton "Détails" ouvre la vue de détails de la commande
        JPanel mainPanel = (JPanel) mainKitchenUI.getContentPane().getComponent(0);
        assertNotNull(mainPanel, "Le panneau principal doit exister.");
        window.button("DetailButton").requireVisible();
        window.button("DetailButton").click();
        // on vérifie que la méthode `showOrderDetailFrame` de `MainKitchenUI` est appelée
        // on suppose qu'un nouveau panneau est affiché pour le détail de la commande
        Component currentComponent = mainPanel.getComponent(0);
        // on vérifie que le composant actuel est de type `OrderDetailPanel`
        assertTrue(currentComponent instanceof OrderDetailPanel, "Le panneau actif doit être OrderDetailPanel.");
    }
    
    @Test
    public void testVerifieAffichageOrderElement() {
        window.label("OrderNameLabel").requireVisible();
        window.button("DetailButton").requireVisible();
    }
}
