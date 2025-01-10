
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Finance_usRoutingNumberTest {
    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation
            }

            @Override
            public RandomService random() {
                return new RandomService() {
                    @Override
                    public int nextInt(int n) {
                        return 0; // Dummy implementation
                    }
                };
            }
        });
    }

    @Test
    public void testUsRoutingNumber() {
        String routingNumber = finance.usRoutingNumber();
        assertTrue(routingNumber.matches("\\d{9}"));
    }
}
