
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_cloneTest {
    private Attribute originalAttribute;

    @BeforeEach
    public void setUp() {
        originalAttribute = new Attribute("key", "value");
    }

    @Test
    public void testCloneEqualsOriginal() {
        Attribute clonedAttribute = originalAttribute.clone();
        assertEquals(originalAttribute, clonedAttribute);
    }

    @Test
    public void testCloneHasSameHtmlRepresentation() {
        Attribute clonedAttribute = originalAttribute.clone();
        assertEquals(originalAttribute.html(), clonedAttribute.html());
    }

    @Test
    public void testCloneIsDeepCopy() {
        Attribute clonedAttribute = originalAttribute.clone();
        clonedAttribute.setKey("newKey");
        clonedAttribute.setValue("newValue");

        assertNotEquals(originalAttribute.getKey(), clonedAttribute.getKey());
        assertNotEquals(originalAttribute.getValue(), clonedAttribute.getValue());
    }
}
