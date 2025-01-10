
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Entities_getByNameTest {

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
        String name = "AElig";
        String expected = "Æ";
        String result = Entities.getByName(name);
        assertEquals(expected, result);
    }
}
