
package org.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Jsoup_cleanTest {

    @Test
    public void testClean() {
        // Given
        String bodyHtml = "<div><script>alert('XSS')</script></div>";
        String baseUri = "http://example.com";
        Safelist safelist = Safelist.basic();

        Document dirtyDoc = mock(Document.class);
        Document cleanDoc = mock(Document.class);
        Cleaner cleaner = mock(Cleaner.class);

        when(Jsoup.parseBodyFragment(bodyHtml, baseUri)).thenReturn(dirtyDoc);
        when(new Cleaner(safelist)).thenReturn(cleaner);
        when(cleaner.clean(dirtyDoc)).thenReturn(cleanDoc);
        when(cleanDoc.body()).thenReturn(cleanDoc);
        when(cleanDoc.html()).thenReturn("<div></div>");

        // When
        String result = Jsoup.clean(bodyHtml, baseUri, safelist);

        // Then
        assertEquals("<div></div>", result);
    }
}
