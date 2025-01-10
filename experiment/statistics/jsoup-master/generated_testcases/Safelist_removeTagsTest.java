
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeTagsTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
        safelist.addTags("a", "b", "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em", "i", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong", "sub", "sup", "u", "ul");
    }

    @Test
    public void testRemoveTags_ValidTags() {
        String[] tagsToRemove = {"a", "b", "blockquote"};
        safelist.removeTags(tagsToRemove);

        assertFalse(safelist.isSafeTag("a"));
        assertFalse(safelist.isSafeTag("b"));
        assertFalse(safelist.isSafeTag("blockquote"));
        assertTrue(safelist.isSafeTag("p"));
    }

    @Test
    public void testRemoveTags_InvalidTags() {
        String[] tagsToRemove = {"invalidTag", "anotherInvalidTag"};
        safelist.removeTags(tagsToRemove);

        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("p"));
    }

    @Test
    public void testRemoveTags_NullTags() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeTags(null);
        });
    }

    @Test
    public void testRemoveTags_EmptyTags() {
        String[] tagsToRemove = {""};
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeTags(tagsToRemove);
        });
    }
}
