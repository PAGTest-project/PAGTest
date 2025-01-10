
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
    public void testIdSetSuccess() {
        element.id("testId");
        assertEquals("testId", element.id());
    }

    @Test
    public void testIdSetNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            element.id(null);
        });
    }

    @Test
    public void testIdSetEmpty() {
        element.id("");
        assertEquals("", element.id());
    }

    @Test
    public void testIdSetWhitespace() {
        element.id("   ");
        assertEquals("   ", element.id());
    }

    @Test
    public void testIdSetMultipleTimes() {
        element.id("firstId");
        element.id("secondId");
        assertEquals("secondId", element.id());
    }
}
