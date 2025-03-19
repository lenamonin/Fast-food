/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.drink;
import product.Product;

public class Drink extends Product {
    
    public Drink(String name, double price) {
        super(name, price, "drink");
    }
    
    @Override
    public String getDescription() {
        return this.getName();
    }
}
