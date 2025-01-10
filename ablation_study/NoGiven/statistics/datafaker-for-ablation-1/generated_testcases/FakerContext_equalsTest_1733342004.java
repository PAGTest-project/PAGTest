
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class FakerContext_equalsTest {

    @Test
    void testEquals_SameInstance() {
        FakerContext context = new FakerContext(Locale.US, new RandomService());
        assertTrue(context.equals(context));
    }

    @Test
    void testEquals_NullObject() {
        FakerContext context = new FakerContext(Locale.US, new RandomService());
        assertFalse(context.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        FakerContext context = new FakerContext(Locale.US, new RandomService());
        assertFalse(context.equals(new Object()));
    }

    @Test
    void testEquals_DifferentLocale() {
        FakerContext context1 = new FakerContext(Locale.US, new RandomService());
        FakerContext context2 = new FakerContext(Locale.FRANCE, new RandomService());
        assertFalse(context1.equals(context2));
    }

    @Test
    void testEquals_DifferentRandomService() {
        FakerContext context1 = new FakerContext(Locale.US, new RandomService());
        FakerContext context2 = new FakerContext(Locale.US, new RandomService());
        context2.setRandomService(new RandomService());
        assertFalse(context1.equals(context2));
    }

    @Test
    void testEquals_SameLocaleAndRandomService() {
        FakerContext context1 = new FakerContext(Locale.US, new RandomService());
        FakerContext context2 = new FakerContext(Locale.US, new RandomService());
        assertTrue(context1.equals(context2));
    }
}
