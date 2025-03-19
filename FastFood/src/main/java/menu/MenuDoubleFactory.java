/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import product.burger.CheeseBurgerFactory;
import product.dessert.DessertFactory;
import product.drink.DrinkFactory;

/**
 *
 * @author Jallais
 */
public class MenuDoubleFactory extends MenuFactory{
    @Override
    public Menu getMenu() {
        Menu menu = new Menu("Menu double", 15.0);
        
        menu.addProduct(new CheeseBurgerFactory().getBurger());
        menu.addProduct(new CheeseBurgerFactory().getBurger());
        menu.addProduct(new DrinkFactory().getCoca());
        menu.addProduct(new DessertFactory().getGlace());
                
        return menu;
    }
}
