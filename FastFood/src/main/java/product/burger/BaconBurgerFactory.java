/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.burger;

/**
 *
 * @author Jallais
 */
public class BaconBurgerFactory extends BurgerFactory {
    public BaconBurgerFactory() {
        super(5.0);
    }
    
    @Override
    public Burger getBurger() {
        Burger burger = new Burger("BaconBurger", this.price);
        burger = new Bacon(burger, 0);
        burger = new Oignon(burger, 0);
        burger = new Cheddar(burger, 0);
        burger = new Ketchup(burger, 0);
        
        return burger;
    }
}
