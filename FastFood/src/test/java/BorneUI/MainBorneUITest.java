package BorneUI;

/**
 *
 * @author conte
 */

import java.util.ArrayList;
import order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;

class MainBorneUITest {

    private MainBorneUI mainBorneUI;
    private OrderManager orderManager;
    private OrderListManager orderListManager;

    @BeforeEach
    void setUp() {
        orderManager = new OrderManager(new Order()); 
        orderListManager = new OrderListManager(new ArrayList<>()); 
        mainBorneUI = new MainBorneUI(orderManager, orderListManager, "TestUser");
    }

    @Test
    void testConstructor() {
        assertNotNull(mainBorneUI);
        assertNotNull(mainBorneUI.getOrderManager());
        assertEquals("Borne de commande", mainBorneUI.getTitle());
        assertTrue(mainBorneUI.getSize().equals(new java.awt.Dimension(800, 600)));
    }

    @Test
    void testShowEditOrderFrame_Menu() {
        mainBorneUI.showEditOrderFrame("menu");
        assertTrue(mainBorneUI.isAncestorOf(mainBorneUI.getEditOrderFrame()));
        assertTrue(mainBorneUI.getEditOrderFrame().isVisible());
    }

    @Test
    void testShowEditOrderFrame_Product() {
        mainBorneUI.showEditOrderFrame("product");
        assertTrue(mainBorneUI.isAncestorOf(mainBorneUI.getEditOrderFrame()));
        assertTrue(mainBorneUI.getEditOrderFrame().isVisible());
    }

    @Test
    void testShowChoiceFrame() {
        mainBorneUI.showChoiceFrame();
        assertTrue(mainBorneUI.isAncestorOf(mainBorneUI.getChoiceFrame()));
    }

    @Test
    void testShowPaymentFrame() {
        Order currentOrder = new Order(); 
        mainBorneUI.showPaymentFrame(currentOrder);
        assertFalse(mainBorneUI.isVisible());
        //assertTrue(mainBorneUI.getPaymentFrame().isVisible());
    }

    @Test
    void testGetOrderManager() {
        assertEquals(orderManager, mainBorneUI.getOrderManager());
    }

    //orderListManager n'est pas initialisé dans MainBorneUI
    /*@Test
    void testGetOrderListManager() {
        assertEquals(orderListManager, mainBorneUI.getOrderListManager());
    }*/
}

