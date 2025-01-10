
package org.jsoup.safety;

import org.jsoup.internal.Functions;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Safelist_addEnforcedAttributeTest {

    @Test
    void testAddEnforcedAttribute() {
        Safelist safelist = new Safelist();
        safelist.addEnforcedAttribute("a", "rel", "nofollow");

        Map<Safelist.TagName, Map<Safelist.AttributeKey, Safelist.AttributeValue>> enforcedAttributes = safelist.getEnforcedAttributes();
        Safelist.TagName tagName = Safelist.TagName.valueOf("a");
        Safelist.AttributeKey attrKey = Safelist.AttributeKey.valueOf("rel");
        Safelist.AttributeValue attrVal = Safelist.AttributeValue.valueOf("nofollow");

        assertTrue(enforcedAttributes.containsKey(tagName));
        assertTrue(enforcedAttributes.get(tagName).containsKey(attrKey));
        assertEquals(attrVal, enforcedAttributes.get(tagName).get(attrKey));
    }
}
