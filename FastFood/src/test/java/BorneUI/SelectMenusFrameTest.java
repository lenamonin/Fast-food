package BorneUI;

/**
 *
 * @author conte
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import order.OrderManager;
import menu.*;


import javax.swing.*;
import order.Order;

public class SelectMenusFrameTest {

    private FrameFixture window;
    private OrderManager manager;

    @BeforeEach
    public void setUp() {
        manager = new OrderManager(new Order());
        SelectMenusFrame selectMenusFrame = GuiActionRunner.execute(() -> new SelectMenusFrame(manager));
        JFrame frame = GuiActionRunner.execute(() -> {
            JFrame testFrame = new JFrame();
            testFrame.add(selectMenusFrame);
            testFrame.pack();
            return testFrame;
        });

        window = new FrameFixture(frame);
        window.show(); 
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp(); 
    }

    @Test
    public void testAddMenuClassique() {
        window.button(JButtonMatcher.withText("Menu Classique 10€")).click();
        assertEquals(1, manager.getOrder().getMenus().size(), "Un menu devrait être ajouté.");
        assertEquals(Menu.class, manager.getOrder().getMenus().get(0).getClass(),
            "Le menu ajouté devrait être un MenuClassique.");
    }

    @Test
    public void testAddMenuVegetarien() {
        window.button(JButtonMatcher.withText("Menu Végétarien 11€")).click();
        assertEquals(1, manager.getOrder().getMenus().size(), "Un menu devrait être ajouté.");
        assertEquals(Menu.class, manager.getOrder().getMenus().get(0).getClass(),
            "Le menu ajouté devrait être un MenuVegetarien.");
    }

    @Test
    public void testAddMenuDouble() {
        window.button(JButtonMatcher.withText("Menu Double 15€")).click();
        assertEquals(1, manager.getOrder().getMenus().size(), "Un menu devrait être ajouté.");
        assertEquals(Menu.class, manager.getOrder().getMenus().get(0).getClass(),
            "Le menu ajouté devrait être un MenuDouble.");
    }
}


