/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.burger;

/**
 *
 * @author Jallais
 */
public class ChickenBurgerFactory extends BurgerFactory {
    public ChickenBurgerFactory() {
        super(5.5);
    }
    
    @Override
    public Burger getBurger() {
        Burger burger = new Burger("ChickenBurger", this.price);
        burger = new Poulet(burger, 0);
        burger = new Tomate(burger, 0);
        burger = new Oignon(burger, 0);
        burger = new Cheddar(burger, 0);
        burger = new Ketchup(burger, 0);
        
        return burger;
    }
}
