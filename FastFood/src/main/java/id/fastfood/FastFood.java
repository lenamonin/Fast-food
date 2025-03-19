package id.fastfood;

import BorneUI.acceuilFrame;
import KitchenUI.MainKitchenUI;
import order.*;

import java.util.ArrayList;

public class FastFood {

       public static void main(String[] args) {
        Order order = new Order();
        OrderManager orderManager = new OrderManager(order);
        OrderListManager orderListManager = new OrderListManager(new ArrayList<>());
        acceuilFrame acceuilFrame= new acceuilFrame(orderManager, orderListManager);
        MainKitchenUI mainKitchenUI = new MainKitchenUI(orderListManager);
        mainKitchenUI.setVisible(true);
        acceuilFrame.setVisible(true);
    }
}