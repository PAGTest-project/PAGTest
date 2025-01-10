
package org.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jsoup_cleanTest {

    @Test
    public void testClean() {
        // Given
        String bodyHtml = "<div><script>alert('XSS')</script></div>";
        String baseUri = "http://example.com";
        Safelist safelist = Safelist.basic();

        // When
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);

        // Then
        Document expectedCleanDoc = Jsoup.parseBodyFragment("<div></div>", baseUri);
        assertEquals(expectedCleanDoc.body().html(), cleanedHtml);
    }
}
