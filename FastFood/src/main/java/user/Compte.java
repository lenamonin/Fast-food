/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

/**
 *
 * @author HP
 */
public class Compte {
      private String nomUtilisateur;
    private String codeFidelite;
    // private int nbPoints;

    // Constructeur
    public Compte(String nomUtilisateur, String codeFidelite) {
        this.nomUtilisateur = nomUtilisateur;
        this.codeFidelite = codeFidelite;
    }

  
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getCodeFidelite() {
        return codeFidelite;
    }

    // Méthode pour vérifier si deux comptes sont égaux 
    public boolean verifierCode(String code) {
        return this.codeFidelite.equals(code.trim());
    }
}

