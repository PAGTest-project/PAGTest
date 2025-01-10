
package org.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jsoup_cleanTest {

    @Test
    public void testCleanWithBasicSafelist() {
        String bodyHtml = "<p><b>Hello</b> <a href='http://example.com/' style='color:red;'>World</a></p>";
        String baseUri = "http://example.com/";
        Safelist safelist = Safelist.basic();

        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);

        assertEquals("<p><b>Hello</b> <a href=\"http://example.com/\" rel=\"nofollow\">World</a></p>", cleanedHtml);
    }

    @Test
    public void testCleanWithRelaxedSafelist() {
        String bodyHtml = "<p><b>Hello</b> <a href='http://example.com/' style='color:red;'>World</a></p>";
        String baseUri = "http://example.com/";
        Safelist safelist = Safelist.relaxed();

        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);

        assertEquals("<p><b>Hello</b> <a href=\"http://example.com/\" style=\"color:red;\">World</a></p>", cleanedHtml);
    }

    @Test
    public void testCleanWithNoneSafelist() {
        String bodyHtml = "<p><b>Hello</b> <a href='http://example.com/' style='color:red;'>World</a></p>";
        String baseUri = "http://example.com/";
        Safelist safelist = Safelist.none();

        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);

        assertEquals("Hello World", cleanedHtml);
    }

    @Test
    public void testCleanWithCustomSafelist() {
        String bodyHtml = "<p><b>Hello</b> <a href='http://example.com/' style='color:red;'>World</a></p>";
        String baseUri = "http://example.com/";
        Safelist safelist = new Safelist()
                .addTags("p", "b")
                .addAttributes("a", "href");

        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);

        assertEquals("<p><b>Hello</b> <a href=\"http://example.com/\">World</a></p>", cleanedHtml);
    }
}
