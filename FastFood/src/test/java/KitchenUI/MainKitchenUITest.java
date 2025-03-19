/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KitchenUI;

import org.assertj.swing.fixture.FrameFixture;
import java.util.ArrayList;
import order.Order;
import order.OrderListManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import product.Product;

/**
 *
 * @author lenam
 */
public class MainKitchenUITest {
    
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
    
    private MainKitchenUI mainKitchenUI;
    private OrderListManager orderListManager;
    private TestProduct product1;
    private Order order1;
    private FrameFixture window;
    
    
    @BeforeEach
    public void setUp() {
        product1 = new TestProduct("Test Product 1", 10.0);
        // Création de commandes pour les tests
        order1 = new Order();
        order1.addProduct(product1);
        // Créer un OrderListManager vide
        orderListManager = new OrderListManager(new ArrayList<>());
        mainKitchenUI = new MainKitchenUI(orderListManager);
        //l'interface graphique
        window = new FrameFixture(mainKitchenUI);
        window.show();  // Affiche la fenêtre
    }
    
    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }
    
    @Test
    public void testConstructor() {
        window.requireTitle("Cuisine");
        window.panel("jPanel1").requireVisible(); 
        //assertTrue(mainKitchenUI.getSize().equals(new java.awt.Dimension(800, 600)));
    }

    @Test
    public void testShowListOrderFrame() {
        // Appelle la méthode et vérifie l'affichage
        mainKitchenUI.showListOrderFrame();
        window.panel("jPanel1").requireVisible();
        
    }
    
    @Test
    public void testShowOrderDetailFrame() {
        // Affiche les détails d'une commande et vérifie
        mainKitchenUI.showOrderDetailFrame(order1);
        window.panel("jPanel1").requireVisible(); 
    }
}
