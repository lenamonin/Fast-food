package BorneUI;

/**
 *
 * @author conte
 */
import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import order.*;
import BorneUI.MainBorneUI;
import javax.swing.JFrame;
import java.util.ArrayList;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.junit.jupiter.api.AfterEach;


public class EditOrderFrameTest {

    private FrameFixture window;
    private OrderManager orderManager;
    private MainBorneUI main;
    private OrderListManager orderListManager;

    @BeforeEach
    void setUp() {
        orderManager = new OrderManager(new Order());  
        main = new MainBorneUI(orderManager, new OrderListManager(new ArrayList<>()), "parametre1");  
        orderListManager = new OrderListManager(new ArrayList<Order>());
        EditOrderFrame editOrderFrame = new EditOrderFrame(orderManager, main, orderListManager);
        JFrame testFrame = GuiActionRunner.execute(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(editOrderFrame);
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
}    
