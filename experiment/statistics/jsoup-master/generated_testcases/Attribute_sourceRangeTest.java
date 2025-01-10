
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Attribute_sourceRangeTest {
    private Attribute attribute;
    private Attributes parent;

    @BeforeEach
    public void setUp() {
        parent = mock(Attributes.class);
        attribute = new Attribute("key", "value", parent);
    }

    @Test
    public void testSourceRangeWithParent() {
        Range.AttributeRange expectedRange = Range.AttributeRange.UntrackedAttr;
        when(parent.sourceRange("key")).thenReturn(expectedRange);

        assertEquals(expectedRange, attribute.sourceRange());
    }

    @Test
    public void testSourceRangeWithoutParent() {
        attribute = new Attribute("key", "value", null);
        assertEquals(Range.AttributeRange.UntrackedAttr, attribute.sourceRange());
    }
}
