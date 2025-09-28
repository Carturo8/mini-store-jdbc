package org.carturo.ministore.service;

import org.carturo.ministore.entity.Product;

import java.util.List;

public interface InventoryService {
    Product addProduct(String name, double price, int stock);
    boolean updatePrice(int id, double newPrice);
    boolean updateStock(int id, int newStock);
    boolean deleteProduct(int id);
    List<Product> listAllProducts();
    List<Product> findProductByName(String text);
}