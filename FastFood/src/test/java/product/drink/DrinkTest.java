package product.drink;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import general.Describable;
import general.Purchasable;

/**
 *
 * @author kerchoune
 */

/**
 * Tests pour la classe Drink
 * On vérifie le comportement des boissons en tant que produits
 */
public class DrinkTest {
    
    /**
     * Test la création d'une boisson avec des valeurs normales
     */
    @Test
    public void testConstructeurValide() {
        Drink boisson = new Drink("Coca Cola", 2.50);
        assertEquals("Coca Cola", boisson.getName());
        assertEquals(2.50, boisson.getPrice(), 0.001);
        assertEquals("drink", boisson.getImage());
    }
    
    /**
     * Test de getDescription
     * Vérifie que la description est identique au nom pour une boisson
     */
    @Test
    public void testGetDescription() {
        String nomBoisson = "Fanta Orange";
        Drink boisson = new Drink(nomBoisson, 2.00);
        assertEquals(nomBoisson, boisson.getDescription(), 
                "La description d'une boisson doit être son nom");
    }
    
    /**
     * Test de getFullDescription
     * Vérifie le format "{description} : {prix} euros"
     */
    @Test
    public void testGetFullDescription() {
        Drink boisson = new Drink("Sprite", 2.50);
        String expectedDescription = "Sprite : 2.5 euros";
        assertEquals(expectedDescription, boisson.getFullDescription());
    }
    
    /**
     * Test avec un prix négatif
     * Doit lever une IllegalArgumentException
     */
    @Test
    public void testPrixNegatif() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Drink("Ice Tea", -1.0);
        }, "Un prix négatif devrait lever une exception");
        
        assertEquals("Le prix du produit ne peut pas être négatif.", 
                exception.getMessage());
    }
    
    /**
     * Test avec un prix trop élevé (>= 1000)
     * Doit lever une IllegalArgumentException
     */
    @Test
    public void testPrixTropEleve() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Drink("Champagne Luxe", 1000.0);
        }, "Un prix >= 1000 devrait lever une exception");
        
        assertEquals("Le prix du produit ne peut pas dépasser 999.", 
                exception.getMessage());
    }
    
    /**
     * Test avec un prix à la limite supérieure
     * Doit accepter un prix de 999.99
     */
    @Test
    public void testPrixLimite() {
        Drink boisson = new Drink("Champagne Premium", 999.99);
        assertEquals(999.99, boisson.getPrice(), 0.001);
    }
    
    /**
     * Test avec un prix à zéro
     * Prix zéro devrait être accepté
     */
    @Test
    public void testPrixZero() {
        Drink boisson = new Drink("Eau gratuite", 0.0);
        assertEquals(0.0, boisson.getPrice(), 0.001);
    }
    
    /**
     * Vérifie que le nom vide est accepté
     * Le comportement par défaut de Product l'autorise
     */
    @Test
    public void testNomVide() {
        Drink boisson = new Drink("", 2.00);
        assertEquals("", boisson.getName());
        assertEquals("", boisson.getDescription());
    }
    
    /**
     * Test des interfaces implémentées
     * Vérifie que Drink implémente bien Purchasable et Describable via Product
     */
    @Test
    public void testInterfaces() {
        Drink boisson = new Drink("Jus d'orange", 2.50);
        assertTrue(boisson instanceof Purchasable, 
                "Une boisson doit implémenter Purchasable");
        assertTrue(boisson instanceof Describable, 
                "Une boisson doit implémenter Describable");
    }
    
    /**
     * Test que l'image est toujours "drink"
     * Vérifie la constante utilisée dans le constructeur
     */
    @Test
    public void testImage() {
        Drink boisson1 = new Drink("Café", 1.50);
        Drink boisson2 = new Drink("Thé", 1.50);
        assertEquals("drink", boisson1.getImage(), 
                "L'image doit toujours être 'drink'");
        assertEquals("drink", boisson2.getImage(), 
                "L'image doit toujours être 'drink'");
    }
}