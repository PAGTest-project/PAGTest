
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

class FakeValues_equalsTest {

    @Test
    void testEquals_SameInstance() {
        FakeValuesContext context = FakeValuesContext.of(Locale.ENGLISH);
        FakeValues fakeValues = FakeValues.of(context);
        assertTrue(fakeValues.equals(fakeValues));
    }

    @Test
    void testEquals_DifferentInstanceSameContext() {
        FakeValuesContext context = FakeValuesContext.of(Locale.ENGLISH);
        FakeValues fakeValues1 = FakeValues.of(context);
        FakeValues fakeValues2 = FakeValues.of(context);
        assertTrue(fakeValues1.equals(fakeValues2));
    }

    @Test
    void testEquals_DifferentContext() {
        FakeValuesContext context1 = FakeValuesContext.of(Locale.ENGLISH);
        FakeValuesContext context2 = FakeValuesContext.of(Locale.FRENCH);
        FakeValues fakeValues1 = FakeValues.of(context1);
        FakeValues fakeValues2 = FakeValues.of(context2);
        assertFalse(fakeValues1.equals(fakeValues2));
    }

    @Test
    void testEquals_DifferentType() {
        FakeValuesContext context = FakeValuesContext.of(Locale.ENGLISH);
        FakeValues fakeValues = FakeValues.of(context);
        assertFalse(fakeValues.equals("Not a FakeValues instance"));
    }
}
