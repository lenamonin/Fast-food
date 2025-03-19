/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.burger;

/**
 *
 * @author Jallais
 */
public class Tomate extends BurgerDecorator {
    public Tomate(Burger burger, double price) {
        super(burger, price);
    }
    
    @Override
    public String getIngredientList() {
        return this.burger.getIngredientList() + "tomate, ";
    }
    
    @Override
    public double getPrice() {
        return this.burger.getPrice() + this.price;
    }
}
