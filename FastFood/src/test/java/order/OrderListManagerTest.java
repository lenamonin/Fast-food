package order;

import menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import product.Product;
import java.util.ArrayList;

/**
 *
 * @author kerchoune
 */

/**
 * Tests unitaires pour la classe OrderListManager
 * Cette classe teste la gestion d'une liste de commandes et le pattern Observer
 * vérifiant que les vues sont correctement notifiées des changements dans la liste
 */
public class OrderListManagerTest {
    
    // Une classe Product concrète pour les tests
    private static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price, "test_image.jpg");
        }
        
        @Override
        public String getDescription() {
            return this.name;
        }
    }
    
    // Une implémentation de OrderListView pour les tests
    // Cette classe nous permet de suivre les notifications reçues par les vues
    private static class TestOrderListView implements OrderListView {
        private ArrayList<Order> lastUpdatedList;
        private int updateCount;
        
        public TestOrderListView() {
            this.updateCount = 0;
        }
        
        @Override
        public void update(ArrayList<Order> orderList) {
            this.lastUpdatedList = new ArrayList<>(orderList);
            this.updateCount++;
        }
        
        public ArrayList<Order> getLastUpdatedList() {
            return this.lastUpdatedList;
        }
        
        public int getUpdateCount() {
            return this.updateCount;
        }
    }
    
    private OrderListManager orderListManager;
    private TestOrderListView testView;
    private Order order1;
    private Order order2;
    
    @BeforeEach
    public void setUp() {
        // Création d'une nouvelle liste de commandes pour chaque test
        ArrayList<Order> orderList = new ArrayList<>();
        orderListManager = new OrderListManager(orderList);
        testView = new TestOrderListView();
        
        // Création de commandes de test avec quelques produits et menus
        order1 = new Order();
        order1.addProduct(new TestProduct("Product 1", 10.0));
        order1.addMenu(new Menu("Menu 1", 20.0));
        
        order2 = new Order();
        order2.addProduct(new TestProduct("Product 2", 15.0));
        order2.addMenu(new Menu("Menu 2", 25.0));
    }
    
    /**
     * Test de l'ajout d'une vue
     * Vérifie que la vue est correctement enregistrée et qu'elle reçoit
     * les notifications lors des modifications de la liste
     */
    @Test
    public void testAddView() {
        // Quand on ajoute une vue
        orderListManager.addView(testView);
        
        // Et que nous effectuons une modification de la liste
        orderListManager.addOrder(order1);
        
        // Alors la vue devrait être notifiée
        assertEquals(1, testView.getUpdateCount(),
                "La vue devrait être notifiée une fois après l'ajout d'une commande");
        assertNotNull(testView.getLastUpdatedList(),
                "La vue devrait recevoir une référence à la liste de commandes");
        assertEquals(1, testView.getLastUpdatedList().size(),
                "La liste mise à jour devrait contenir une commande");
    }
    
    /**
     * Test de l'ajout d'une commande
     * Vérifie que la commande est ajoutée et que les vues sont notifiées
     */
    @Test
    public void testAddOrder() {
        // Configuration initiale
        orderListManager.addView(testView);
        
        // Quand nous ajoutons une commande
        orderListManager.addOrder(order1);
        
        // Alors la vue devrait être notifiée
        assertEquals(1, testView.getUpdateCount(),
                "La vue devrait être notifiée une fois");
        assertTrue(testView.getLastUpdatedList().contains(order1),
                "La commande devrait être présente dans la liste");
        
        // Quand nous ajoutons une deuxième commande
        orderListManager.addOrder(order2);
        
        // Alors la vue devrait être notifiée à nouveau
        assertEquals(2, testView.getUpdateCount(),
                "La vue devrait être notifiée deux fois");
        assertEquals(2, testView.getLastUpdatedList().size(),
                "La liste devrait contenir deux commandes");
    }
    
    /**
     * Test de la suppression d'une commande
     * Vérifie que la commande est supprimée et que les vues sont notifiées
     */
    @Test
    public void testRemoveOrder() {
        // Configuration initiale
        orderListManager.addView(testView);
        orderListManager.addOrder(order1);
        orderListManager.addOrder(order2);
        int initialUpdateCount = testView.getUpdateCount();
        
        // Quand nous supprimons une commande
        orderListManager.removeOrder(order1);
        
        // Alors la vue devrait être notifiée
        assertEquals(initialUpdateCount + 1, testView.getUpdateCount(),
                "La vue devrait être notifiée après la suppression");
        assertFalse(testView.getLastUpdatedList().contains(order1),
                "La commande supprimée ne devrait plus être dans la liste");
        assertTrue(testView.getLastUpdatedList().contains(order2),
                "La commande non supprimée devrait toujours être dans la liste");
    }
    
    /**
     * Test de la suppression d'une commande non existante
     * Vérifie que la tentative de suppression d'une commande inexistante
     * ne provoque pas d'erreur et ne modifie pas la liste
     */
    @Test
    public void testRemoveNonExistentOrder() {
        // Configuration initiale
        orderListManager.addView(testView);
        orderListManager.addOrder(order1);
        int initialUpdateCount = testView.getUpdateCount();
        
        // Quand nous essayons de supprimer une commande qui n'existe pas
        Order nonExistentOrder = new Order();
        orderListManager.removeOrder(nonExistentOrder);
        
        // Alors la vue ne devrait pas être notifiée et la liste ne devrait pas changer
        assertEquals(initialUpdateCount, testView.getUpdateCount(),
                "La vue ne devrait pas être notifiée pour une suppression sans effet");
        assertTrue(testView.getLastUpdatedList().contains(order1),
                "La commande existante devrait toujours être dans la liste");
    }
    
    /**
     * Test de notifications multiples
     * Vérifie que plusieurs vues reçoivent correctement les notifications
     */
    @Test
    public void testMultipleViews() {
        // Configuration avec deux vues
        TestOrderListView secondView = new TestOrderListView();
        orderListManager.addView(testView);
        orderListManager.addView(secondView);
        
        // Quand nous effectuons une modification
        orderListManager.addOrder(order1);
        
        // Alors les deux vues devraient être notifiées
        assertEquals(1, testView.getUpdateCount(),
                "La première vue devrait être notifiée une fois");
        assertEquals(1, secondView.getUpdateCount(),
                "La seconde vue devrait être notifiée une fois");
        assertEquals(testView.getLastUpdatedList().size(), 
                    secondView.getLastUpdatedList().size(),
                "Les deux vues devraient avoir la même taille de liste");
    }

    @Test
    public void testGetOrderList() {
        // Ajouter des commandes dans l'OrderListManager
        orderListManager.addOrder(order1);
        orderListManager.addOrder(order2);
        ArrayList<Order> orderList = orderListManager.getOrderList();
        assertThat(orderList).hasSize(2); 
        assertThat(orderList).contains(order1, order2);
    }
}
