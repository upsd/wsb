import java.util.ArrayList;
import java.util.List;

public class InMemoryProductDatabase implements Database {

    private final List<Product> products;

    public InMemoryProductDatabase() {
        this.products = new ArrayList<>();
    }

    @Override
    public List<Product> getAllProducts() {
        return this.products;
    }

    @Override
    public void add(Product product) {
        this.products.add(product);
    }
}
