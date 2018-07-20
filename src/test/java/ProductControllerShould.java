import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductControllerShould {

    @Test
    public void call_database_and_format_into_specified_format() {
        PostgreSQLDatabase database = mock(PostgreSQLDatabase.class);
        JsonFormatter formatter = mock(JsonFormatter.class);
        ProductController productController = new ProductController(database, formatter);

        productController.get();

        verify(database).getAllProducts();
        verify(formatter).format(any());
    }
}
