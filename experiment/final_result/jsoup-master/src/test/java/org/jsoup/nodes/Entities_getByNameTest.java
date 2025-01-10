
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Entities_getByNameTest {

    @BeforeEach
    public void setUp() {
        // Initialize any necessary setup for the tests
    }

    @Test
    public void testGetByName_ExistingEntity() {
        String name = "lt";
        String expected = "<";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }

    @Test
    public void testGetByName_NonExistingEntity() {
        String name = "nonexistent";
        String expected = "";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }

    @Test
    public void testGetByName_MultiPointsEntity() {
        String name = "AElig";
        String expected = "Æ";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }
}
