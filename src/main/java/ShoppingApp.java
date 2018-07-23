import java.math.BigDecimal;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class ShoppingApp {

    public static void main(String[] args) {

        Database database = null;

        if (System.getenv("SHOPPING_ENV").equals("test")) {
            database = new InMemoryProductDatabase();
            database.add(new Product("Breaking Bad", new Money(new BigDecimal(45))));
            database.add(new Product("Fixing Good", new Money(new BigDecimal(24))));
        }

        ProductController productController = new ProductController(database, new JsonFormatter());

        staticFileLocation("/public");
        get("/", (req, res) -> null);

        get("/products", (req, res) -> {
            res.type("application/json");
            return productController.get();
        });
    }
}
