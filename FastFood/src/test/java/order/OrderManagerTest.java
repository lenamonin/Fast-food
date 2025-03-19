package order;

import menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import product.Product;

/**
 *
 * @author kerchoune
 */

/**
 * Tests unitaires pour la classe OrderManager
 * Cette classe teste le pattern Observer et la gestion des commandes
 * en s'assurant que les vues sont correctement notifiées lors des modifications
 */
public class OrderManagerTest {
    
    // Nous créons d'abord une classe Product concrète pour nos tests
    private static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price, "test_image.jpg");
        }
        
        @Override
        public String getDescription() {
            return this.name;
        }
    }
    
    // Une implémentation simple de OrderView pour les tests
    private static class TestOrderView implements OrderView {
        private Order lastUpdatedOrder;
        private int updateCount;
        
        public TestOrderView() {
            this.updateCount = 0;
        }
        
        @Override
        public void update(Order order) {
            this.lastUpdatedOrder = order;
            this.updateCount++;
        }
        
        public Order getLastUpdatedOrder() {
            return this.lastUpdatedOrder;
        }
        
        public int getUpdateCount() {
            return this.updateCount;
        }
    }
    
    private OrderManager orderManager;
    private TestOrderView testView;
    private TestProduct product;
    private Menu menu;
    
    @BeforeEach
    public void setUp() {
        // Nous créons une nouvelle commande pour chaque test
        Order order = new Order();
        orderManager = new OrderManager(order);
        testView = new TestOrderView();
        product = new TestProduct("Test Product", 10.0);
        menu = new Menu("Test Menu", 20.0);
    }
    
    /**
     * Test de l'ajout d'une vue
     * Vérifie que la vue est correctement enregistrée et notifiée
     */
    @Test
    public void testAddView() {
        // Quand nous ajoutons une vue
        orderManager.addView(testView);
        
        // Et que nous effectuons une modification
        orderManager.addProduct(product);
        
        // Alors la vue devrait être notifiée
        assertEquals(1, testView.getUpdateCount(),
                "La vue devrait être notifiée une fois après l'ajout d'un produit");
        assertNotNull(testView.getLastUpdatedOrder(),
                "La vue devrait recevoir une référence à la commande");
    }
    
    /**
     * Test de l'ajout d'un produit
     * Vérifie que le produit est ajouté et que les vues sont notifiées
     */
    @Test
    public void testAddProduct() {
        // Configuration initiale
        orderManager.addView(testView);
        
        // Quand nous ajoutons un produit
        orderManager.addProduct(product);
        
        // Alors la vue devrait être notifiée
        assertEquals(1, testView.getUpdateCount(),
                "La vue devrait être notifiée une fois");
        assertTrue(testView.getLastUpdatedOrder().getProducts().contains(product),
                "Le produit devrait être présent dans la commande");
    }
    
    /**
     * Test de l'ajout d'un menu
     * Vérifie que le menu est ajouté et que les vues sont notifiées
     */
    @Test
    public void testAddMenu() {
        // Configuration initiale
        orderManager.addView(testView);
        
        // Quand nous ajoutons un menu
        orderManager.addMenu(menu);
        
        // Alors la vue devrait être notifiée
        assertEquals(1, testView.getUpdateCount(),
                "La vue devrait être notifiée une fois");
        assertTrue(testView.getLastUpdatedOrder().getMenus().contains(menu),
                "Le menu devrait être présent dans la commande");
    }
    
    /**
     * Test de la suppression d'un produit
     * Vérifie que le produit est supprimé et que les vues sont notifiées
     */
    @Test
    public void testRemoveProduct() {
        // Configuration initiale
        orderManager.addView(testView);
        orderManager.addProduct(product);
        int initialUpdateCount = testView.getUpdateCount();
        
        // Quand nous supprimons le produit
        orderManager.removeProduct(product);
        
        // Alors la vue devrait être notifiée à nouveau
        assertEquals(initialUpdateCount + 1, testView.getUpdateCount(),
                "La vue devrait être notifiée après la suppression");
        assertFalse(testView.getLastUpdatedOrder().getProducts().contains(product),
                "Le produit ne devrait plus être présent dans la commande");
    }
    
    /**
     * Test de la suppression d'un menu
     * Vérifie que le menu est supprimé et que les vues sont notifiées
     */
    @Test
    public void testRemoveMenu() {
        // Configuration initiale
        orderManager.addView(testView);
        orderManager.addMenu(menu);
        int initialUpdateCount = testView.getUpdateCount();
        
        // Quand nous supprimons le menu
        orderManager.removeMenu(menu);
        
        // Alors la vue devrait être notifiée à nouveau
        assertEquals(initialUpdateCount + 1, testView.getUpdateCount(),
                "La vue devrait être notifiée après la suppression");
        assertFalse(testView.getLastUpdatedOrder().getMenus().contains(menu),
                "Le menu ne devrait plus être présent dans la commande");
    }
    
    /**
     * Test du vidage de la commande
     * Vérifie que la commande est vidée et que les vues sont notifiées
     */
    @Test
    public void testClearOrder() {
        // Configuration initiale
        orderManager.addView(testView);
        orderManager.addProduct(product);
        orderManager.addMenu(menu);
        int initialUpdateCount = testView.getUpdateCount();
        
        // Quand nous vidons la commande
        orderManager.clearOrder();
        
        // Alors la vue devrait être notifiée
        assertEquals(initialUpdateCount + 1, testView.getUpdateCount(),
                "La vue devrait être notifiée après le vidage");
        assertTrue(testView.getLastUpdatedOrder().getProducts().isEmpty(),
                "La liste des produits devrait être vide");
        assertTrue(testView.getLastUpdatedOrder().getMenus().isEmpty(),
                "La liste des menus devrait être vide");
    }
    
    /**
     * Test de la récupération de la commande
     * Vérifie que nous pouvons accéder à la commande gérée
     */
    @Test
    public void testGetOrder() {
        Order retrievedOrder = orderManager.getOrder();
        assertNotNull(retrievedOrder,
                "La commande récupérée ne devrait pas être null");
    }
}