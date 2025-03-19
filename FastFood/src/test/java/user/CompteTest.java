package user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kerchoune
 */

/**
 * Tests pour la classe Compte
 * On vérifie la création et les fonctionnalités des comptes utilisateurs
 */
public class CompteTest {
    
    /**
     * Test du constructeur avec des valeurs normales
     */
    @Test
    public void testConstructeur() {
        String nomUtilisateur = "Jean Dupont";
        String codeFidelite = "ABC123";
        
        Compte compte = new Compte(nomUtilisateur, codeFidelite);
        
        assertNotNull(compte, "Le compte ne devrait pas être null");
        assertEquals(nomUtilisateur, compte.getNomUtilisateur(), 
                "Le nom d'utilisateur devrait être correctement enregistré");
        assertEquals(codeFidelite, compte.getCodeFidelite(), 
                "Le code de fidélité devrait être correctement enregistré");
    }
    
    /**
     * Test de getNomUtilisateur
     */
    @Test
    public void testGetNomUtilisateur() {
        String nomUtilisateur = "Marie Martin";
        Compte compte = new Compte(nomUtilisateur, "XYZ789");
        
        String result = compte.getNomUtilisateur();
        
        assertEquals(nomUtilisateur, result, 
                "getNomUtilisateur devrait retourner le nom stocké");
    }
    
    /**
     * Test de getCodeFidelite
     */
    @Test
    public void testGetCodeFidelite() {
        String codeFidelite = "123ABC";
        Compte compte = new Compte("Pierre Durand", codeFidelite);
   
        String result = compte.getCodeFidelite();
        
        assertEquals(codeFidelite, result, 
                "getCodeFidelite devrait retourner le code stocké");
    }
    
    /**
     * Test de verifierCode avec un code valide
     */
    @Test
    public void testVerifierCodeValide() {
        String codeFidelite = "CODE123";
        Compte compte = new Compte("Sophie Bernard", codeFidelite);
        
        assertTrue(compte.verifierCode(codeFidelite), 
                "Le code identique devrait être validé");
    }
    
    /**
     * Test de verifierCode avec un code invalide
     */
    @Test
    public void testVerifierCodeInvalide() {
        Compte compte = new Compte("Luc Petit", "CODE123");
        
        assertFalse(compte.verifierCode("WRONG123"), 
                "Un code différent ne devrait pas être validé");
    }
    
    /**
     * Test de verifierCode avec des espaces en trop
     */
    @Test
    public void testVerifierCodeAvecEspaces() {
        String codeFidelite = "CODE123";
        Compte compte = new Compte("Antoine Dubois", codeFidelite);
        
        assertTrue(compte.verifierCode("  CODE123  "), 
                "Le code avec des espaces devrait être validé après trim");
    }
    
    /**
     * Test de verifierCode avec un code null
     */
    @Test
    public void testVerifierCodeNull() {
        Compte compte = new Compte("Claire Martin", "CODE123");
        
        assertThrows(NullPointerException.class, () -> {
            compte.verifierCode(null);
        }, "La vérification d'un code null devrait lever une exception");
    }
    
    /**
     * Test avec un nom d'utilisateur vide
     */
    @Test
    public void testConstructeurNomVide() {
        String nomVide = "";
        Compte compte = new Compte(nomVide, "CODE123");
        
        String result = compte.getNomUtilisateur();

        assertEquals(nomVide, result, 
                "Un nom d'utilisateur vide devrait être accepté");
    }
    
    /**
     * Test avec un code de fidélité vide
     */
    @Test
    public void testConstructeurCodeVide() {
        String codeVide = "";
        Compte compte = new Compte("Paul Durand", codeVide);
        
        String result = compte.getCodeFidelite();
        
        assertEquals(codeVide, result, 
                "Un code de fidélité vide devrait être accepté");
    }
}