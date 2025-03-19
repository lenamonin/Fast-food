/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;
import product.Product;
import menu.Menu;
import java.util.ArrayList;

public class OrderManager {
    private ArrayList<OrderView> views;
    private Order order;
    
    public void addView(OrderView view) {
        this.views.add(view);
    }
    
    public OrderManager(Order order) {
        this.order = order;
        this.views = new ArrayList<>();
    }
    
    public Order getOrder() {
        return this.order;
    }
    
    public void clearOrder() {
        this.order.clearOrder();
        this.notifyAllViews();
    }
    
    public void addProduct(Product p) {
        this.order.addProduct(p);
        this.notifyAllViews();
    }
    
    public void addMenu(Menu m) {
        this.order.addMenu(m);
        this.notifyAllViews();
    }
    
    public void removeProduct(Product p) {
        this.order.removeProduct(p);
        this.notifyAllViews();
    }
    
    public void removeMenu(Menu m) {
        this.order.removeMenu(m);
        this.notifyAllViews();
    }
    
    private void notifyAllViews() {
        for (OrderView view: this.views) {
            view.update(this.order);
        }
    }
}

