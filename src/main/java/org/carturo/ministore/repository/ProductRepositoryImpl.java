package org.carturo.ministore.repository;

import org.carturo.ministore.database.ConnectionFactory;
import org.carturo.ministore.entity.Product;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements Repository<Product> {

    @Override
    public Product create(Product product) {
        Product objProduct = (Product) product;
        String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";

        try(Connection objConnection = ConnectionFactory.openConnection();
            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.execute();

            ResultSet objRest = objPrepare.getGeneratedKeys();
            while (objRest.next()){
                objProduct.setId(objRest.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "✅ Product added successfully");

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "⚠️ Product name must be unique");
            } else {
                JOptionPane.showMessageDialog(null, "❌ Error adding product: " + e.getMessage());
            }
        }

        return objProduct;
    }

    @Override
    public Product findById(int id) {
        Product objProduct = null;
        String sql = "SELECT * FROM products WHERE id = ?";

        try (Connection objConnection = ConnectionFactory.openConnection();
            PreparedStatement objPrepare = objConnection.prepareStatement(sql))
        {
            objPrepare.setInt(1, id);

            ResultSet objResult = objPrepare.executeQuery();
            if (objResult.next()){
                objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
            }

        } catch (SQLException error){
            JOptionPane.showMessageDialog(null, "❌ Error finding product: " + error.getMessage());
        }

        return objProduct;
    }

    @Override
    public List<Product> findAll() {
        List<Product> listProducts = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection objConnection = ConnectionFactory.openConnection();
            PreparedStatement objPrepare = objConnection.prepareStatement(sql))
        {
            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()){
                Product objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
                listProducts.add(objProduct);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "❌ Error listing products: " + error.getMessage());
        }

        return listProducts;
    }

    @Override
    public boolean update(Product product) {
        Product objProduct = (Product) product;
        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE id = ?";
        boolean isUpdated = false;

        try (Connection objConnection = ConnectionFactory.openConnection();
            PreparedStatement objPrepare = objConnection.prepareStatement(sql))
        {
            objPrepare.setString(1, objProduct.getName());
            objPrepare.setDouble(2, objProduct.getPrice());
            objPrepare.setInt(3, objProduct.getStock());
            objPrepare.setInt(4, objProduct.getId());

            int result = objPrepare.executeUpdate();
            if (result > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "✅ Product updated successfully");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "❌ Error updating product: " + error.getMessage());
        }

        return isUpdated;
    }

    @Override
    public boolean delete(Product product) {
        Product objProduct = (Product) product;
        String sql = "DELETE FROM products WHERE id = ?";
        boolean isDeleted = false;

        try (Connection objConnection = ConnectionFactory.openConnection();
            PreparedStatement objPrepare = objConnection.prepareStatement(sql))
        {
            objPrepare.setInt(1, objProduct.getId());

            int result = objPrepare.executeUpdate();
            if (result > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "✅ Product deleted successfully");
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "❌ Error deleting product: " + error.getMessage());
        }

        return isDeleted;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE LOWER(name) LIKE ?";

        try (Connection objConnection = ConnectionFactory.openConnection();
             PreparedStatement objPrepare = objConnection.prepareStatement(sql))
        {
            objPrepare.setString(1, "%" + name.toLowerCase() + "%");

            ResultSet objResult = objPrepare.executeQuery();
            while (objResult.next()) {
                Product objProduct = new Product();
                objProduct.setId(objResult.getInt("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setPrice(objResult.getDouble("price"));
                objProduct.setStock(objResult.getInt("stock"));
                products.add(objProduct);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "❌ Error searching products: " + error.getMessage());
        }

        return products;
    }
}