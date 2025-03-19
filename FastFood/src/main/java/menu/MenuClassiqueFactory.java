/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import product.burger.HamburgerFactory;
import product.dessert.DessertFactory;
import product.drink.DrinkFactory;

public class MenuClassiqueFactory extends MenuFactory {
    @Override
    public Menu getMenu() {
        Menu menu = new Menu("Menu classique", 10.0);
        
        menu.addProduct(new HamburgerFactory().getBurger());
        menu.addProduct(new DrinkFactory().getCoca());
        menu.addProduct(new DessertFactory().getFondant());
                
        return menu;
    }
}
