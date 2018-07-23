import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLDatabase implements Database {

    private Connection connection;

    public PostgreSQLDatabase(String host, int port, String dbName, String username, String password) {
        String url = getJdbcUrl(host, port, dbName);

        establishConnection(url, username, password);

        createTable();
    }

    @Override
    public List<Product> getAllProducts() {
        return getProducts(this.connection);
    }

    @Override
    public void add(Product product) {
        addProduct(product);
    }

    private String getJdbcUrl(String host, int port, String dbName) {
        return String.format("jdbc:postgresql://%s:%d/%s", host, port, dbName);
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

    private void establishConnection(String jdbcUrl, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
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
