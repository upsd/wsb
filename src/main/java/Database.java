import java.util.List;

public interface Database {
    List<Product> getAllProducts();
    void add(Product product);
}
