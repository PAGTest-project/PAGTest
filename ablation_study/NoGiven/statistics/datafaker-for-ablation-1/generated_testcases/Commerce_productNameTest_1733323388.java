
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Commerce_productNameTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        commerce = new Commerce(new BaseProviders());
    }

    @Test
    void testProductName() {
        String productName = commerce.productName();
        String[] parts = productName.split(" ");

        assertEquals(3, parts.length);
        assertEquals(commerce.resolve("commerce.product_name.adjective"), parts[0]);
        assertEquals(commerce.resolve("commerce.product_name.material"), parts[1]);
        assertEquals(commerce.resolve("commerce.product_name.product"), parts[2]);
    }
}
