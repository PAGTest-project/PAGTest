
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_addTagsTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testAddTags_ValidTags() {
        String[] tags = {"p", "div", "span"};
        safelist.addTags(tags);
        for (String tag : tags) {
            assertTrue(safelist.isSafeTag(tag));
        }
    }

    @Test
    public void testAddTags_NullTags() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addTags(null);
        });
    }

    @Test
    public void testAddTags_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addTags("");
        });
    }

    @Test
    public void testAddTags_NoscriptTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addTags("noscript");
        });
    }
}
