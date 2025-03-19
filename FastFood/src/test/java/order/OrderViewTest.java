package order;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kerchoune
 */

/**
 * Tests pour l'interface OrderView
 * On vérifie qu'elle peut être implémentée et utilisée correctement
 */
public class OrderViewTest {
    
    /**
     * Classe de test implémentant OrderView
     */
    private class TestOrderView implements OrderView {
        private Order lastUpdatedOrder;
        
        @Override
        public void update(Order order) {
            this.lastUpdatedOrder = order;
        }
        
        public Order getLastUpdatedOrder() {
            return this.lastUpdatedOrder;
        }
    }
    
    /**
     * Vérifie qu'on peut implémenter OrderView et utiliser sa méthode update
     */
    @Test
    public void testOrderViewUpdate() {
        // Given
        TestOrderView view = new TestOrderView();
        Order order = new Order();
        
        // When
        view.update(order);
        
        // Then
        assertNotNull(view.getLastUpdatedOrder(), 
                "L'ordre mis à jour ne devrait pas être null");
        assertSame(order, view.getLastUpdatedOrder(), 
                "L'ordre stocké devrait être celui passé à update");
    }
    
    /**
     * Vérifie qu'on peut passer null à update
     */
    @Test
    public void testOrderViewUpdateWithNull() {
        // Given
        TestOrderView view = new TestOrderView();
        
        // When
        view.update(null);
        
        // Then
        assertNull(view.getLastUpdatedOrder(), 
                "L'ordre stocké devrait être null après update(null)");
    }
}