package product.drink;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kerchoune
 */

/**
 * Tests pour la classe DrinkFactory
 * On vérifie que chaque boisson est créée avec les bonnes caractéristiques
 */
public class DrinkFactoryTest {
    
    private DrinkFactory factory;
    
    /**
     * Initialisation d'une nouvelle factory avant chaque test
     */
    @BeforeEach
    public void setUp() {
        factory = new DrinkFactory();
    }
    
    /**
     * Vérifie la création d'un Coca
     * Prix attendu : 2€
     */
    @Test
    public void testGetCoca() {
        // When
        Drink coca = factory.getCoca();
        
        // Then
        assertNotNull(coca, "La boisson ne doit pas être null");
        assertEquals("Coca", coca.getName(), "Le nom doit être 'Coca'");
        assertEquals(2.0, coca.getPrice(), 0.001, "Le prix doit être de 2€");
        assertEquals("drink", coca.getImage(), "L'image doit être 'drink'");
        assertEquals("Coca", coca.getDescription(), "La description doit être égale au nom");
        assertEquals("Coca : 2.0 euros", coca.getFullDescription(), 
                "La description complète doit être au format attendu");
    }
    
    /**
     * Vérifie la création d'une Eau
     * Prix attendu : 1€
     */
    @Test
    public void testGetEau() {
        // When
        Drink eau = factory.getEau();
        
        // Then
        assertNotNull(eau, "La boisson ne doit pas être null");
        assertEquals("Eau", eau.getName(), "Le nom doit être 'Eau'");
        assertEquals(1.0, eau.getPrice(), 0.001, "Le prix doit être de 1€");
        assertEquals("drink", eau.getImage(), "L'image doit être 'drink'");
        assertEquals("Eau", eau.getDescription(), "La description doit être égale au nom");
    }
    
    /**
     * Vérifie la création d'un Jus
     * Prix attendu : 2.50€
     */
    @Test
    public void testGetJus() {
        // When
        Drink jus = factory.getJus();
        
        // Then
        assertNotNull(jus, "La boisson ne doit pas être null");
        assertEquals("Jus", jus.getName(), "Le nom doit être 'Jus'");
        assertEquals(2.50, jus.getPrice(), 0.001, "Le prix doit être de 2.50€");
        assertEquals("drink", jus.getImage(), "L'image doit être 'drink'");
        assertEquals("Jus", jus.getDescription(), "La description doit être égale au nom");
    }
    
    /**
     * Vérifie la création d'un Thé
     * Prix attendu : 2€
     */
    @Test
    public void testGetThe() {
        // When
        Drink the = factory.getThe();
        
        // Then
        assertNotNull(the, "La boisson ne doit pas être null");
        assertEquals("Thé", the.getName(), "Le nom doit être 'Thé'");
        assertEquals(2.0, the.getPrice(), 0.001, "Le prix doit être de 2€");
        assertEquals("drink", the.getImage(), "L'image doit être 'drink'");
        assertEquals("Thé", the.getDescription(), "La description doit être égale au nom");
    }
    
    /**
     * Vérifie la création d'une Limonade
     * Prix attendu : 2.5€
     */
    @Test
    public void testGetLimonade() {
        // When
        Drink limonade = factory.getLimonade();
        
        // Then
        assertNotNull(limonade, "La boisson ne doit pas être null");
        assertEquals("Limonade", limonade.getName(), "Le nom doit être 'Limonade'");
        assertEquals(2.5, limonade.getPrice(), 0.001, "Le prix doit être de 2.5€");
        assertEquals("drink", limonade.getImage(), "L'image doit être 'drink'");
        assertEquals("Limonade", limonade.getDescription(), "La description doit être égale au nom");
    }
    
    /**
     * Vérifie que deux appels à la même méthode créent des objets différents
     * mais avec les mêmes caractéristiques
     */
    @Test
    public void testDifferentsObjets() {
        // When
        Drink coca1 = factory.getCoca();
        Drink coca2 = factory.getCoca();
        
        // Then
        assertNotSame(coca1, coca2, "Les deux objets doivent être différents");
        assertEquals(coca1.getName(), coca2.getName(), "Les noms doivent être identiques");
        assertEquals(coca1.getPrice(), coca2.getPrice(), 0.001, "Les prix doivent être identiques");
        assertEquals(coca1.getImage(), coca2.getImage(), "Les images doivent être identiques");
        assertEquals(coca1.getDescription(), coca2.getDescription(), "Les descriptions doivent être identiques");
        assertEquals(coca1.getFullDescription(), coca2.getFullDescription(), 
                "Les descriptions complètes doivent être identiques");
    }
}
