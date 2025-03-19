package BorneUI;
/**
 *
 * @author conte
 */

import java.awt.Frame;
import java.awt.Point;
import java.awt.event.MouseEvent;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import order.OrderListManager;
import order.OrderManager;
import user.Compte;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextField;
import order.Order;

public class AcceuilFrameTest {

    private acceuilFrame acceuilFrame;
    private OrderManager orderManager;
    private OrderListManager orderListManager;
    private File testFile;
    private File invalidTestFile;
    @BeforeEach
    void setUp() throws IOException {
        orderManager = new OrderManager(new Order());
        orderListManager = new OrderListManager(new ArrayList<>());
        acceuilFrame = new acceuilFrame(orderManager, orderListManager);

        testFile = File.createTempFile("test_comptes", ".txt");
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Alice,123456\n");
            writer.write("Bob,654321\n");
        }
        invalidTestFile = new File("invalid_comptes_test.txt");
        invalidTestFile.deleteOnExit(); 
        String invalidContent = "InvalidData\n12345OnlyOneField";
        java.nio.file.Files.writeString(invalidTestFile.toPath(), invalidContent);
    }

    @Test
    void testVerifierCompte() throws Exception {
        Method chargerComptesMethod = acceuilFrame.getClass().getDeclaredMethod("chargerComptesDepuisFichier", String.class);
        chargerComptesMethod.setAccessible(true);
        chargerComptesMethod.invoke(acceuilFrame, testFile.getAbsolutePath());

        Method verifierCompteMethod = acceuilFrame.getClass().getDeclaredMethod("verifierCompte", String.class);
        verifierCompteMethod.setAccessible(true); 
        String result1 = (String) verifierCompteMethod.invoke(acceuilFrame, "123456");
        assertEquals("Alice", result1, "Le compte avec le code 123456 devrait retourner 'Alice'.");
        String result2 = (String) verifierCompteMethod.invoke(acceuilFrame, "000000");
        assertNull(result2, "Un code invalide devrait retourner null.");
    }
    @Test
    void testChargerComptesDepuisFichier() throws Exception {
        Method chargerComptesMethod = acceuilFrame.getClass().getDeclaredMethod("chargerComptesDepuisFichier", String.class);
        chargerComptesMethod.setAccessible(true);
        chargerComptesMethod.invoke(acceuilFrame, testFile.getAbsolutePath());

        var field = acceuilFrame.getClass().getDeclaredField("comptes");
        field.setAccessible(true);
        List<Compte> comptes = (List<Compte>) field.get(acceuilFrame);

        assertNotNull(comptes, "La liste des comptes ne devrait pas être nulle.");
        assertEquals(2, comptes.size(), "Le fichier contient deux comptes.");
        assertEquals("Alice", comptes.get(0).getNomUtilisateur(), "Le premier compte devrait être Alice.");
    }

      @Test
    void testVerifierCompteWithInvalidFile() throws Exception {
        Method chargerComptesMethod = acceuilFrame.getClass().getDeclaredMethod("chargerComptesDepuisFichier", String.class);
        chargerComptesMethod.setAccessible(true);
        chargerComptesMethod.invoke(acceuilFrame, invalidTestFile.getAbsolutePath());

        Field comptesField = acceuilFrame.getClass().getDeclaredField("comptes");
        comptesField.setAccessible(true); // Accès au champ privé
        @SuppressWarnings("unchecked")
        List<Compte> comptes = (List<Compte>) comptesField.get(acceuilFrame);

        assertNotNull(comptes, "La liste des comptes ne doit pas être null.");
        assertTrue(comptes.isEmpty(), "La liste des comptes doit être vide pour un fichier invalide.");
    }

    @Test
    void testVerifierCompteWithEmptyCode() throws Exception {
        Method chargerComptesMethod = acceuilFrame.getClass().getDeclaredMethod("chargerComptesDepuisFichier", String.class);
        chargerComptesMethod.setAccessible(true);
        chargerComptesMethod.invoke(acceuilFrame, testFile.getAbsolutePath());

        Method verifierCompteMethod = acceuilFrame.getClass().getDeclaredMethod("verifierCompte", String.class);
        verifierCompteMethod.setAccessible(true); 

        String result = (String) verifierCompteMethod.invoke(acceuilFrame, ""); 
        assertNull(result, "Un code vide devrait retourner null.");
    }
@Test
void testJButton1ActionPerformedWithValidCode() throws Exception {
    Method chargerComptesMethod = acceuilFrame.getClass().getDeclaredMethod("chargerComptesDepuisFichier", String.class);
    chargerComptesMethod.setAccessible(true);
    chargerComptesMethod.invoke(acceuilFrame, "valid_comptes.txt"); 

    Field textFieldField = acceuilFrame.getClass().getDeclaredField("jTextField1");
    textFieldField.setAccessible(true);
    JTextField textField = (JTextField) textFieldField.get(acceuilFrame);
    textField.setText("12345"); 
    Field buttonField = acceuilFrame.getClass().getDeclaredField("jButton1");
    buttonField.setAccessible(true);
    JButton submitButton = (JButton) buttonField.get(acceuilFrame);
    submitButton.doClick();

    boolean found = false;
    for (Frame frame : Frame.getFrames()) {
        if (frame instanceof MainBorneUI && frame.isVisible()) {
            found = true;
            break;
        }
    }
    assertTrue(found);
}

@Test
void testJButton1ActionPerformedWithInvalidCode() throws Exception {
    Method chargerComptesMethod = acceuilFrame.getClass().getDeclaredMethod("chargerComptesDepuisFichier", String.class);
    chargerComptesMethod.setAccessible(true);
    chargerComptesMethod.invoke(acceuilFrame, "valid_comptes.txt");

    Field textFieldField = acceuilFrame.getClass().getDeclaredField("jTextField1");
    textFieldField.setAccessible(true);
    JTextField textField = (JTextField) textFieldField.get(acceuilFrame);
    textField.setText("invalidCode");
    Field buttonField = acceuilFrame.getClass().getDeclaredField("jButton1");
    buttonField.setAccessible(true);
    JButton submitButton = (JButton) buttonField.get(acceuilFrame);
    submitButton.doClick();
}
@Test
void testJButton2ActionPerformed() throws Exception {
    Field buttonField = acceuilFrame.getClass().getDeclaredField("jButton2");
    buttonField.setAccessible(true);
    JButton skipButton = (JButton) buttonField.get(acceuilFrame);
    skipButton.doClick();

    boolean found = false;
    for (Frame frame : Frame.getFrames()) {
        if (frame instanceof MainBorneUI && frame.isVisible()) {
            MainBorneUI ui = (MainBorneUI) frame;
            if ("nouveau client !".equals(ui.getUserName())) {
                found = true;
                break;
            }
        }
    }
    assertTrue(found);
}
@Test
void testNewAccountButtonMouseClicked() throws Exception {
    acceuilFrame.setVisible(true);
    Field buttonField = acceuilFrame.getClass().getDeclaredField("NewAccountButton");
    buttonField.setAccessible(true);
    JButton newAccountButton = (JButton) buttonField.get(acceuilFrame);
    Point location = newAccountButton.getLocationOnScreen();
    int x = location.x + newAccountButton.getWidth() / 2; 
    int y = location.y + newAccountButton.getHeight() / 2;
    MouseEvent mouseClickedEvent = new MouseEvent(
            newAccountButton, 
            MouseEvent.MOUSE_CLICKED, 
            System.currentTimeMillis(), 
            0, 
            x, y, 
            1, 
            false, 
            MouseEvent.BUTTON1 
    );
    newAccountButton.dispatchEvent(mouseClickedEvent);

    boolean found = false;
    for (Frame frame : Frame.getFrames()) {
        if (frame instanceof CreateAccountFrame && frame.isVisible()) {
            found = true;
            break;
        }
    }
    assertTrue(found, "La fenêtre CreateAccountFrame n'a pas été ouverte ou rendue visible après le clic.");
}

@Test
void testJTextField1ActionPerformed() throws Exception {
    Field textFieldField = acceuilFrame.getClass().getDeclaredField("jTextField1");
    textFieldField.setAccessible(true);
    JTextField textField = (JTextField) textFieldField.get(acceuilFrame);
    textField.postActionEvent();
    assertDoesNotThrow(() -> textField.postActionEvent());
}

}
