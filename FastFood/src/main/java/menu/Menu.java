/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package menu;

import general.Describable;
import general.Purchasable;
import product.Product;
import java.util.ArrayList;

public class Menu implements Purchasable, Describable {
    private ArrayList<Product> products;
    private String name;
    private double price;
    
    public Menu(String name, double price) {
        this.name = name;
        this.price = price;
        this.products = new ArrayList<Product>();
    }
    
    public void addProduct(Product product) {
        this.products.add(product);
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public double getPrice() {
        return this.price;
    }
    
    @Override
    public String getDescription() {
        String chaine = this.name + " (" + this.price + "€)\n";
        for (Product p: this.products) {
            chaine += "    -" + p.getDescription() + "\n";
        }
        
        return chaine;
    }
     public ArrayList<Product> getProducts() {
        return this.products;
    }
}
