/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import product.burger.VeggieFactory;
import product.dessert.DessertFactory;
import product.drink.DrinkFactory;

/**
 *
 * @author Jallais
 */
public class MenuVegetarienFactory extends MenuFactory {
    
    @Override
    public Menu getMenu() {
        Menu menu = new Menu("Menu végétarien", 11.0);
        
        menu.addProduct(new VeggieFactory().getBurger());
        menu.addProduct(new DrinkFactory().getJus());
        menu.addProduct(new DessertFactory().getTarte());
                
        return menu;
    }
}
