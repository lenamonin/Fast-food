/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

import general.Purchasable;
import general.Describable;
import product.Product;
import menu.Menu;

import java.util.Random;
import java.util.ArrayList;

public class Order implements Purchasable, Describable {
    private ArrayList<Product> products;
    private ArrayList<Menu> menus;
    private int id;
    private String state;
    
    public Order(ArrayList<Product> products, ArrayList<Menu> menus) {
        this.products = products;
        this.menus = menus;
        Random random = new Random();
        this.id = random.nextInt(0, 999);
        this.state = "En cours de préparation";
    }
    
    public Order() {
        this(new ArrayList<>(), new ArrayList<>());
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getName() {
        return String.format("Commande n°%d", this.id);
    }
    
    public Order copy() {
        return new Order((ArrayList<Product>)this.products.clone(), (ArrayList<Menu>)this.menus.clone());
    }
    
    public void clearOrder() {
        this.menus.clear();
        this.products.clear();
    }
    
    public void removeProduct(Product p) {
        for (int i=0; i<this.products.size(); i++) {
            if (this.products.get(i).equals(p)) {
                this.products.remove(i);
                return;
            }
        }
    }
    
    public void removeMenu(Menu m) {
        for (int i=0; i<this.menus.size(); i++) {
            if (this.menus.get(i).equals(m)) {
                this.menus.remove(i);
                return;
            }
        }
    }
    
    public void addProduct(Product p) {
        this.products.add(p);
    }
    
    public void addMenu(Menu m) {
        this.menus.add(m);
    }
    
    public ArrayList<Product> getProducts() {
        return this.products;
    }
    
    public ArrayList<Menu> getMenus() {
        return this.menus;
    }
    
    @Override
    public String getDescription() {
        String chaine = "Les produits : \n";
        
        for (Product p: this.products) {
            chaine += p.getDescription() + "\n";
        }
        
        chaine += "\nLes menus : \n";
        
        for (Menu m: this.menus) {
            chaine += m.getDescription() + "\n";
        }
        
        return chaine;
    }
    
    @Override
    public double getPrice() {
        double price = 0;
        
        for (Product p: this.products) {
            price += p.getPrice();
        }
        
        for (Menu m: this.menus) {
            price += m.getPrice();
        }
        
        return price;
    }
}
