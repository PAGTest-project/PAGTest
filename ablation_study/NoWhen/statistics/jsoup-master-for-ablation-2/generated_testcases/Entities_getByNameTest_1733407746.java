
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Entities_getByNameTest {

    @Test
    public void testGetByNameWithValidEntity() {
        String name = "lt";
        String expected = "<";
        assertEquals(expected, Entities.getByName(name));
    }

    @Test
    public void testGetByNameWithInvalidEntity() {
        String name = "invalidEntity";
        String expected = "";
        assertEquals(expected, Entities.getByName(name));
    }

    @Test
    public void testGetByNameWithMultiPointsEntity() {
        String name = "NotNestedLessLess";
        String expected = "⨔";
        assertEquals(expected, Entities.getByName(name));
    }

    @Test
    public void testGetByNameWithEmptyName() {
        String name = "";
        String expected = "";
        assertEquals(expected, Entities.getByName(name));
    }
}
