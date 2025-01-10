
package org.jsoup.safety;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Safelist_preserveRelativeLinksTest {

    @Test
    void testPreserveRelativeLinks() {
        Safelist safelist = new Safelist();

        // Given: A Safelist instance
        // When: preserveRelativeLinks is set to true
        safelist.preserveRelativeLinks(true);

        // Then: preserveRelativeLinks should be true
        assertTrue(safelist.preserveRelativeLinks);

        // When: preserveRelativeLinks is set to false
        safelist.preserveRelativeLinks(false);

        // Then: preserveRelativeLinks should be false
        assertFalse(safelist.preserveRelativeLinks);
    }
}
