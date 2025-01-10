
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Entities_getByNameTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testGetByNameWithKnownEntity() {
        String name = "amp";
        String expected = "&";
        assertEquals(expected, Entities.getByName(name));
    }

    @Test
    public void testGetByNameWithUnknownEntity() {
        String name = "unknown";
        String expected = "";
        assertEquals(expected, Entities.getByName(name));
    }

    @Test
    public void testGetByNameWithMultiPointsEntity() {
        String name = "AElig";
        String expected = "Æ";
        assertEquals(expected, Entities.getByName(name));
    }

    @Test
    public void testGetByNameWithEmptyName() {
        String name = "";
        String expected = "";
        assertEquals(expected, Entities.getByName(name));
    }

    @Test
    public void testGetByNameWithNullName() {
        String name = null;
        String expected = "";
        assertThrows(NullPointerException.class, () -> Entities.getByName(name));
    }
}
