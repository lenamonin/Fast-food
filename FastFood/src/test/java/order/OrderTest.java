package order;

import java.util.ArrayList;
import menu.Menu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import product.Product;

/**
 *
 * @author kerchoune
 */

/**
 * Tests unitaires pour la classe Order
 * Cette classe teste toutes les fonctionnalités de gestion des commandes,
 * incluant l'ajout/suppression de produits et menus, le calcul du prix,
 * et la génération des descriptions
 */
public class OrderTest {
    
    // Classe Product concrète pour les tests
    private static class TestProduct extends Product {
        public TestProduct(String name, double price) {
            super(name, price, "test_image.jpg");
        }
        
        @Override
        public String getDescription() {
            return this.name;
        }
    }
    
    // Les objets utilisés dans les tests
    private Order order;
    private TestProduct product1;
    private TestProduct product2;
    private Menu menu1;
    private Menu menu2;
    
    @BeforeEach
    public void setUp() {
        // Initialisation des objets avant chaque test
        order = new Order();
        product1 = new TestProduct("Test Product 1", 10.0);
        product2 = new TestProduct("Test Product 2", 15.0);
        menu1 = new Menu("Test Menu 1", 20.0);
        menu2 = new Menu("Test Menu 2", 25.0);
    }
    
    @AfterEach
    public void tearDown() {
        // Nettoyage après chaque test
        order = null;
        product1 = null;
        product2 = null;
        menu1 = null;
        menu2 = null;
    }

    /**
     * Test de la méthode setState
     * Vérifie que l'état de la commande peut être modifié
     */
    @Test
    public void testSetState() {
        String newState = "En livraison";
        order.setState(newState);
        // L'état étant privé, nous vérifions simplement qu'aucune exception n'est levée
    }

    /**
     * Test de la méthode getName
     */
    @Test
    public void testGetName() {
        String result = order.getName();
        assertTrue(result.startsWith("Commande n°"), 
                "Le nom devrait commencer par 'Commande n°'");
    }

    /**
     * Test de la méthode copy
     * Vérifie que la copie d'une commande est indépendante de l'original
     */
    @Test
    public void testCopy() {
        order.addProduct(product1);
        order.addMenu(menu1);
        
        Order copiedOrder = order.copy();
        assertNotNull(copiedOrder, "La copie ne devrait pas être null");
        assertEquals(order.getProducts().size(), copiedOrder.getProducts().size(),
                "La copie devrait avoir le même nombre de produits");
        
        // Modification de la copie pour vérifier l'indépendance
        copiedOrder.addProduct(product2);
        assertNotEquals(order.getProducts().size(), copiedOrder.getProducts().size(),
                "Les modifications de la copie ne devraient pas affecter l'original");
    }

    /**
     * Test de la méthode clearOrder
     * Vérifie que la commande est correctement vidée
     */
    @Test
    public void testClearOrder() {
        order.addProduct(product1);
        order.addMenu(menu1);
        order.clearOrder();
        
        assertTrue(order.getProducts().isEmpty(), 
                "La liste des produits devrait être vide après clear");
        assertTrue(order.getMenus().isEmpty(), 
                "La liste des menus devrait être vide après clear");
    }

    /**
     * Test de la méthode removeProduct
     * Vérifie la suppression de produits
     */
    @Test
    public void testRemoveProduct() {
        order.addProduct(product1);
        order.removeProduct(product1);
        assertTrue(order.getProducts().isEmpty(), 
                "Le produit devrait être supprimé");
        
        // Test de suppression d'un produit non existant
        order.removeProduct(product2);
        assertTrue(order.getProducts().isEmpty(), 
                "La suppression d'un produit non existant ne devrait pas avoir d'effet");
    }

    /**
     * Test de la méthode removeMenu
     * Vérifie la suppression de menus
     */
    @Test
    public void testRemoveMenu() {
        order.addMenu(menu1);
        order.removeMenu(menu1);
        assertTrue(order.getMenus().isEmpty(), 
                "Le menu devrait être supprimé");
        
        // Test de suppression d'un menu non existant
        order.removeMenu(menu2);
        assertTrue(order.getMenus().isEmpty(), 
                "La suppression d'un menu non existant ne devrait pas avoir d'effet");
    }

    /**
     * Test de la méthode addProduct
     * Vérifie l'ajout de produits à la commande
     */
    @Test
    public void testAddProduct() {
        order.addProduct(product1);
        assertEquals(1, order.getProducts().size(), 
                "La liste devrait contenir un produit");
        assertTrue(order.getProducts().contains(product1), 
                "Le produit ajouté devrait être dans la liste");
    }

    /**
     * Test de la méthode addMenu
     * Vérifie l'ajout de menus à la commande
     */
    @Test
    public void testAddMenu() {
        order.addMenu(menu1);
        assertEquals(1, order.getMenus().size(), 
                "La liste devrait contenir un menu");
        assertTrue(order.getMenus().contains(menu1), 
                "Le menu ajouté devrait être dans la liste");
    }

    /**
     * Test de la méthode getProducts
     * Vérifie la récupération de la liste des produits
     */
    @Test
    public void testGetProducts() {
        order.addProduct(product1);
        ArrayList<Product> result = order.getProducts();
        assertNotNull(result, "La liste ne devrait pas être null");
        assertEquals(1, result.size(), 
                "La liste devrait contenir un produit");
    }

    /**
     * Test de la méthode getMenus
     * Vérifie la récupération de la liste des menus
     */
    @Test
    public void testGetMenus() {
        order.addMenu(menu1);
        ArrayList<Menu> result = order.getMenus();
        assertNotNull(result, "La liste ne devrait pas être null");
        assertEquals(1, result.size(), 
                "La liste devrait contenir un menu");
    }

    /**
     * Test de la méthode getDescription
     * Vérifie la génération de la description de la commande
     */
    @Test
    public void testGetDescription() {
        order.addProduct(product1);
        order.addMenu(menu1);
        String description = order.getDescription();
        
        assertTrue(description.contains("Les produits"),
                "La description devrait contenir la section produits");
        assertTrue(description.contains("Les menus"),
                "La description devrait contenir la section menus");
        assertTrue(description.contains(product1.getName()),
                "La description devrait contenir le nom du produit");
        assertTrue(description.contains(menu1.getName()),
                "La description devrait contenir le nom du menu");
    }

    /**
     * Test de la méthode getPrice
     * Vérifie le calcul du prix total de la commande
     */
    @Test
    public void testGetPrice() {
        order.addProduct(product1);  // 10.0€
        order.addProduct(product2);  // 15.0€
        order.addMenu(menu1);        // 20.0€
        
        double expectedPrice = 45.0;
        double result = order.getPrice();
        assertEquals(expectedPrice, result, 0.001,
                "Le prix total devrait être la somme des prix des produits et menus");
    }
}