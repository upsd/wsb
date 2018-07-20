import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetProductAcceptanceTest {

    @Test
    public void get_all_products_from_db() {
        PostgreSQLDatabase database = mock(PostgreSQLDatabase.class);
        ProductController productController = new ProductController(database);

        when(database.getAllProducts()).thenReturn(Arrays.asList(new Product("Game of Thrones", new Money(new BigDecimal(9)))));

        String products = productController.get(new JsonFormatter());

        assertThat(products, is("{ products: [{ title: \" Game of Thrones \", \" price: 9 \"}]"));
    }
}