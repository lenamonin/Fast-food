package product.dessert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kerchoune
 */

/**
 * Tests pour la classe DessertFactory
 * On vérifie que chaque dessert est créé avec les bonnes caractéristiques
 */
public class DessertFactoryTest {
    
    private DessertFactory factory;
    
    /**
     * Initialisation d'une nouvelle factory avant chaque test
     */
    @BeforeEach
    public void setUp() {
        factory = new DessertFactory();
    }
    
    /**
     * Vérifie la création d'une glace
     * Prix attendu : 3€
     */
    @Test
    public void testGetGlace() {
        Dessert glace = factory.getGlace();
        assertEquals("Glace", glace.getName());
        assertEquals(3.0, glace.getPrice(), 0.001);
    }
    
    /**
     * Vérifie la création d'une tarte
     * Prix attendu : 2.5€
     */
    @Test
    public void testGetTarte() {
        Dessert tarte = factory.getTarte();
        assertEquals("Tarte", tarte.getName());
        assertEquals(2.5, tarte.getPrice(), 0.001);
    }
    
    /**
     * Vérifie la création d'un brownie
     * Prix attendu : 2€
     */
    @Test
    public void testGetBrownie() {
        Dessert brownie = factory.getBrownie();
        assertEquals("Brownie", brownie.getName());
        assertEquals(2.0, brownie.getPrice(), 0.001);
    }
    
    /**
     * Vérifie la création d'un fondant
     * Prix attendu : 2€
     */
    @Test
    public void testGetFondant() {
        Dessert fondant = factory.getFondant();
        assertEquals("Fondant", fondant.getName());
        assertEquals(2.0, fondant.getPrice(), 0.001);
    }
    
    /**
     * Vérifie la création d'un macaron
     * Prix attendu : 3€
     */
    @Test
    public void testGetMacaron() {
        Dessert macaron = factory.getMacaron();
        assertEquals("Macaron", macaron.getName());
        assertEquals(3.0, macaron.getPrice(), 0.001);
    }
    
    /**
     * Vérifie la création d'un donut
     * Prix attendu : 3€
     */
    @Test
    public void testGetDonut() {
        Dessert donut = factory.getDonut();
        assertEquals("Donut", donut.getName());
        assertEquals(3.0, donut.getPrice(), 0.001);
    }
    
    /**
     * Vérifie que deux appels à la même méthode créent des objets différents
     * mais avec les mêmes caractéristiques
     */
    @Test
    public void testDifferentsObjets() {
        Dessert glace1 = factory.getGlace();
        Dessert glace2 = factory.getGlace();
        
        // Vérifie que ce sont deux objets différents
        assertNotSame(glace1, glace2);
        
        // Mais avec les mêmes caractéristiques
        assertEquals(glace1.getName(), glace2.getName());
        assertEquals(glace1.getPrice(), glace2.getPrice(), 0.001);
    }
}