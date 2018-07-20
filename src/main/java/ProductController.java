public class ProductController {

    private Database database;

    public ProductController(Database database) {

        this.database = database;
    }

    public String get(ProductFormatter formatter) {
        return formatter.format(database.getAllProducts());
    }
}
