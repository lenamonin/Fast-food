/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.dessert;

/**
 *
 * @author Jallais
 */
public class DessertFactory {
    public Dessert getGlace() {
        return new Dessert("Glace", 3);
    }
    
    public Dessert getTarte() {
        return new Dessert("Tarte", 2.5);
    }
    
    public Dessert getBrownie() {
        return new Dessert("Brownie", 2);
    }
    
    public Dessert getFondant() {
        return new Dessert("Fondant", 2);
    }
    
    public Dessert getMacaron() {
        return new Dessert("Macaron", 3);
    }
    
    public Dessert getDonut() {
        return new Dessert("Donut", 3);
    }
}
