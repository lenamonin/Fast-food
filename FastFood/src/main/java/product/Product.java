/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package product;
import general.Describable;
import general.Purchasable;
import java.io.File;

/**
 *
 * @author Jallais
 */
public abstract class Product implements Purchasable, Describable{
    protected String name;  // Le nom du produit
    protected double price;  // Le prix du produit
    protected String image;  // Le chemin du fichier image
    
    public Product(String name, double price, String image) {
        this.setName(name);
        this.setPrice(price);
        this.setImage(image);
    }
    
    protected void setName(String name) {
        this.name = name;
    }
    
    protected void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Le prix du produit ne peut pas être négatif.");
        }
        
        if (price >= 1000) {
            throw new IllegalArgumentException("Le prix du produit ne peut pas dépasser 999.");
        }
        
        this.price = price;
    }
    
    protected void setImage(String image) {
        if (image == null) {  // Si null
            throw new IllegalArgumentException("Le nom de l'image ne peut pas être null.");
        }
        
        if (image.isEmpty()) { // Si chaine vide
            throw new IllegalArgumentException("Le nom ne peut pas être une chaine vide.");
        }
        
        File file = new File(image);
        
//        if (!file.exists()) {
//            throw new IllegalArgumentException("L'image doit être un fichier existant.");
//        }

        this.image = image;
    }
        
    public String getFullDescription() {
        return this.getDescription() + " : " + this.getPrice() + " euros";
    }
    
    public String getName() {return this.name;}
    public String getImage() {return this.image;}
    
    @Override
    public double getPrice() {return this.price;}
}
