package org.carturo.ministore.service;

import org.carturo.ministore.entity.Product;
import org.carturo.ministore.repository.Repository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    private final Repository<Product> repository;

    public InventoryServiceImpl(Repository<Product> repository) {
        this.repository = repository;
    }

    @Override
    public Product addProduct(String name, double price, int stock) {
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠ Product name cannot be empty");
            return null;
        }
        if (price <= 0) {
            JOptionPane.showMessageDialog(null, "⚠ Price must be greater than 0");
            return null;
        }
        if (stock < 0) {
            JOptionPane.showMessageDialog(null, "⚠ Stock cannot be negative");
            return null;
        }

        Product product = new Product();
        product.setName(name.trim());
        product.setPrice(price);
        product.setStock(stock);

        return repository.create(product);
    }

    @Override
    public boolean updatePrice(int id, double newPrice) {
        if (newPrice <= 0) {
            JOptionPane.showMessageDialog(null, "⚠️ Price must be greater than 0");
            return false;
        }

        Product product = repository.findById(id);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "❌ Product not found with id: " + id);
            return false;
        }

        product.setPrice(newPrice);

        return repository.update(product);
    }

    @Override
    public boolean updateStock(int id, int newStock) {
        if (newStock < 0) {
            JOptionPane.showMessageDialog(null, "⚠️ Stock cannot be negative");
            return false;
        }

        Product product = repository.findById(id);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "❌ Product not found with id: " + id);
            return false;
        }

        product.setStock(newStock);

        return repository.update(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        Product product = repository.findById(id);
        if (product == null) {
            JOptionPane.showMessageDialog(null, "❌ Product not found with id: " + id);
            return false;
        }

        return repository.delete(product);
    }

    @Override
    public List<Product> listAllProducts() {
        return repository.findAll();
    }

    @Override
    public List<Product> findProductByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "⚠️ Product name cannot be empty for search");
            return new ArrayList<>();
        }

        return repository.findByName(name.trim());
    }
}