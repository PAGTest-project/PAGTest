
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lorem_paragraphsTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        lorem = new Lorem(new BaseProviders());
    }

    @Test
    public void testParagraphsWithZeroCount() {
        List<String> paragraphs = lorem.paragraphs(0);
        assertTrue(paragraphs.isEmpty());
    }

    @Test
    public void testParagraphsWithPositiveCount() {
        int paragraphCount = 5;
        List<String> paragraphs = lorem.paragraphs(paragraphCount);
        assertEquals(paragraphCount, paragraphs.size());
    }

    @Test
    public void testParagraphsContent() {
        int paragraphCount = 3;
        List<String> paragraphs = lorem.paragraphs(paragraphCount);
        for (String paragraph : paragraphs) {
            assertTrue(paragraph.contains("."));
        }
    }
}
