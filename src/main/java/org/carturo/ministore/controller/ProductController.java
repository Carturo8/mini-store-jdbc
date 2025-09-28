package org.carturo.ministore.controller;

import org.carturo.ministore.entity.Product;
import org.carturo.ministore.service.InventoryService;

import javax.swing.*;
import java.util.List;

public class ProductController {

    private final InventoryService inventoryService;
    private int operationsAdded = 0;
    private int operationsUpdated = 0;
    private int operationsDeleted = 0;

    public ProductController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void createProduct() {
        try {
            String name = JOptionPane.showInputDialog("Enter the product name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the product price:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter the product stock:"));

            Product product = inventoryService.addProduct(name, price, stock);
            if (product != null) {
                operationsAdded++;
            }

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ Invalid number format",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void listAllProducts() {
        List<Product> products = inventoryService.listAllProducts();
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No products in inventory.",
                    "Inventory",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== Inventory ===\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }

    public void updateProductPrice() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter product id:"));
            double newPrice = Double.parseDouble(JOptionPane.showInputDialog("Enter new price:"));

            if (inventoryService.updatePrice(id, newPrice)) {
                operationsUpdated++;
            }

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ Invalid number format",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void updateProductStock() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter product id:"));
            int newStock = Integer.parseInt(JOptionPane.showInputDialog("Enter new stock:"));

            if (inventoryService.updateStock(id, newStock)) {
                operationsUpdated++;
            }

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ Invalid number format",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void deleteProduct() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter product id:"));

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete product with id " + id + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                if (inventoryService.deleteProduct(id)) {
                    operationsDeleted++;
                }
            }

        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null,
                    "⚠️ Invalid number format",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void findProductByName() {
        String name = JOptionPane.showInputDialog("Enter product name (or part):");

        List<Product> products = inventoryService.findProductByName(name);
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No products found.",
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("=== Results ===\n");
            for (Product product : products) {
                sb.append(product).append("\n");
            }
            JOptionPane.showMessageDialog(null,
                    sb.toString(),
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void showOperationsSummary() {
        String summary = """
                === Operations Summary ===
                Products Added: %d
                Products Updated: %d
                Products Deleted: %d
                """.formatted(operationsAdded, operationsUpdated, operationsDeleted);

        JOptionPane.showMessageDialog(null,
                summary,
                "Summary",
                JOptionPane.INFORMATION_MESSAGE);
    }
}