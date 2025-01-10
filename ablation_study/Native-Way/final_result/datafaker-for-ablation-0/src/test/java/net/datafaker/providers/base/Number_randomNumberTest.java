
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Number_randomNumberTest {
    private Number number;
    private BaseProviders faker;

    @BeforeEach
    public void setUp() {
        faker = new BaseFaker();
        number = new Number(faker);
    }

    @Test
    public void testRandomNumberWithZeroDigits() {
        long result = number.randomNumber(0, false);
        assertTrue(result == 0);
    }

    @Test
    public void testRandomNumberWithNegativeDigits() {
        long result = number.randomNumber(-5, false);
        assertTrue(result == 0);
    }

    @Test
    public void testRandomNumberStrictTrue() {
        long result = number.randomNumber(3, true);
        assertTrue(result >= 100 && result < 1000);
    }

    @Test
    public void testRandomNumberStrictFalse() {
        long result = number.randomNumber(3, false);
        assertTrue(result >= 0 && result < 1000);
    }

    @Test
    public void testRandomNumberNonStrict() {
        long result = number.randomNumber(5, false);
        assertTrue(result >= 0 && result < 100000);
    }
}
