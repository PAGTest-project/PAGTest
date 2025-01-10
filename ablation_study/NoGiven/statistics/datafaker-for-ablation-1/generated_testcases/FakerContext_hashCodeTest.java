
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Locale;

public class FakerContext_hashCodeTest {

    @Test
    public void testHashCodeWithNonNulls() {
        RandomService randomService = new RandomService();
        FakerContext context = new FakerContext(Locale.US, randomService);
        int expectedHashCode = context.getSingletonLocale().hashCode() * 31 + randomService.hashCode();
        assertEquals(expectedHashCode, context.hashCode());
    }

    @Test
    public void testHashCodeWithNulls() {
        FakerContext context = new FakerContext(Locale.US, null);
        int expectedHashCode = context.getSingletonLocale().hashCode() * 31;
        assertEquals(expectedHashCode, context.hashCode());
    }
}
