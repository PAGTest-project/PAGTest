
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Commerce_productNameTest {
    private Commerce commerce;

    @BeforeEach
    public void setUp() {
        BaseProviders baseProviders = new BaseProviders() {
            @Override
            public String resolve(String key) {
                return "test";
            }
        };
        commerce = new Commerce(baseProviders);
    }

    @Test
    void testProductName() {
        String productName = commerce.productName();
        assertTrue(productName.contains(" "));
        assertTrue(productName.split(" ").length == 3);
    }
}
