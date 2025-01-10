
package org.jsoup.safety;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Safelist_removeAttributesTest {
    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = new Safelist();
        safelist.addTags("a", "img");
        safelist.addAttributes("a", "href", "class");
        safelist.addAttributes("img", "src", "alt");
    }

    @Test
    public void testRemoveAttributes_SingleTag() {
        safelist.removeAttributes("a", "class");
        assertFalse(safelist.isSafeAttribute("a", null, new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_AllTags() {
        safelist.removeAttributes(":all", "class");
        assertFalse(safelist.isSafeAttribute("a", null, new Attribute("class", "")));
        assertFalse(safelist.isSafeAttribute("img", null, new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_TagNotAllowed() {
        safelist.removeAttributes("div", "class");
        assertFalse(safelist.isSafeAttribute("div", null, new Attribute("class", "")));
    }

    @Test
    public void testRemoveAttributes_NoAttributesSupplied() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeAttributes("a");
        });
    }

    @Test
    public void testRemoveAttributes_EmptyTag() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeAttributes("", "class");
        });
    }

    @Test
    public void testRemoveAttributes_NullAttributes() {
        assertThrows(IllegalArgumentException.class, () -> {
            safelist.removeAttributes("a", (String[]) null);
        });
    }
}
