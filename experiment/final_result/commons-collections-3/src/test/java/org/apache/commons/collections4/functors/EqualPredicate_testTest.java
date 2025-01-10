
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EqualPredicate_testTest {

    private EqualPredicate<String> equalPredicate;
    private Equator<String> equator;

    @BeforeEach
    public void setUp() {
        equator = mock(Equator.class);
    }

    @Test
    public void testWithEquator() {
        equalPredicate = new EqualPredicate<>("test", equator);
        when(equator.equate("test", "test")).thenReturn(true);
        when(equator.equate("test", "notTest")).thenReturn(false);

        assertTrue(equalPredicate.test("test"));
        assertFalse(equalPredicate.test("notTest"));
    }

    @Test
    public void testWithoutEquator() {
        equalPredicate = new EqualPredicate<>("test");

        assertTrue(equalPredicate.test("test"));
        assertFalse(equalPredicate.test("notTest"));
    }
}
