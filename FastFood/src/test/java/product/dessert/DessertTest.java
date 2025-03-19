package product.dessert;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author kerchoune
 */

/**
 * Tests de la classe Dessert
 * On vérifie le comportement des desserts en tant que produits
 */
public class DessertTest {
    
    /**
     * Test la création d'un dessert avec des valeurs normales
     */
    @Test
    public void testConstructeurValide() {
        Dessert dessert = new Dessert("Tarte aux pommes", 4.50);
        assertEquals("Tarte aux pommes", dessert.getName());
        assertEquals(4.50, dessert.getPrice(), 0.001);
        assertEquals("test", dessert.getImage());
    }
    
    /**
     * Test de getDescription
     * Vérifie que la description est identique au nom pour un dessert
     */
    @Test
    public void testGetDescription() {
        String nomDessert = "Éclair au chocolat";
        Dessert dessert = new Dessert(nomDessert, 3.00);
        assertEquals(nomDessert, dessert.getDescription(), 
                "La description d'un dessert doit être son nom");
    }
    
    /**
     * Test de getFullDescription
     * Vérifie le format "{description} : {prix} euros"
     */
    @Test
    public void testGetFullDescription() {
        Dessert dessert = new Dessert("Mille-feuille", 5.50);
        String expectedDescription = "Mille-feuille : 5.5 euros";
        assertEquals(expectedDescription, dessert.getFullDescription());
    }
    
    /**
     * Test avec un prix négatif
     * Doit lever une IllegalArgumentException
     */
    @Test
    public void testPrixNegatif() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Dessert("Crème brûlée", -1.0);
        }, "Un prix négatif devrait lever une exception");
    }
    
    /**
     * Test avec un prix trop élevé (>= 1000)
     * Doit lever une IllegalArgumentException
     */
    @Test
    public void testPrixTropEleve() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Dessert("Gâteau de luxe", 1000.0);
        }, "Un prix >= 1000 devrait lever une exception");
    }
    
    /**
     * Test avec un prix à la limite supérieure
     * Doit accepter un prix de 999.99
     */
    @Test
    public void testPrixLimite() {
        Dessert dessert = new Dessert("Gâteau premium", 999.99);
        assertEquals(999.99, dessert.getPrice(), 0.001);
    }
    
    /**
     * Test avec un prix à zéro
     * Prix zéro devrait être accepté
     */
    @Test
    public void testPrixZero() {
        Dessert dessert = new Dessert("Dessert gratuit", 0.0);
        assertEquals(0.0, dessert.getPrice(), 0.001);
    }
    
    /**
     * Vérifie que le nom vide est accepté
     * Le comportement par défaut de Product l'autorise
     */
    @Test
    public void testNomVide() {
        Dessert dessert = new Dessert("", 2.00);
        assertEquals("", dessert.getName());
        assertEquals("", dessert.getDescription());
    }
}