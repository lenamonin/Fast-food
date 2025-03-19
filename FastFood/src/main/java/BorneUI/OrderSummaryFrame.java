/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BorneUI;

import static id.fastfood.FastFood.main;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import static jdk.jshell.execution.RemoteExecutionControl.main;
import menu.Menu;
import order.Order;
import order.OrderListManager;
import order.OrderManager;
import product.Product;

/**
 *
 * @author HP
 */
public class OrderSummaryFrame extends javax.swing.JFrame {

    /**
     * Creates new form OrderSummaryFrame
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   private MainBorneUI main; // Référence à MainBorneUI
   public OrderSummaryFrame(MainBorneUI main,Order order) {
       this.main = main;
        // Configurer la fenêtre
        this.setTitle("Résumé de votre commande");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrer la fenêtre
        initComponents(order);
    }

    private void initComponents(Order order) {
        this.setLayout(new BorderLayout());

        // Ajouter un label pour le titre
        JLabel titleLabel = new JLabel("Merci pour votre commande", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(titleLabel, BorderLayout.NORTH);

        // Créer un tableau pour afficher les produits et prix
        String[] columnNames = {"Produit(s)", "Prix (€)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Ajouter les produits individuels au tableau
        List<Product> products = order.getProducts();
        for (Product product : products) {
            String productName = product.getDescription();
            double productPrice = product.getPrice();
            tableModel.addRow(new Object[]{productName, String.format("%.2f", productPrice)});
        }

        // Ajouter les menus au tableau
        ArrayList<Menu> menus = order.getMenus();
        for (Menu menu : menus) {
            String menuName = menu.getName();
            double menuPrice = menu.getPrice();
            tableModel.addRow(new Object[]{menuName, String.format("%.2f", menuPrice)});
            
            // Ajouter les détails des éléments du menu
            for (Product productInMenu : menu.getProducts()) {
                String productName = "  - " + productInMenu.getDescription(); // Indentation pour différencier les éléments du menu
                double productPrice = productInMenu.getPrice();
                tableModel.addRow(new Object[]{productName, String.format("%.2f", productPrice)});
            }
        }

        // Ajouter une ligne pour le total
        tableModel.addRow(new Object[]{"Total", String.format("%.2f", order.getPrice())});

        JTable orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);
        this.add(scrollPane, BorderLayout.CENTER);
        orderTable.setEnabled(false);
        JButton closeButton = new JButton("Fermer");
        closeButton.addActionListener(e -> closeAndReturnToAccueil());
        this.add(closeButton, BorderLayout.SOUTH);
    }
 private void closeAndReturnToAccueil() {
    this.dispose(); // Fermer la fenêtre actuelle
    this.main.showChoiceFrame();
    this.main.setVisible(true);

}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
