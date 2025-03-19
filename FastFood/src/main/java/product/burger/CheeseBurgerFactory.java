/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.burger;

/**
 *
 * @author Jallais
 */
public class CheeseBurgerFactory extends BurgerFactory {
    
    public CheeseBurgerFactory() {
        super(4.5);
    }
    
    @Override
    public Burger getBurger() {
        Burger burger = new Burger("Cheeseburger", this.price);
        burger = new Tomate(burger, 0);
        burger = new Oignon(burger, 0);
        burger = new Cheddar(burger, 0);
        burger = new Steack(burger, 0);
        burger = new Ketchup(burger, 0);
        
        return burger;
    }
}
