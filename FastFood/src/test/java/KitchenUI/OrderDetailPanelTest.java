/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KitchenUI;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import menu.Menu;
import order.Order;
import order.OrderListManager;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.Product;

/**
 *
 * @author lenam
 */
public class OrderDetailPanelTest {
    
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
    private Menu menu1;
    private Order order1;
    private FrameFixture window;
    private OrderDetailPanel orderDetailPanel;
    private MainKitchenUI mainKitchenUI;
    private OrderListManager orderListManager;
    //private OrderListFrame orderListFrame;
    
    @BeforeEach
    public void setUp() {
        product1 = new TestProduct("Test Product 1", 10.0);
        product2 = new TestProduct("Test Product 2", 20.0);
        menu1 = new Menu("Test Menu 1", 20.0);
        // Création de commandes pour les tests
        order1 = new Order();
        order1.addProduct(product1);
        order1.addProduct(product2);
        order1.addMenu(menu1);
        
        orderListManager = new OrderListManager(new ArrayList<>());
        orderListManager.addOrder(order1);
        
        mainKitchenUI = new MainKitchenUI(orderListManager);
        
        orderDetailPanel = new OrderDetailPanel(order1, mainKitchenUI, orderListManager);
        //orderListFrame = new OrderListFrame(orderListManager, mainKitchenUI);
        
        // l'interface graphique
        JFrame frame = new JFrame("Test OrderDetailPanel");
        frame.add(orderDetailPanel);
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
        assertTrue(orderDetailPanel.isVisible());
    }
    
    @Test
    public void testSetProducts() {
        // Vérifie que les produits et menus sont correctement affichés
        JPanel productsPanel = (JPanel) window.panel("ProductsPanel").target();
        JPanel pricesPanel = (JPanel) window.panel("PricesPanel").target();

        // Vérifie que les descriptions des produits sont ajoutées
        assertEquals(3, productsPanel.getComponentCount()); // 2 produits + 1 menu
        assertEquals(3, pricesPanel.getComponentCount());

        // Vérifie que les descriptions et prix sont affichés
        JLabel productLabel = (JLabel) ((JPanel) productsPanel.getComponent(0)).getComponent(0);
        assertEquals(product1.getDescription(), productLabel.getText());
        JLabel priceLabel = (JLabel) ((JPanel) pricesPanel.getComponent(0)).getComponent(0);
        assertEquals("10,00€", priceLabel.getText());
    }
    
    @Test
    public void testBackButton() {
        // Vérifie que le clic sur "Retour" retourne à la liste des commandes
        JPanel mainPanel = (JPanel) mainKitchenUI.getContentPane().getComponent(0);
        assertNotNull(mainPanel, "Le panneau principal doit exister.");
        window.button("BackButton").requireVisible();
        window.button("BackButton").click();
        Component currentComponent = mainPanel.getComponent(0);
        assertTrue(currentComponent instanceof OrderListFrame, "Le panneau actif doit être OrderListFrame.");
    }
    
    @Test
    public void testCancelButton() {
        // Vérifie que le clic sur "Annuler" annule la commande
        window.button("CancelButton").requireVisible();
        window.button("CancelButton").click();
        DialogFixture dialog = window.dialog();
        dialog.requireVisible();
        dialog.label("OptionPane.label").requireText("Annuler la commande ?");
        dialog.button(JButtonMatcher.withText("Yes")).click();
        
        //verifier que la commande a ete retire de la liste des commandes
        //ajouter la méthode getState
        /*int nbOrder = orderListManager.getOrderList().size();
        orderListManager.removeOrder(order1);  
        orderListFrame.update(orderListManager.getOrderList());
        assertEquals(nbOrder - 1, orderListManager.getOrderList().size(), "La commande devrait être retirée de la liste.");
        //assertEquals("Annulée", order1.getState());*/
    }
    
    @Test
    public void testValidateButton() {
        // Vérifie que le clic sur "Valider" termine la commande
        window.button("ValidateButton").requireVisible();
        window.button("ValidateButton").click();
        DialogFixture dialog = window.dialog();
        dialog.requireVisible();
        dialog.label("OptionPane.label").requireText("Valider la commande ?");
        dialog.button(JButtonMatcher.withText("Yes")).click();
        /*assertEquals("Terminée", order1.getState());
        assertEquals(orderListManager.getOrderList().size() - 1, orderListManager.getOrderList().size(), "La commande devrait être retirée de la liste.");*/
    }
}
