
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_htmlTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testHtmlWithEmptyAttributes() {
        String result = attributes.html();
        assertEquals("", result);
    }

    @Test
    public void testHtmlWithSingleAttribute() {
        attributes.put("key1", "value1");
        String result = attributes.html();
        assertEquals(" key1=\"value1\"", result);
    }

    @Test
    public void testHtmlWithMultipleAttributes() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        String result = attributes.html();
        assertEquals(" key1=\"value1\" key2=\"value2\"", result);
    }

    @Test
    public void testHtmlAfterRemovingAttribute() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        attributes.remove("key1");
        String result = attributes.html();
        assertEquals(" key2=\"value2\"", result);
    }

    @Test
    public void testHtmlAfterNormalizingAttributes() {
        attributes.put("KEY1", "value1");
        attributes.put("key2", "value2");
        attributes.normalize();
        String result = attributes.html();
        assertEquals(" key1=\"value1\" key2=\"value2\"", result);
    }
}
