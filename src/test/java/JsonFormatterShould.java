import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonFormatterShould {

    @Test
    public void format_collection_of_products_into_json() {
        JsonFormatter formatter = new JsonFormatter();
        List<Product> products = Arrays.asList(new Product("A product", new Money(new BigDecimal(23))));

        String json = formatter.format(products);

        assertThat(json, is("{ \"products\": [{ \"title\": \"A product\", \"price\": 23.00 }] }"));
    }
}
