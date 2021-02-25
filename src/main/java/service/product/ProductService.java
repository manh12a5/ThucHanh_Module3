package service.product;

import model.Product;
import service.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {

    //Lấy Connection
    Connection connection = SingletonConnection.getConnection();

    //Danh sách phương thức
    @Override
    public List<Product> findAll() {
        String showAllProduct = "select * from product";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(showAllProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("nameProduct");
                int price = resultSet.getInt("price");
                int amount = resultSet.getInt("amountProduct");
                String color = resultSet.getString("colorProduct");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                products.add(new Product(id, name, price, amount, color, image, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void insert(Product product) {
        String insertSQL = "insert into product (nameProduct, price, amountProduct, colorProduct, image, description, category_id) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getCategory());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findById(int id) {
        String showProduct = "select * from product where product_id = ?";
        Product products = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(showProduct);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("nameProduct");
                int price = resultSet.getInt("price");
                int amount = resultSet.getInt("amountProduct");
                String color = resultSet.getString("colorProduct");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                products = new Product(id, name, price, amount, color, image, description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void edit(Product product) {
        String editSQL = "update product set nameProduct = ?, price = ?, amountProduct = ?, colorProduct = ?, image = ?, description = ?, categoty_id = ? where product_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(editSQL);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getAmount());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getImage());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setInt(7, product.getCategory());
            preparedStatement.setInt(8, product.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String deleteSQL = "delete from product where product_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findByName(String name) {
        String searchName = "select * from product where nameProduct like ?";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(searchName);
            name = name + "%";
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String nameProduct = resultSet.getString("nameProduct");
                int price = resultSet.getInt("price");
                int amount = resultSet.getInt("amountProduct");
                String color = resultSet.getString("colorProduct");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                products.add(new Product(id, nameProduct, price, amount, color, image, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}

