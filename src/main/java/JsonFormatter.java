import java.util.List;
import java.util.stream.Collectors;

public class JsonFormatter implements ProductFormatter {

    @Override
    public String format(List<Product> allProducts) {
        String productsAsJson = allProducts
                .stream()
                .map(product -> String.format("{ \"title\": \"%s\", \"price\": %.2f }", product.title(), product.price()))
                .collect(Collectors.joining(","));
        return "{ \"products\": [" + productsAsJson + "] }";
    }
}
