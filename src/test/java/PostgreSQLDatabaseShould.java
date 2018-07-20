import org.junit.*;
import org.testcontainers.containers.PostgreSQLContainer;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PostgreSQLDatabaseShould {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:9.6.2")
            .withDatabaseName("shopping-db")
            .withUsername("username")
            .withPassword("password");

    @Before
    public void setup() {
        postgreSQLContainer.start();
    }

    @After
    public void teardown() {
        postgreSQLContainer.stop();
    }

    @Test
    public void get_all_products() {
        PostgreSQLDatabase postgreSQLDatabase = new PostgreSQLDatabase(postgreSQLContainer);

        postgreSQLDatabase.add(new Product("Breaking Bad", new Money(new BigDecimal(9))));

        List<Product> allProducts = postgreSQLDatabase.getAllProducts();

        assertThat(allProducts.size(), is(1));
    }
}
