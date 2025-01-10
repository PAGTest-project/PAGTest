
package org.jsoup.safety;

import org.jsoup.helper.Validate;
import org.jsoup.internal.Functions;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Safelist_addAttributesTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
    }

    @Test
    public void testAddAttributes_validAttributes() {
        String tag = "a";
        String[] attributes = {"href", "class"};

        safelist.addAttributes(tag, attributes);

        TagName tagName = TagName.valueOf(tag);
        Set<AttributeKey> expectedAttributes = new HashSet<>();
        for (String attr : attributes) {
            expectedAttributes.add(AttributeKey.valueOf(attr));
        }

        assertEquals(expectedAttributes, safelist.getAttributes().get(tagName));
    }

    @Test
    public void testAddAttributes_emptyTag() {
        String tag = "";
        String[] attributes = {"href", "class"};

        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes(tag, attributes);
        });
    }

    @Test
    public void testAddAttributes_nullAttributes() {
        String tag = "a";
        String[] attributes = null;

        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes(tag, attributes);
        });
    }

    @Test
    public void testAddAttributes_noAttributes() {
        String tag = "a";
        String[] attributes = {};

        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes(tag, attributes);
        });
    }

    @Test
    public void testAddAttributes_emptyAttribute() {
        String tag = "a";
        String[] attributes = {"", "class"};

        assertThrows(IllegalArgumentException.class, () -> {
            safelist.addAttributes(tag, attributes);
        });
    }
}
