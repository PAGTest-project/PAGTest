
package org.jsoup.safety;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Safelist_addTagsTest {

    @Test
    void testAddTags_ValidTags() {
        Safelist safelist = new Safelist();
        safelist.addTags("a", "b", "p");
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("p"));
    }

    @Test
    void testAddTags_InvalidTag() {
        Safelist safelist = new Safelist();
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags("noscript"));
    }

    @Test
    void testAddTags_NullTags() {
        Safelist safelist = new Safelist();
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags((String[]) null));
    }

    @Test
    void testAddTags_EmptyTag() {
        Safelist safelist = new Safelist();
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags(""));
    }
}
