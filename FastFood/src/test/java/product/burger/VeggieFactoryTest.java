package product.burger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kerchoune
 */

/**
 * Tests unitaires pour la VeggieFactory
 * Cette classe est responsable de la création des burgers végétariens
 */
public class VeggieFactoryTest {

    /**
     * Test du constructeur de VeggieFactory
     * Vérifie que le prix de base est correctement initialisé à 3.5€,
     * qui est le prix standard
     */
    @Test
    public void testConstructeur() {
        VeggieFactory factory = new VeggieFactory();
        Burger burger = factory.getBurger();
        assertEquals(3.5, burger.getPrice(), 0.001,
            "Le prix du Veggie burger devrait être fixé à 3.5€");
    }

    /**
     * Test de la méthode getBurger
     * Cette méthode vérifie les aspects suivant :
     * - Son nom
     * - Ses ingrédients et leur ordre
     * - Son prix total (qui doit rester à 3.5€)
     */
    @Test
    public void testGetBurger() {
        VeggieFactory factory = new VeggieFactory();
        Burger veggieBurger = factory.getBurger();

        // Vérifie le nom du burger
        assertEquals("Veggie", veggieBurger.getName(),
            "Le burger devrait s'appeler 'Veggie'");

        // Vérifie le prix
        assertEquals(3.5, veggieBurger.getPrice(), 0.001,
            "Le prix total devrait rester à 3.5€, les ingrédients étant inclus");

        // Vérifie la composition complète avec tous les ingrédients
        String expectedDescription = "Veggie(tomate, oignon, cheddar, ketchup)";
        assertEquals(expectedDescription, veggieBurger.getDescription(),
            "Le Veggie burger devrait contenir tomate, oignon, cheddar et ketchup dans cet ordre");
    }

    /**
     * Test de l'intégration avec les méthodes de BurgerFactory
     * Vérifie que le burger végétarien peut être modifié comme n'importe quel autre burger
     */
    @Test
    public void testModificationBurger() {
        VeggieFactory factory = new VeggieFactory();
        Burger veggieBurger = factory.getBurger();
        
        // Ajoute plusieurs ingrédients supplémentaires
        Burger modifiedVeggie = BurgerFactory.addKetchup(
            BurgerFactory.addOignon(veggieBurger)
        );

        // Vérifie que le prix a augmenté correctement (3.5 + 0.25 + 0.25)
        assertEquals(4.0, modifiedVeggie.getPrice(), 0.001,
            "Le prix devrait inclure le prix de base plus les suppléments");

        // Vérifie que les nouveaux ingrédients sont ajoutés à la liste existante
        assertTrue(modifiedVeggie.getDescription().contains("ketchup"),
            "Les nouveaux ingrédients devraient être ajoutés à la description");
    }

    /**
     * Test de la description complète du burger
     * Vérifie le format de la description incluant le prix
     */
    @Test
    public void testGetFullDescription() {
        VeggieFactory factory = new VeggieFactory();
        Burger veggieBurger = factory.getBurger();
        
        String expectedFullDescription = "Veggie(tomate, oignon, cheddar, ketchup) : 3.5 euros";
        assertEquals(expectedFullDescription, veggieBurger.getFullDescription(),
            "La description complète devrait inclure tous les ingrédients et le prix");
    }
}