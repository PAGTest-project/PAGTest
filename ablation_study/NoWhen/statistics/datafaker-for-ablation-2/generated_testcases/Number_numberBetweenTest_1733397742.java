
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Number_numberBetweenTest {

    @Test
    public void testNumberBetween_MinEqualsMax() {
        BaseFaker faker = new BaseFaker();
        int result = faker.numberBetween(5, 5);
        assertEquals(5, result);
    }

    @Test
    public void testNumberBetween_MinLessThanMax() {
        BaseFaker faker = new BaseFaker();
        int result = faker.numberBetween(3, 8);
        assertTrue(result >= 3 && result < 8);
    }

    @Test
    public void testNumberBetween_MinGreaterThanMax() {
        BaseFaker faker = new BaseFaker();
        int result = faker.numberBetween(8, 3);
        assertTrue(result >= 3 && result < 8);
    }
}
