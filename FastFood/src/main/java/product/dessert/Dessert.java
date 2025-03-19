/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product.dessert;
import product.Product;


public class Dessert extends Product {
    public Dessert(String name, double price) {
        super(name, price, "test");
    }
    
    @Override
    public String getDescription() {
        return this.getName();
    }
}
