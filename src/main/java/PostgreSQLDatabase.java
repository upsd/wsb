import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLDatabase implements Database {

    private final PostgreSQLContainer container;
    private Connection connection;

    public PostgreSQLDatabase(PostgreSQLContainer postgreSQLContainer) {
        this.container = postgreSQLContainer;
        this.connection = establishConnection(this.connection);

        establishConnection(this.connection);
        createTable(this.connection);
    }

    @Override
    public List<Product> getAllProducts() {
        return getProducts(this.connection);
    }

    @Override
    public void add(Product product) {
        addProduct(product);
    }

    private void addProduct(Product product) {
        try {
            PreparedStatement insertNewProduct = connection.prepareStatement("INSERT INTO products (title, price) VALUES (?, ?)");
            insertNewProduct.setString(1, product.title());
            insertNewProduct.setDouble(2, product.price());
            insertNewProduct.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Product> getProducts(Connection connection) {
        List<Product> products = new ArrayList<>();

        try {
            ResultSet resultSet = connection
                    .prepareStatement("SELECT * FROM products")
                    .executeQuery();

            while(resultSet.next()) {
                String name = resultSet.getString("title");
                BigDecimal price = resultSet.getBigDecimal("price");

                products.add(new Product(name, new Money(price)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private Connection establishConnection(Connection connection) {
        try {
            connection = DriverManager.getConnection(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void createTable(Connection connection) {
        try {
            connection.prepareStatement("CREATE TABLE products (\n" +
                    "    title        varchar(30),\n" +
                    "    price       decimal" +
                    ");").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
