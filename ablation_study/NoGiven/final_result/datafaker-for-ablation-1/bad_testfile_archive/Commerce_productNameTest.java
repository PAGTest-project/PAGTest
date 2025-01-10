
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Commerce_productNameTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "mock";
            }

            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }
        };
        commerce = new Commerce(baseProviders);
    }

    @Test
    void testProductName() {
        String productName = commerce.productName();
        String[] parts = productName.split(" ");

        assertEquals(3, parts.length);
        assertEquals("mock", parts[0]);
        assertEquals("mock", parts[1]);
        assertEquals("mock", parts[2]);
    }
}
