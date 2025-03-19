package BorneUI;

/**
 *
 * @author conte
 */

import java.awt.event.WindowAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.fixture.DialogFixture;
import org.assertj.swing.finder.WindowFinder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.Test;
import org.assertj.swing.fixture.FrameFixture;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateAccountFrameTest {
    private FrameFixture window;

    @BeforeEach
    void setUp() {
        CreateAccountFrame frame = new CreateAccountFrame();
        frame.setName("frame0");
        window = new FrameFixture(frame);
        window.show(); 
    }

    @AfterEach
    void tearDown() {
        window.cleanUp(); 
    }

    @Test
    void testInvalidName() {
        JTextComponentFixture nameField = window.textBox("NameInput");
        nameField.enterText(""); 
        window.button(JButtonMatcher.withText("Créer")).click();
        DialogFixture dialog = window.dialog();
        String dialogText = dialog.label("OptionPane.label").text(); 

        assertThat(dialogText).contains("Le nom rentré n'est pas valide");
}
   /* @Test
    void testValidAccountCreation() throws IOException {
        String validName = "John Doe";
        JTextComponentFixture nameField = window.textBox("NameInput");
        nameField.enterText(validName);
        window.button(JButtonMatcher.withText("Créer")).click();
        DialogFixture dialog = window.dialog();
        String dialogText = dialog.label("OptionPane.label").text(); 
        assertTrue(dialogText.contains("Compte créé avec succès !"));

        File file = new File("data/comptes.txt");
        assertTrue(file.exists(), "The account file should exist");

        String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
        assertTrue(content.contains(validName), "Account name should be present in the file");
        assertTrue(content.matches(".*" + validName + ",\\d{5}.*"), "Account line should match the format with a valid number");
    }*/

    @Test
    void testCancelButtonWithListener() throws InterruptedException {
        AtomicBoolean isClosed = new AtomicBoolean(false);
        window.target().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
            isClosed.set(true);
        }
    });
    window.button(JButtonMatcher.withText("Annuler")).click();
    Thread.sleep(500);

    assertTrue(isClosed.get(), "La fenêtre n'a pas été fermée.");
}

}

