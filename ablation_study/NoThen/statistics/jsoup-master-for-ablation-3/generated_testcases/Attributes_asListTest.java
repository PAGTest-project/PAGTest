
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Attributes_asListTest {

    @Test
    public void testAsList_WithInternalAndNonInternalKeys() {
        Attributes attributes = new Attributes();
        attributes.put("/internalKey", "internalValue"); // Prefix internal key with '/'
        attributes.put("nonInternalKey", "nonInternalValue");

        List<Attribute> result = attributes.asList();

        assertEquals(1, result.size());
        assertEquals("nonInternalKey", result.get(0).getKey());
        assertEquals("nonInternalValue", result.get(0).getValue());
        assertTrue(result instanceof Collections.UnmodifiableList);
    }
}
