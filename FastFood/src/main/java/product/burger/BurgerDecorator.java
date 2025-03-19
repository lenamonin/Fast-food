/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.burger;

/**
 *
 * @author Jallais
 */
public abstract class BurgerDecorator extends Burger {
    protected Burger burger;
    
    public BurgerDecorator(Burger burger, double price) {
        super(burger.getName(), price);
        this.burger = burger;
    }
        
    @Override
    public abstract String getIngredientList();
    
    @Override
    public abstract double getPrice();
}
