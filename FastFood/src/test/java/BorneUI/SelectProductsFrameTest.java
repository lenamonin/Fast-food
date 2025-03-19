package BorneUI;

/**
 *
 * @author conte
 */


import javax.swing.JFrame;
import order.Order;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import order.OrderManager;

public class SelectProductsFrameTest {

    private FrameFixture window;
    private OrderManager orderManager;

    @BeforeEach
    public void setUp() {
        orderManager = new OrderManager(new Order());
        SelectProductsFrame selectProductsFrame = GuiActionRunner.execute(() -> new SelectProductsFrame(orderManager));
        JFrame frame = GuiActionRunner.execute(() -> {
            JFrame testFrame = new JFrame();
            testFrame.add(selectProductsFrame);
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
    public void testAddCheeseBurger() {
        window.button(JButtonMatcher.withText("CheeseBurger 4.50€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Cheeseburger", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un CheeseBurger.");
    }

    @Test
    public void testAddHamburger() {
        window.button(JButtonMatcher.withText("Hamburger 4.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Hamburger", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Hamburger.");
    }

    @Test
    public void testAddVeggieBurger() {
        window.button(JButtonMatcher.withText("Veggie 3.50€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Veggie", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un VeggieBurger.");
    }

    @Test
    public void testAddBaconBurger() {
        window.button(JButtonMatcher.withText("BaconBurger 5.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("BaconBurger", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un BaconBurger.");
    }

    @Test
    public void testAddChickenBurger() {
        window.button(JButtonMatcher.withText("ChickenBurger 5.50€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("ChickenBurger", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un ChickenBurger.");
    }

    @Test
    public void testAddDoubleCheese() {
        window.button(JButtonMatcher.withText("DoubleCheese 6.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("DoubleCheese", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un DoubleCheese.");
    }

    @Test
    public void testAddCoca() {
        window.button(JButtonMatcher.withText("Coca 2.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Coca", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Coca.");
    }

    @Test
    public void testAddEau() {
        window.button(JButtonMatcher.withText("Eau 1.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Eau", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être de l'Eau.");
    }

    @Test
    public void testAddJus() {
        window.button(JButtonMatcher.withText("Jus 2.50€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Jus", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Jus.");
    }
    @Test
    public void testAddThe() {
        window.button(JButtonMatcher.withText("Thé 2.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Thé", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Thé.");
    }
    @Test
    public void testAddLimonade() {
        window.button(JButtonMatcher.withText("Limonade 2.50€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Limonade", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Limonade.");
    }
    @Test
    public void testAddGlace() {
        window.button(JButtonMatcher.withText("Glace 3.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Glace", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être une Glace.");
    }

    @Test
    public void testAddTarte() {
        window.button(JButtonMatcher.withText("Tarte 2.50€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Tarte", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être une Tarte.");
    }
    @Test
    public void testAddBrownie() {
        window.button(JButtonMatcher.withText("Brownie 2.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Brownie", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Brownie.");
    }
    @Test
    public void testAddFondant() {
        window.button(JButtonMatcher.withText("Fondant 2.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Fondant", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Fondant.");
    }
    @Test
    public void testAddMacaron() {
        window.button(JButtonMatcher.withText("Macaron 3.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Macaron", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Macaron.");
    }
    @Test
    public void testAddDonut() {
        window.button(JButtonMatcher.withText("Donut 3.00€")).click();
        assertEquals(1, orderManager.getOrder().getProducts().size(), "Un produit devrait être ajouté.");
        assertEquals("Donut", orderManager.getOrder().getProducts().get(0).getName(),
            "Le produit ajouté devrait être un Donut.");
    }
}

