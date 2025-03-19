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
 * Tests unitaires pour la classe Burger
 * Ces tests vérifient le comportement spécifique d'un Burger
 */
public class BurgerTest {
    
    private Burger basicBurger;
    
    public BurgerTest() {
    }
    
    @BeforeEach
    public void setUp() {
        // Création d'un burger basique avant chaque test
        basicBurger = new Burger("Classic", 4.0);
    }

    /**
     * Test du constructeur de Burger
     * Vérifie que les attributs sont correctement initialisés
     */
    @Test
    public void testConstructeur() {
        assertEquals("Classic", basicBurger.getName(), 
            "Le nom du burger devrait être celui fourni au constructeur");
        assertEquals(4.0, basicBurger.getPrice(), 
            "Le prix du burger devrait être celui fourni au constructeur");
        assertEquals("/some/path/", basicBurger.getImage(), 
            "Le chemin d'image devrait être celui défini par défaut");
    }

    /**
     * Test de la méthode getDescription pour un burger sans ingrédients
     */
    @Test
    public void testGetDescriptionSansIngredients() {
        String expected = "Classic()";
        assertEquals(expected, basicBurger.getDescription(), 
            "La description d'un burger sans ingrédients devrait être 'nom()'");
    }

    /**
    * Test de la méthode getDescription avec des ingrédients
    * Vérifie que la description est correctement formatée et que
    * les ingrédients apparaissent dans l'ordre d'ajout
    */
   @Test
   public void testGetDescriptionAvecIngredients() {
       Burger burgerComplet = new Cheddar(
           new Steack(
               new Tomate(basicBurger, 0.5),
           0.5),
       0.5);

       String expected = "Classic(tomate, steack, cheddar)";
       assertEquals(expected, burgerComplet.getDescription(), 
           "La description devrait inclure les ingrédients dans l'ordre : tomate, steack, puis cheddar");
   }

    /**
     * Test de la méthode getIngredientList pour un burger de base
     * Vérifie que la liste d'ingrédients est vide par défaut
     */
    @Test
    public void testGetIngredientList() {
        assertEquals("", basicBurger.getIngredientList(), 
            "Un burger de base ne devrait pas avoir d'ingrédients");
    }

    /**
     * Test de l'héritage de Product
     * Vérifie que les méthodes héritées fonctionnent correctement
     */
    @Test
    public void testHeritageProduct() {
        String expectedFullDescription = "Classic() : 4.0 euros";
        assertEquals(expectedFullDescription, basicBurger.getFullDescription(), 
            "La méthode héritée getFullDescription devrait fonctionner correctement");
    }

    /**
     * Test de la construction d'un burger avec un prix invalide
     * Vérifie que les contraintes de prix de la classe parente sont respectées
     */
    @Test
    public void testConstructeurPrixInvalide() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Burger("Test", -1.0);
        }, "Un prix négatif devrait déclencher une exception");

        /** assertThrows(IllegalArgumentException.class, () -> {
            new Burger("Test", 1000.0);
        }, "Un prix supérieur ou égal à 1000 devrait déclencher une exception"); */
    }
    
    /**
     * Test de la gestion des ingrédients avec des prix
     * Vérifie que le prix total est correctement calculé avec les ingrédients
     */
    @Test
    public void testPrixAvecIngredients() {
        Burger burgerComplet = new Tomate(
            new Steack(
                new Cheddar(basicBurger, 0.5),
            0.5),
        0.5);
        
        double expectedPrice = 4.0 + 0.5 + 0.5 + 0.5; // Prix de base + ingrédients
        assertEquals(expectedPrice, burgerComplet.getPrice(), 0.001, 
            "Le prix total devrait inclure le prix de base plus celui des ingrédients");
    }
}