package order;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 *
 * @author kerchoune
 */

/**
 * Tests pour l'interface OrderListView
 * On vérifie qu'elle peut être implémentée et utilisée correctement
 */
public class OrderListViewTest {
    
    /**
     * Classe de test implémentant OrderListView
     */
    private class TestOrderListView implements OrderListView {
        private ArrayList<Order> lastUpdatedList;
        
        @Override
        public void update(ArrayList<Order> orderList) {
            this.lastUpdatedList = orderList;
        }
        
        public ArrayList<Order> getLastUpdatedList() {
            return this.lastUpdatedList;
        }
    }
    
    /**
     * Vérifie qu'on peut implémenter OrderListView et utiliser sa méthode update
     */
    @Test
    public void testOrderListViewUpdate() {
        // Given
        TestOrderListView view = new TestOrderListView();
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order());
        orders.add(new Order());
        
        // When
        view.update(orders);
        
        // Then
        assertNotNull(view.getLastUpdatedList(), 
                "La liste mise à jour ne devrait pas être null");
        assertSame(orders, view.getLastUpdatedList(), 
                "La liste stockée devrait être celle passée à update");
        assertEquals(2, view.getLastUpdatedList().size(), 
                "La liste devrait contenir le bon nombre d'ordres");
    }
    
    /**
     * Vérifie qu'on peut passer null à update
     */
    @Test
    public void testOrderListViewUpdateWithNull() {
        // Given
        TestOrderListView view = new TestOrderListView();
        
        // When
        view.update(null);
        
        // Then
        assertNull(view.getLastUpdatedList(), 
                "La liste stockée devrait être null après update(null)");
    }
    
    /**
     * Vérifie qu'on peut utiliser une liste vide
     */
    @Test
    public void testOrderListViewUpdateWithEmptyList() {
        // Given
        TestOrderListView view = new TestOrderListView();
        ArrayList<Order> emptyList = new ArrayList<>();
        
        // When
        view.update(emptyList);
        
        // Then
        assertNotNull(view.getLastUpdatedList(), 
                "La liste mise à jour ne devrait pas être null");
        assertTrue(view.getLastUpdatedList().isEmpty(), 
                "La liste stockée devrait être vide");
    }
}