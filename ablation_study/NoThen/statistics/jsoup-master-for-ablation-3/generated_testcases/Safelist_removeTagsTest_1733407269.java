
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Safelist_removeTagsTest {

    private Safelist safelist;

    @BeforeEach
    void setUp() {
        safelist = new Safelist();
        safelist.addTags("a", "b", "p"); // Initialize with some tags
    }

    @Test
    void testRemoveTags_ExistingTag() {
        // Given
        String[] tagsToRemove = {"a"};

        // When
        safelist.removeTags(tagsToRemove);

        // Then
        assertFalse(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("p"));
    }

    @Test
    void testRemoveTags_NonExistingTag() {
        // Given
        String[] tagsToRemove = {"img"};

        // When
        safelist.removeTags(tagsToRemove);

        // Then
        assertTrue(safelist.isSafeTag("a"));
        assertTrue(safelist.isSafeTag("b"));
        assertTrue(safelist.isSafeTag("p"));
        assertFalse(safelist.isSafeTag("img"));
    }
}
