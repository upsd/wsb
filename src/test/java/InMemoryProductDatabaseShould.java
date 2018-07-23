import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InMemoryProductDatabaseShould {

    private InMemoryProductDatabase inMemoryProductDatabase;

    @Before
    public void setup() {
        inMemoryProductDatabase = new InMemoryProductDatabase();
    }

    @Test
    public void have_no_products_initially() {
        assertThat(inMemoryProductDatabase.getAllProducts().size(), is(0));
    }
    
    @Test
    public void add_products() {
        inMemoryProductDatabase.add(null);

        assertThat(inMemoryProductDatabase.getAllProducts().size(), is(1));
    }
}
