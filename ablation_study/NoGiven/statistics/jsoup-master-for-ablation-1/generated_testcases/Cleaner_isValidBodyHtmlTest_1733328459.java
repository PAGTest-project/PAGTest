
package org.jsoup.safety;

import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Cleaner_isValidBodyHtmlTest {

    @Test
    public void testIsValidBodyHtml_ValidHtml() {
        Cleaner cleaner = new Cleaner(Safelist.basic());
        String validHtml = "<p>Valid paragraph</p>";
        assertTrue(cleaner.isValidBodyHtml(validHtml));
    }

    @Test
    public void testIsValidBodyHtml_InvalidHtml() {
        Cleaner cleaner = new Cleaner(Safelist.basic());
        String invalidHtml = "<script>alert('XSS')</script>";
        assertFalse(cleaner.isValidBodyHtml(invalidHtml));
    }

    @Test
    public void testIsValidBodyHtml_EmptyHtml() {
        Cleaner cleaner = new Cleaner(Safelist.basic());
        String emptyHtml = "";
        assertTrue(cleaner.isValidBodyHtml(emptyHtml));
    }
}
