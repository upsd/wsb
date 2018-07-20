public class ProductController {

    private Database database;
    private ProductFormatter formatter;

    public ProductController(Database database, ProductFormatter formatter) {
        this.database = database;
        this.formatter = formatter;
    }

    public String get() {
        return formatter.format(database.getAllProducts());
    }
}
