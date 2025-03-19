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
 * Tests unitaires pour le décorateur Oignon
 * Oignon est un ingrédient pour les burgers
 */
public class OignonTest {
    
    private Burger basicBurger;
    private Oignon oignonBurger;
    private final double OIGNON_PRICE = 0.25;
    
    
    @BeforeEach
    public void setUp() {
        // Création d'un nouveau burger et de sa version avec oignon avant chaque test
        basicBurger = new Burger("TestBurger", 4.0);
        oignonBurger = new Oignon(basicBurger, OIGNON_PRICE);
    }


    /**
     * Test du constructeur du décorateur Oignon
     * Vérifie que les propriétés de base du burger sont préservées
     * lors de l'ajout des oignons
     */
    @Test
    public void testConstructeur() {
        assertEquals("TestBurger", oignonBurger.getName(), 
            "Le nom du burger doit rester inchangé après l'ajout d'oignon");
        assertEquals("/some/path/", oignonBurger.getImage(), 
            "Le chemin de l'image doit rester inchangé après l'ajout d'oignon");
    }

    /**
     * Test de la méthode getIngredientList
     * Vérifie l'ajout correct de l'oignon dans différentes configurations d'ingrédients
     */
    @Test
    public void testGetIngredientList() {
        // Test avec un burger simple
        String expectedList = "oignon, ";
        assertEquals(expectedList, oignonBurger.getIngredientList(), 
            "La liste d'ingrédients d'un burger avec uniquement de l'oignon doit être 'oignon, '");
        
        // Test avec un burger qui contient déjà d'autres ingrédients
        Burger burgerComplet = new Oignon(
            new Tomate(basicBurger, 0.5),
            OIGNON_PRICE
        );
        assertEquals("tomate, oignon, ", burgerComplet.getIngredientList(), 
            "L'oignon doit être ajouté après les ingrédients existants");
    }

    /**
     * Test de la méthode getPrice
     * Vérifie que le prix de l'oignon est correctement ajouté dans différentes situations
     */
    @Test
    public void testGetPrice() {
        // Test du prix avec uniquement l'ajout d'oignon
        double expectedPrice = 4.0 + OIGNON_PRICE;
        assertEquals(expectedPrice, oignonBurger.getPrice(), 0.001, 
            "Le prix doit être la somme du burger de base et du supplément oignon");
        
        // Test du prix avec plusieurs ingrédients
        Burger burgerComplet = new Oignon(
            new Tomate(basicBurger, 0.5),
            OIGNON_PRICE
        );
        double expectedPriceWithExtras = 4.0 + 0.5 + OIGNON_PRICE;
        assertEquals(expectedPriceWithExtras, burgerComplet.getPrice(), 0.001, 
            "Le prix doit inclure tous les suppléments correctement");
    }

    /**
     * Test de la méthode getDescription
     * Vérifie que l'oignon apparaît correctement dans la description du burger
     */
    @Test
    public void testGetDescription() {
        String expectedDescription = "TestBurger(oignon)";
        assertEquals(expectedDescription, oignonBurger.getDescription(), 
            "La description doit mentionner l'oignon comme ingrédient");
    }

    /**
     * Test de l'intégration avec d'autres ingrédients
     * Vérifie le comportement du décorateur Oignon dans une composition multiple
     */
    @Test
    public void testCompositionMultiple() {
        // Création d'un burger avec plusieurs ingrédients dans un ordre spécifique
        Burger burgerComplet = new Ketchup(
            new Oignon(
                new Tomate(basicBurger, 0.5),
                OIGNON_PRICE
            ),
            0.25
        );
        
        String expectedDescription = "TestBurger(tomate, oignon, ketchup)";
        assertEquals(expectedDescription, burgerComplet.getDescription(), 
            "La description doit avoir  l'ordre correct des ingrédients");
        
        double expectedPrice = 4.0 + 0.5 + OIGNON_PRICE + 0.25;
        assertEquals(expectedPrice, burgerComplet.getPrice(), 0.001, 
            "Le prix total doit inclure tous les suppléments");
    }

    /**
     * Test de getFullDescription
     * Vérifie que la description complète inclut l'oignon et le prix correct
     */
    @Test
    public void testGetFullDescription() {
        double totalPrice = 4.0 + OIGNON_PRICE;
        String expectedFullDescription = "TestBurger(oignon) : " + totalPrice + " euros";
        assertEquals(expectedFullDescription, oignonBurger.getFullDescription(), 
            "La description complète doit inclure l'oignon et le prix total correct");
    }
}