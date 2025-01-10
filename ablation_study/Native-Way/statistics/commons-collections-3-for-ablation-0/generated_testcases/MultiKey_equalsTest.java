
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiKey_equalsTest {

    private Integer ONE;
    private Integer TWO;
    private Integer THREE;
    private Integer FOUR;
    private Integer FIVE;

    @BeforeEach
    public void setUp() {
        ONE = 1;
        TWO = 2;
        THREE = 3;
        FOUR = 4;
        FIVE = 5;
    }

    @Test
    public void testEqualsSameInstance() {
        MultiKey<Integer> key = new MultiKey<>(ONE, TWO);
        assertTrue(key.equals(key));
    }

    @Test
    public void testEqualsDifferentType() {
        MultiKey<Integer> key = new MultiKey<>(ONE, TWO);
        assertFalse(key.equals("Not a MultiKey"));
    }

    @Test
    public void testEqualsSameKeys() {
        MultiKey<Integer> key1 = new MultiKey<>(ONE, TWO);
        MultiKey<Integer> key2 = new MultiKey<>(ONE, TWO);
        assertTrue(key1.equals(key2));
    }

    @Test
    public void testEqualsDifferentKeys() {
        MultiKey<Integer> key1 = new MultiKey<>(ONE, TWO);
        MultiKey<Integer> key2 = new MultiKey<>(THREE, FOUR);
        assertFalse(key1.equals(key2));
    }

    @Test
    public void testEqualsNullKeys() {
        MultiKey<Integer> key1 = new MultiKey<>(null, null);
        MultiKey<Integer> key2 = new MultiKey<>(null, null);
        assertTrue(key1.equals(key2));
    }

    @Test
    public void testEqualsMixedNullKeys() {
        MultiKey<Integer> key1 = new MultiKey<>(ONE, null);
        MultiKey<Integer> key2 = new MultiKey<>(ONE, null);
        assertTrue(key1.equals(key2));
    }

    @Test
    public void testEqualsDifferentLengthKeys() {
        MultiKey<Integer> key1 = new MultiKey<>(ONE, TWO);
        MultiKey<Integer> key2 = new MultiKey<>(ONE, TWO, THREE);
        assertFalse(key1.equals(key2));
    }

    @Test
    public void testEqualsEmptyKeys() {
        MultiKey<Integer> key1 = new MultiKey<>(new Integer[] {});
        MultiKey<Integer> key2 = new MultiKey<>(new Integer[] {});
        assertTrue(key1.equals(key2));
    }
}
