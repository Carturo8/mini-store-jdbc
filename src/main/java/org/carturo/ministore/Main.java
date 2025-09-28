package org.carturo.ministore;

import org.carturo.ministore.controller.ProductController;
import org.carturo.ministore.repository.ProductRepositoryImpl;
import org.carturo.ministore.repository.Repository;
import org.carturo.ministore.entity.Product;
import org.carturo.ministore.service.InventoryService;
import org.carturo.ministore.service.InventoryServiceImpl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Repository<Product> repository = new ProductRepositoryImpl();
        InventoryService service = new InventoryServiceImpl(repository);
        ProductController productController = new ProductController(service);

        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                    === 🛒 Mini Store Menu ===

                    1. ➕ Add Product
                    2. 📋 List Inventory
                    3. 💲 Update Price
                    4. 📦 Update Stock
                    5. ❌ Delete Product
                    6. 🔎 Find Product by Name
                    7. 🚪 Exit

                    Choose an option:
                    """, "Mini Store", JOptionPane.QUESTION_MESSAGE);

            if (option == null) {
                int confirmExit = JOptionPane.showConfirmDialog(
                        null,
                        "Do you really want to exit?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );
                if (confirmExit == JOptionPane.YES_OPTION) {
                    break;
                } else {
                    continue;
                }
            }

            switch (option.trim()) {
                case "1" -> productController.createProduct();
                case "2" -> productController.listAllProducts();
                case "3" -> productController.updateProductPrice();
                case "4" -> productController.updateProductStock();
                case "5" -> productController.deleteProduct();
                case "6" -> productController.findProductByName();
                case "7" -> {
                    int confirmExit = JOptionPane.showConfirmDialog(
                            null,
                            "Do you want to exit the application?",
                            "Exit Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (confirmExit == JOptionPane.YES_OPTION) {
                        productController.showOperationsSummary();
                        return;
                    }
                }
                default -> JOptionPane.showMessageDialog(
                        null,
                        "⚠️ Invalid option, please try again.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } while (true);
    }
}