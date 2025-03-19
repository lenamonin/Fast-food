package product.burger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import product.Product;

/**
 *
 * @author kerchoune
 */

/**
 * Tests unitaires pour la classe BurgerFactory
 */
public class BurgerFactoryTest {

    /**
     * Une implémentation concrète de BurgerFactory pour les tests
     * Nous en avons besoin car BurgerFactory est abstraite
     */
    private class TestBurgerFactory extends BurgerFactory {
        public TestBurgerFactory(double price) {
            super(price);
        }

        @Override
        public Burger getBurger() {
            return new Burger("TestBurger", this.price);
        }
    }

    /**
     * Test du constructeur de BurgerFactory
     * Vérifie que le prix est correctement initialisé
     */
    @Test
    public void testConstructeur() {
        BurgerFactory factory = new TestBurgerFactory(5.0);
        Burger burger = factory.getBurger();
        assertEquals(5.0, burger.getPrice(), 
            "Le prix du burger devrait correspondre à celui de la factory");
    }

    /**
     * Test de l'ajout de cheddar via la méthode statique
     */
    @Test
    public void testAddCheddar() {
        // Test avec paramètre Burger
        Burger baseBurger = new Burger("TestBurger", 4.0);
        Burger burgerWithCheddar = BurgerFactory.addCheddar(baseBurger);
        
        assertEquals("TestBurger(cheddar)", burgerWithCheddar.getDescription(),
            "Le cheddar devrait être ajouté à la description");
        assertEquals(4.25, burgerWithCheddar.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 avec le cheddar");

        // Test avec paramètre Product
        Product productBurger = baseBurger;
        Product productWithCheddar = BurgerFactory.addCheddar(productBurger);
        
        assertEquals(4.25, productWithCheddar.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 même avec le type Product");
    }

    /**
     * Test de l'ajout de steak via la méthode statique
     */
    @Test
    public void testAddSteack() {
        Burger baseBurger = new Burger("TestBurger", 4.0);
        
        // Test avec paramètre Burger
        Burger burgerWithSteak = BurgerFactory.addSteack(baseBurger);
        assertEquals("TestBurger(steack)", burgerWithSteak.getDescription(),
            "Le steak devrait être ajouté à la description");
        assertEquals(4.5, burgerWithSteak.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.5 avec le steak");

        // Test avec paramètre Product
        Product productBurger = baseBurger;
        Product productWithSteak = BurgerFactory.addSteack(productBurger);
        assertEquals(4.5, productWithSteak.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.5 même avec le type Product");
    }

    /**
     * Test de l'ajout d'oignon via la méthode statique
     * Teste à la fois la version Burger et Product
     */
    @Test
    public void testAddOignon() {
        Burger baseBurger = new Burger("TestBurger", 4.0);
        
        // Test avec paramètre Burger
        Burger burgerWithOignon = BurgerFactory.addOignon(baseBurger);
        assertEquals("TestBurger(oignon)", burgerWithOignon.getDescription(),
            "L'oignon devrait être ajouté à la description");
        assertEquals(4.25, burgerWithOignon.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 avec l'oignon");

        // Test avec paramètre Product
        Product productBurger = baseBurger;
        Product productWithOignon = BurgerFactory.addOignon(productBurger);
        assertEquals(4.25, productWithOignon.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 même avec le type Product");
    }

    /**
     * Test de l'ajout de tomate via la méthode statique
     * Teste à la fois la version Burger et Product
     */
    @Test
    public void testAddTomate() {
        Burger baseBurger = new Burger("TestBurger", 4.0);
        
        // Test avec paramètre Burger
        Burger burgerWithTomate = BurgerFactory.addTomate(baseBurger);
        assertEquals("TestBurger(tomate)", burgerWithTomate.getDescription(),
            "La tomate devrait être ajoutée à la description");
        assertEquals(4.25, burgerWithTomate.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 avec la tomate");

        // Test avec paramètre Product
        Product productBurger = baseBurger;
        Product productWithTomate = BurgerFactory.addTomate(productBurger);
        assertEquals(4.25, productWithTomate.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 même avec le type Product");
    }

    /**
     * Test de l'ajout de ketchup via la méthode statique
     * Teste à la fois la version Burger et Product
     */
    @Test
    public void testAddKetchup() {
        Burger baseBurger = new Burger("TestBurger", 4.0);
        
        // Test avec paramètre Burger
        Burger burgerWithKetchup = BurgerFactory.addKetchup(baseBurger);
        assertEquals("TestBurger(ketchup)", burgerWithKetchup.getDescription(),
            "Le ketchup devrait être ajouté à la description");
        assertEquals(4.25, burgerWithKetchup.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 avec le ketchup");

        // Test avec paramètre Product
        Product productBurger = baseBurger;
        Product productWithKetchup = BurgerFactory.addKetchup(productBurger);
        assertEquals(4.25, productWithKetchup.getPrice(), 0.001,
            "Le prix devrait augmenter de 0.25 même avec le type Product");
    }

    /**
     * Test de composition multiple d'ingrédients.
     * Vérifie que plusieurs ingrédients peuvent être ajoutés
     * dans n'importe quel ordre.
     */
    @Test
    public void testCompositionMultiple() {
        Burger baseBurger = new Burger("TestBurger", 4.0);
        
        // Ajout de plusieurs ingrédients dans un ordre spécifique
        Burger burgerComplet = BurgerFactory.addKetchup(
            BurgerFactory.addCheddar(
                BurgerFactory.addTomate(baseBurger)
            )
        );
        
        assertEquals("TestBurger(tomate, cheddar, ketchup)", 
            burgerComplet.getDescription(),
            "Les ingrédients devraient apparaître dans l'ordre d'ajout");
        
        assertEquals(4.75, burgerComplet.getPrice(), 0.001,
            "Le prix devrait inclure tous les suppléments (0.25 * 3)");
    }
}