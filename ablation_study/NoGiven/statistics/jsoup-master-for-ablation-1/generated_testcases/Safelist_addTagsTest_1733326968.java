
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Safelist_addTagsTest {

    private Safelist safelist;

    @BeforeEach
    void setUp() {
        safelist = new Safelist();
    }

    @Test
    void testAddTags_ValidTags() {
        // Given
        String[] tags = {"p", "div", "span"};

        // When
        safelist.addTags(tags);

        // Then
        assertTrue(safelist.isSafeTag("p"));
        assertTrue(safelist.isSafeTag("div"));
        assertTrue(safelist.isSafeTag("span"));
    }

    @Test
    void testAddTags_InvalidTag() {
        // Given
        String[] tags = {"noscript"};

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags(tags));
    }

    @Test
    void testAddTags_EmptyTag() {
        // Given
        String[] tags = {""};

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags(tags));
    }

    @Test
    void testAddTags_NullTags() {
        // Given
        String[] tags = null;

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> safelist.addTags(tags));
    }
}
