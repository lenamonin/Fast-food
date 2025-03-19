package product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import product.burger.Burger;


/**
 *
 * @author kerchoune
 */

/**
 * Classe de test pour Product. Product est une classe abstraite donc
 * nous utilisons la classe Burger pour tester ses fonctionnalités
 */
public class ProductTest {
    
    // Instance pour tous nos tests
    private Burger testProduct;
    
    @BeforeEach
    public void setUp() {
        // Avant chaque test création d'un nouveau Burger
        testProduct = new Burger("TestBurger", 5.0);
    }


    // Test de la méthode setName avec des valeurs valides
    @Test
    public void testSetName() {
        String expectedName = "NewBurger";
        testProduct.setName(expectedName);
        assertEquals(expectedName, testProduct.getName(), 
            "Le nom du produit devrait être mis à jour");
    }


    // Test de la méthode setPrice avec différentes valeurs
    @Test
    public void testSetPrice() {
        // Test d'un prix valide
        double validPrice = 10.0;
        testProduct.setPrice(validPrice);
        assertEquals(validPrice, testProduct.getPrice(), 
            "Le prix devrait être correctement mis à jour");

        // Test prix négatif
        assertThrows(IllegalArgumentException.class, () -> {
            testProduct.setPrice(-1.0);
        }, "Un prix négatif devrait déclencher une exception");

        // Test prix trop élevé
        assertThrows(IllegalArgumentException.class, () -> {
            testProduct.setPrice(1000.0);
        }, "Un prix >= 1000 devrait déclencher une exception");
    }

    // Test de la méthode setImage avec différentes valeurs
    @Test
    public void testSetImage() {
        // Test image valide
        String validImage = "/valid/path/image.jpg";
        testProduct.setImage(validImage);
        assertEquals(validImage, testProduct.getImage(), 
            "Le chemin de l'image devrait être mis à jour");

        // Test image null
        assertThrows(IllegalArgumentException.class, () -> {
            testProduct.setImage(null);
        }, "Une image null devrait déclencher une exception");

        // Test chaîne vide
        assertThrows(IllegalArgumentException.class, () -> {
            testProduct.setImage("");
        }, "Une chaîne vide devrait déclencher une exception");
    }

    // Test de la méthode getFullDescription
    @Test
    public void testGetFullDescription() {
        String expectedDescription = testProduct.getDescription() + " : 5.0 euros";
        String result = testProduct.getFullDescription();
        assertEquals(expectedDescription, result, 
            "La description complète devrait suivre le format 'description : prix euros'");
    }

    // Test de la méthode getName
    @Test
    public void testGetName() {
        assertEquals("TestBurger", testProduct.getName(), 
            "Le nom devrait correspondre à celui défini dans le constructeur");
    }

    // Test de la méthode getImage.
    @Test
    public void testGetImage() {
        assertEquals("/some/path/", testProduct.getImage(), 
            "L'image devrait correspondre à celle définie dans le constructeur");
    }

    // Test de la méthode getPrice.
    @Test
    public void testGetPrice() {
        assertEquals(5.0, testProduct.getPrice(), 0.001, 
            "Le prix devrait correspondre à celui défini dans le constructeur");
    }
}