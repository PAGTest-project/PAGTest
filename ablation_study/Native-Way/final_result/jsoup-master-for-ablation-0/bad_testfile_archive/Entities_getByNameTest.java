
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Entities_getByNameTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testGetByNameWithExistingEntity() {
        String name = "amp";
        String expected = "&";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }

    @Test
    public void testGetByNameWithNonExistingEntity() {
        String name = "nonexistent";
        String expected = "";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }

    @Test
    public void testGetByNameWithMultiPointsEntity() {
        String name = "NotNestedLessLess";
        String expected = "\u2AAB\u204A";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }

    @Test
    public void testGetByNameWithExtendedEntity() {
        String name = "hopf";
        String expected = "\uD835\uDD59";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }
}
