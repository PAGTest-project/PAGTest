
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Attribute_createFromEncodedTest {

    @BeforeEach
    public void setUp() {
        // No setup required for this test case
    }

    @Test
    public void testCreateFromEncoded() {
        String unencodedKey = "key";
        String encodedValue = "Hello &amp; World";
        Attribute attribute = Attribute.createFromEncoded(unencodedKey, encodedValue);

        assertEquals(unencodedKey, attribute.getKey());
        assertEquals("Hello & World", attribute.getValue());
    }
}
