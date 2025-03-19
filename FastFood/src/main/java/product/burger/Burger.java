/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.burger;

import product.Product;

/**
 *
 * @author Jallais
 */
public class Burger extends Product {
    public Burger(String name, double price) {
        super(name, price, "/some/path/");
    }
    
    @Override
    public String getDescription() {
        String ingredientList = this.getIngredientList();
        if (!ingredientList.isEmpty()) {
            ingredientList = ingredientList.substring(0, ingredientList.length()-2);  // Pour enlever ', '
        }
        return this.getName()+"(" + ingredientList + ")";
    }
    
    public String getIngredientList() {
        return "";
    }
}
