
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Finance_usRoutingNumberTest {
    private Finance finance;

    @BeforeEach
    public void setUp() {
        finance = new Finance(new BaseProviders());
    }

    @Test
    public void testUsRoutingNumber() {
        String routingNumber = finance.usRoutingNumber();
        assertTrue(routingNumber.matches("\\d{9}"));
    }
}
