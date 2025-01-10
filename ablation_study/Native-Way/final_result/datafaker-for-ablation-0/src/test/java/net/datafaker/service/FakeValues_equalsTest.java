
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class FakeValues_equalsTest {

    private FakeValues fakeValues1;
    private FakeValues fakeValues2;
    private FakeValues fakeValues3;

    @BeforeEach
    public void setUp() {
        FakeValuesContext context1 = FakeValuesContext.of(new Locale("en"));
        FakeValuesContext context2 = FakeValuesContext.of(new Locale("en"));
        FakeValuesContext context3 = FakeValuesContext.of(new Locale("fr"));

        fakeValues1 = FakeValues.of(context1);
        fakeValues2 = FakeValues.of(context2);
        fakeValues3 = FakeValues.of(context3);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(fakeValues1.equals(fakeValues1));
    }

    @Test
    public void testEqualsDifferentType() {
        assertFalse(fakeValues1.equals("not a FakeValues object"));
    }

    @Test
    public void testEqualsDifferentContext() {
        assertFalse(fakeValues1.equals(fakeValues3));
    }

    @Test
    public void testEqualsSameContext() {
        assertTrue(fakeValues1.equals(fakeValues2));
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(fakeValues1.hashCode(), fakeValues2.hashCode());
    }

    @Test
    public void testHashCodeDifference() {
        assertNotEquals(fakeValues1.hashCode(), fakeValues3.hashCode());
    }
}
