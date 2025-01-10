
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(9, routingNumber.length());
    }

    @Test
    public void testUsRoutingNumberCheckDigit() {
        String routingNumber = finance.usRoutingNumber();
        int checkDigit = Character.getNumericValue(routingNumber.charAt(8));
        String base = routingNumber.substring(0, 8);
        int calculatedCheckDigit = calculateCheckDigit(base);
        assertEquals(calculatedCheckDigit, checkDigit);
    }

    private int calculateCheckDigit(String base) {
        int check =
            Character.getNumericValue(base.charAt(0)) * 3
            + Character.getNumericValue(base.charAt(1)) * 7
            + Character.getNumericValue(base.charAt(2))
            + Character.getNumericValue(base.charAt(3)) * 3
            + Character.getNumericValue(base.charAt(4)) * 7
            + Character.getNumericValue(base.charAt(5))
            + Character.getNumericValue(base.charAt(6)) * 3
            + Character.getNumericValue(base.charAt(7)) * 7;
        return Math.abs(check % 10 - 10) % 10;
    }
}
