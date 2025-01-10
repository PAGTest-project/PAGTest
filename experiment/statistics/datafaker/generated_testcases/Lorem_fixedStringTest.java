
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lorem_fixedStringTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public RandomService random() {
                return new RandomService();
            }
        };
        lorem = new Lorem(faker);
    }

    @Test
    public void testFixedStringWithZeroLength() {
        String result = lorem.fixedString(0);
        assertEquals("", result);
    }

    @Test
    public void testFixedStringWithPositiveLength() {
        String result = lorem.fixedString(10);
        assertEquals(10, result.length());
    }
}
