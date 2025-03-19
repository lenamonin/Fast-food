/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.burger;

/**
 *
 * @author Jallais
 */
public class HamburgerFactory extends BurgerFactory {
    
    public HamburgerFactory() {
        super(4.0);
    }
    
    @Override
    public Burger getBurger() {
        Burger burger = new Burger("Hamburger", this.price);
        burger = new Tomate(burger, 0);
        burger = new Oignon(burger, 0);
        burger = new Steack(burger, 0);
        burger = new Ketchup(burger, 0);
        
        return burger;
    }
}
