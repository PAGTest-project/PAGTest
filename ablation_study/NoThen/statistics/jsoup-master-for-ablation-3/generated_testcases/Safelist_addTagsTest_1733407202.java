
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
        safelist.addTags("a", "b", "blockquote");
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("blockquote"));
    }

    @Test
    public void testAddTags_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags(""));
    }

    @Test
    public void testAddTags_NullTag() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags((String) null));
    }

    @Test
    public void testAddTags_NoscriptTag() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags("noscript"));
    }

    @Test
    public void testAddTags_MixedValidAndInvalidTags() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags("a", "noscript", "b"));
    }

    @Test
    public void testAddTags_CaseInsensitive() {
        safelist.addTags("A", "B", "BLOCKQUOTE");
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("blockquote"));
    }

    @Test
    public void testAddTags_NoTags() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags());
    }

    @Test
    public void testAddTags_NullArray() {
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags((String[]) null));
    }
}
