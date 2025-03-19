/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.drink;

/**
 *
 * @author Jallais
 */
public class DrinkFactory {
    public Drink getCoca() {
        return new Drink("Coca", 2);
    }
    
    public Drink getEau() {
        return new Drink("Eau", 1);
    }
    
    public Drink getJus() {
        return new Drink("Jus", 2.50);
    }
    
    public Drink getThe() {
        return new Drink("Thé", 2);
    }
    
    public Drink getLimonade() {
        return new Drink("Limonade", 2.5);
    }
}
