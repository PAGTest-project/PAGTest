
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EqualPredicate_testTest {

    private EqualPredicate<String> equalPredicate;
    private EqualPredicate<String> equalPredicateWithEquator;
    private Equator<String> mockEquator;

    @BeforeEach
    public void setUp() {
        equalPredicate = new EqualPredicate<>("testValue");
        mockEquator = new Equator<String>() {
            @Override
            public boolean equate(String o1, String o2) {
                return o1.equalsIgnoreCase(o2);
            }

            @Override
            public int hash(String o) {
                return o.toLowerCase().hashCode();
            }
        };
        equalPredicateWithEquator = new EqualPredicate<>("testValue", mockEquator);
    }

    @Test
    public void testTestWithEquator() {
        assertTrue(equalPredicateWithEquator.test("TESTVALUE"));
        assertFalse(equalPredicateWithEquator.test("differentValue"));
    }

    @Test
    public void testTestWithoutEquator() {
        assertTrue(equalPredicate.test("testValue"));
        assertFalse(equalPredicate.test("differentValue"));
    }

    @Test
    public void testNullArgumentEqualsNullPredicate() {
        Predicate<Object> nullPredicate = NullPredicate.nullPredicate();
        assertSame(nullPredicate, EqualPredicate.equalPredicate(null));
    }
}
