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
 * Tests unitaires pour le décorateur Bacon
 * Ces tests vérifient le comportement spécifique du décorateur Bacon
 */
public class BaconTest {
    
    private Burger basicBurger;
    private Bacon baconBurger;
    private final double BACON_PRICE = 0.5; // Prix du supplément bacon pour les tests

    @BeforeEach
    public void setUp() {
        // Nous créons un burger de base et son décorateur avant chaque test
        basicBurger = new Burger("TestBurger", 4.0);
        baconBurger = new Bacon(basicBurger, BACON_PRICE);
    }
    

    /**
     * Test du constructeur du décorateur Bacon
     */
    @Test
    public void testConstructeur() {
        assertEquals("TestBurger", baconBurger.getName(), 
            "Le nom du burger ne devrait pas changer avec l'ajout du bacon");
        assertEquals("/some/path/", baconBurger.getImage(), 
            "L'image du burger ne devrait pas changer avec l'ajout du bacon");
    }

    /**
     * Test de la méthode getIngredientList
     * Vérifie que le bacon est correctement ajouté à la liste des ingrédients
     */
    @Test
    public void testGetIngredientList() {
        String expectedList = "bacon, ";
        assertEquals(expectedList, baconBurger.getIngredientList(), 
            "La liste d'ingrédients devrait contenir uniquement 'bacon, ' pour un burger de base");
        
        // Test avec un burger qui a déjà des ingrédients
        Burger burgerWithIngredients = new Bacon(
            new Tomate(basicBurger, 0.5),
            BACON_PRICE
        );
        assertEquals("tomate, bacon, ", burgerWithIngredients.getIngredientList(), 
            "Le bacon devrait être ajouté après les ingrédients existants");
    }

    /**
     * Test de la méthode getPrice
     * Vérifie que le prix du bacon est correctement ajouté au prix du burger
     */
    @Test
    public void testGetPrice() {
        double expectedPrice = 4.0 + BACON_PRICE;
        assertEquals(expectedPrice, baconBurger.getPrice(), 0.001, 
            "Le prix devrait être la somme du prix du burger et du supplément bacon");
        
        // Test avec un burger qui a déjà des suppléments
        Burger burgerWithExtras = new Bacon(
            new Tomate(basicBurger, 0.5),
            BACON_PRICE
        );
        double expectedPriceWithExtras = 4.0 + 0.5 + BACON_PRICE;
        assertEquals(expectedPriceWithExtras, burgerWithExtras.getPrice(), 0.001, 
            "Le prix devrait inclure tous les suppléments");
    }

    /**
     * Test de la méthode getDescription héritée
     * Vérifie que la description inclut correctement le bacon
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "TestBurger(bacon)";
        assertEquals(expectedDescription, baconBurger.getDescription(), 
            "La description devrait inclure le bacon comme ingrédient");
    }

    /**
     * Test de l'intégration avec getFullDescription
     */
    @Test
    public void testGetFullDescription() {
        double totalPrice = 4.0 + BACON_PRICE;
        String expectedFullDescription = "TestBurger(bacon) : " + totalPrice + " euros";
        assertEquals(expectedFullDescription, baconBurger.getFullDescription(), 
            "La description complète devrait inclure le bacon et le prix total");
    }
}