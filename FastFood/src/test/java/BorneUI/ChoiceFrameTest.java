package BorneUI;

/**
 *
 * @author conte
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;

import javax.swing.*;

class ChoiceFrameTest {
    private FrameFixture window;

    @BeforeEach
    void setUp() {
        JFrame testFrame = GuiActionRunner.execute(() -> {
            JFrame frame = new JFrame();
            frame.setContentPane(new ChoiceFrame(null, "TestUser"));
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
    void testWelcomeMessage() {
        String welcomeMessage = window.label("jLabel2").text();
        assertEquals("Bienvenue TestUser", welcomeMessage, "The welcome message is incorrect.");
    }

    @Test
    void testMenuButtonExists() {
        window.button("MenuButton").requireVisible();
    }

    @Test
    void testProductButtonExists() {
        window.button("ProductButton").requireVisible();
    }
    
}

