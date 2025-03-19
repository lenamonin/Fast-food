package product.burger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kerchoune
 */

/**
 * Tests unitaires pour le décorateur Poulet
 * Poulet est un ingrédient disponible pour les burgers
 */
public class PouletTest {
    
    private Burger basicBurger;
    private Poulet pouletBurger;
    private final double POULET_PRICE = 0.5;
    
    @BeforeEach
    public void setUp() {
        // Création d'un burger de base et de sa version avec poulet avant chaque test
        basicBurger = new Burger("TestBurger", 4.0);
        pouletBurger = new Poulet(basicBurger, POULET_PRICE);
    }

    /**
     * Test du constructeur du décorateur Poulet
     */
    @Test
    public void testConstructeur() {
        assertEquals("TestBurger", pouletBurger.getName(), 
            "Le nom du burger doit rester inchangé après l'ajout du poulet");
        assertEquals("/some/path/", pouletBurger.getImage(), 
            "Le chemin de l'image doit rester inchangé après l'ajout du poulet");
        
        // Test avec un prix nul qui devrait être accepté (cas des menus ou promotions)
        Poulet gratuitPoulet = new Poulet(basicBurger, 0.0);
        assertEquals(basicBurger.getPrice(), gratuitPoulet.getPrice(), 
            "Un supplément poulet à 0€ ne devrait pas modifier le prix du burger");
    }

    /**
     * Test de la méthode getIngredientList
     */
    @Test
    public void testGetIngredientList() {
        // Test avec uniquement du poulet
        String expectedList = "poulet, ";
        assertEquals(expectedList, pouletBurger.getIngredientList(), 
            "Un burger avec uniquement du poulet doit afficher 'poulet, '");
        
        // Test d'un ChickenBurger typique avec plusieurs ingrédients
        Burger chickenBurger = new Ketchup(
            new Cheddar(
                new Poulet(basicBurger, POULET_PRICE),
                0.25
            ),
            0.25
        );
        
        assertEquals("poulet, cheddar, ketchup, ", chickenBurger.getIngredientList(), 
            "Le poulet doit apparaître en premier, suivi des autres ingrédients");
    }

    /**
     * Test de la méthode getPrice
     */
    @Test
    public void testGetPrice() {
        // Test du prix avec uniquement l'ajout de poulet
        double expectedPrice = 4.0 + POULET_PRICE;
        assertEquals(expectedPrice, pouletBurger.getPrice(), 0.001, 
            "Le prix doit être la somme du burger de base et du supplément poulet");
        
        // Test d'un ChickenBurger complet
        Burger chickenBurgerComplet = new Ketchup(
            new Cheddar(
                new Poulet(basicBurger, POULET_PRICE),
                0.25
            ),
            0.25
        );
        
        double expectedPriceComplet = 4.0 + POULET_PRICE + 0.25 + 0.25;
        assertEquals(expectedPriceComplet, chickenBurgerComplet.getPrice(), 0.001, 
            "Le prix total doit inclure le burger de base, le poulet et tous les suppléments");
    }

    /**
     * Test des descriptions du burger avec poulet
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "TestBurger(poulet)";
        assertEquals(expectedDescription, pouletBurger.getDescription(), 
            "La description doit mentionner le poulet comme ingrédient");
    }

    /**
     * Test des combinaisons courantes pour un burger au poulet
     */
    @Test
    public void testCombinaisons() {
        // Test d'un "Chicken Deluxe" (poulet, tomate, salade, sauce)
        Burger chickenDeluxe = new Ketchup(
            new Tomate(
                new Poulet(basicBurger, POULET_PRICE),
                0.25
            ),
            0.25
        );
        
        assertEquals("TestBurger(poulet, tomate, ketchup)", chickenDeluxe.getDescription(), 
            "La description doit refléter la composition complète du Chicken Deluxe");
        
        double expectedPrice = 4.0 + POULET_PRICE + 0.25 + 0.25;
        assertEquals(expectedPrice, chickenDeluxe.getPrice(), 0.001, 
            "Le prix doit inclure tous les ingrédients du Chicken Deluxe");
    }

    /**
     * Test de la description complète incluant le prix
     */
    @Test
    public void testGetFullDescription() {
        double totalPrice = 4.0 + POULET_PRICE;
        String expectedFullDescription = "TestBurger(poulet) : " + totalPrice + " euros";
        assertEquals(expectedFullDescription, pouletBurger.getFullDescription(), 
            "La description complète doit inclure le poulet et le prix total correct");
    }
}