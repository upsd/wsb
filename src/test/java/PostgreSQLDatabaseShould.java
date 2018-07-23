import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PostgreSQLDatabaseShould {

    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Test
    public void get_all_products() {
        EmbeddedPostgres inMemoryPostgres = pg.getEmbeddedPostgres();
        PostgreSQLDatabase postgreSQLDatabase = new PostgreSQLDatabase(
                "localhost",
                inMemoryPostgres.getPort(),
                "postgres",
                "postgres",
                "postgres"
        );

        postgreSQLDatabase.add(new Product("Breaking Bad", new Money(new BigDecimal(9))));

        List<Product> allProducts = postgreSQLDatabase.getAllProducts();

        assertThat(allProducts.size(), is(1));
    }
}
