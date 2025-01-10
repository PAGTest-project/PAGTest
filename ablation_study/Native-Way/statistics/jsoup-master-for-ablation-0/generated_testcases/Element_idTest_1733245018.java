
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_idTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testIdWithNonNullId() {
        element.id("testId");
        assertEquals("testId", element.id());
    }

    @Test
    public void testIdWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.id(null);
        });
    }
}
