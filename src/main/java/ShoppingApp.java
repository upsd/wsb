import java.math.BigDecimal;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class ShoppingApp {

    public static void main(String[] args) {

        Database database = getDatabase();

        addExampleProducts(database);

        ProductController productController = new ProductController(database, new JsonFormatter());

        staticFileLocation("/public");

        get("/", (req, res) -> null);

        get("/products", (req, res) -> {
            res.type("application/json");
            return productController.get();
        });
    }

    private static void addExampleProducts(Database database) {
        database.add(new Product("Breaking Bad", new Money(new BigDecimal(45))));
        database.add(new Product("Fixing Good", new Money(new BigDecimal(24))));
    }

    private static Database getDatabase() {
        if (System.getenv("SHOPPING_ENV").equals("test")) {
            return new InMemoryProductDatabase();
        }

        return new PostgreSQLDatabase(
                System.getenv("SHOP_DB_HOST"),
                5432,
                System.getenv("SHOP_DB_NAME"),
                System.getenv("SHOP_DB_USER"),
                System.getenv("SHOP_DB_PASS")
        );
    }
}
