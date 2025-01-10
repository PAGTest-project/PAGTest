
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Commerce_productNameTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "test";
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation not needed for this test
            }
        };
        commerce = new Commerce(faker);
    }

    @Test
    public void testProductName() {
        String productName = commerce.productName();
        String[] parts = productName.split(" ");

        assertEquals(3, parts.length);
        assertEquals("test", parts[0]);
        assertEquals("test", parts[1]);
        assertEquals("test", parts[2]);
    }
}
