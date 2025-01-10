
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
        safelist.addTags("a", "b", "p");
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("p"));
    }

    @Test
    public void testAddTags_NullTags() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addTags((String[]) null);
        });
    }

    @Test
    public void testAddTags_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addTags("");
        });
    }

    @Test
    public void testAddTags_UnsupportedTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addTags("noscript");
        });
    }

    @Test
    public void testAddTags_MixedValidAndInvalidTags() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addTags("a", "noscript", "p");
        });
    }
}
