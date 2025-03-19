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
public abstract class BurgerFactory {
    
    protected double price;
    
    public BurgerFactory(double price) {
        this.setPrice(price);
    }
    
    private void setPrice(double price) {
        this.price = price;
    }
    
    public abstract Burger getBurger();
    
    public static Burger addCheddar(Burger burger) {
        return new Cheddar(burger, 0.25);
    }
    
    public static Product addCheddar(Product burger) {
        return (Product)BurgerFactory.addCheddar((Burger)burger);
    }
    
    public static Burger addSteack(Burger burger) {
        return new Steack(burger, 0.5);
    }
    
    public static Product addSteack(Product burger) {
        return (Product)BurgerFactory.addSteack((Burger)burger);
    }
    
    public static Burger addOignon(Burger burger) {
        return new Oignon(burger, 0.25);
    }
    
    public static Product addOignon(Product burger) {
        return (Product)BurgerFactory.addOignon((Burger)burger);
    }
    
    public static Burger addTomate(Burger burger) {
        return new Tomate(burger, 0.25);
    }
    
    public static Product addTomate(Product burger) {
        return (Product)BurgerFactory.addTomate((Burger)burger);
    }
    
    public static Burger addKetchup(Burger burger) {
        return new Ketchup(burger, 0.25);
    }
    
    public static Product addKetchup(Product burger) {
        return (Product)BurgerFactory.addKetchup((Burger)burger);
    }
}
