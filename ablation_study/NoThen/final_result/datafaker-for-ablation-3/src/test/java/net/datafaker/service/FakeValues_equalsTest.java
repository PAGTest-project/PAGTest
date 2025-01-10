
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
        fakeValues1 = FakeValues.of(FakeValuesContext.of(new Locale("en")));
        fakeValues2 = FakeValues.of(FakeValuesContext.of(new Locale("en")));
        fakeValues3 = FakeValues.of(FakeValuesContext.of(new Locale("uk")));
    }

    @Test
    public void testEqualsSameObject() {
        assertEquals(fakeValues1, fakeValues1);
    }

    @Test
    public void testEqualsDifferentType() {
        assertNotEquals(fakeValues1, new Object());
    }

    @Test
    public void testEqualsSameContext() {
        assertEquals(fakeValues1, fakeValues2);
    }

    @Test
    public void testEqualsDifferentContext() {
        assertNotEquals(fakeValues1, fakeValues3);
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
