package BorneUI;


import javax.swing.JFrame;
import menu.Menu;
import order.Order;
import order.OrderManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MenuItemComponentTest {

    private FrameFixture window;
    private Menu menu;
    private OrderManager order;

    @BeforeEach
    void setUp() {
        menu = new Menu("Menu", 10.0);
        order = new OrderManager(new Order());
        order.addMenu(menu);
        JFrame testFrame = GuiActionRunner.execute(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new MenuItemComponent(menu, order));
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            return frame;
        });
        window = new FrameFixture(testFrame);
        window.show();
    }

    @AfterEach
    void tearDown() {
        window.cleanUp(); 
    }

    @Test
    public void testConstructor() {
        window.textBox().requireText(menu.getDescription());
        window.button("Supprimer").requireVisible();
    }

    @Test
    public void testSupprimerMenu() {
        int initialOrderSize = order.getOrder().getMenus().size();
        window.button("Supprimer").click();
        int newOrderSize = order.getOrder().getMenus().size();
        assertEquals(initialOrderSize-1, newOrderSize, "Le menu n'a pas été supprimé correctement.");
    }
}
