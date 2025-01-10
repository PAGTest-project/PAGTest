
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_toStringTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testToString() {
        attributes.put("One", "One");
        attributes.put(Attributes.internalKey("baseUri"), "example.com");
        attributes.put("Two", "Two");
        attributes.put(Attributes.internalKey("another"), "example.com");

        assertEquals(" One=\"One\" Two=\"Two\"", attributes.toString());
    }
}
