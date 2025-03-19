/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

import java.util.ArrayList;

public class OrderListManager {
    private ArrayList<Order> orderList;
    private ArrayList<OrderListView> views;
    
    public OrderListManager(ArrayList<Order> orderList) {
        this.orderList = orderList;
        this.views = new ArrayList<>();
    }
    
    public void addView(OrderListView view) {
        this.views.add(view);
    }
    
    public void addOrder(Order order) {
        this.orderList.add(order);
        this.notifyAllViews();
    }
    
    public void removeOrder(Order order) {
        for (Order o: this.orderList) {
            if (o.equals(order)) {
                this.orderList.remove(o);
                this.notifyAllViews();
                return;
            }
        }
    }
    
    private void notifyAllViews() {
        for (OrderListView view: this.views) {
            view.update(this.orderList);
        }
    }   

    public ArrayList<Order> getOrderList() {
        return this.orderList;
    }
}
