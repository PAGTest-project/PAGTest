
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeTagsTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testRemoveTags_ValidTags() {
        safelist.addTags("a", "b", "c");
        safelist.removeTags("b");
        assertFalse(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("c"));
    }

    @Test
    public void testRemoveTags_InvalidTags() {
        safelist.addTags("a", "b", "c");
        safelist.removeTags("d");
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("c"));
        assertFalse(safelist.isSafeTag("d"));
    }

    @Test
    public void testRemoveTags_NullTags() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeTags(null);
        });
    }

    @Test
    public void testRemoveTags_EmptyTags() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeTags("");
        });
    }

    @Test
    public void testRemoveTags_MixedValidInvalidTags() {
        safelist.addTags("a", "b", "c");
        safelist.removeTags("b", "d");
        assertFalse(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("c"));
        assertFalse(safelist.isSafeTag("d"));
    }
}
