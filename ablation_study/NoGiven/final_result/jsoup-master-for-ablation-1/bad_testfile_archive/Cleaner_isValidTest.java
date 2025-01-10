
package org.jsoup.safety;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Cleaner_isValidTest {

    @Test
    public void testIsValid_ValidDocument() {
        Cleaner cleaner = new Cleaner(Safelist.basic());
        Document dirtyDocument = Jsoup.parse("<html><head></head><body><p>Valid content</p></body></html>");
        assertTrue(cleaner.isValid(dirtyDocument));
    }

    @Test
    public void testIsValid_InvalidDocument() {
        Cleaner cleaner = new Cleaner(Safelist.basic());
        Document dirtyDocument = Jsoup.parse("<html><head><script>alert('XSS')</script></head><body><p>Valid content</p></body></html>");
        assertFalse(cleaner.isValid(dirtyDocument));
    }
}
