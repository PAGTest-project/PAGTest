
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_hasClassTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testHasClassWhenAttributesIsNull() {
        element.attributes = null;
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttrIsEmpty() {
        element.attributes = new Attributes();
        element.attributes.put("class", "");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttrIsExactMatch() {
        element.attributes = new Attributes();
        element.attributes.put("class", "testClass");
        assertTrue(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttrContainsClassName() {
        element.attributes = new Attributes();
        element.attributes.put("class", "otherClass testClass anotherClass");
        assertTrue(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttrDoesNotContainClassName() {
        element.attributes = new Attributes();
        element.attributes.put("class", "otherClass anotherClass");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttrIsLongerThanClassName() {
        element.attributes = new Attributes();
        element.attributes.put("class", "testClassLonger");
        assertFalse(element.hasClass("testClass"));
    }

    @Test
    public void testHasClassWhenClassAttrIsShorterThanClassName() {
        element.attributes = new Attributes();
        element.attributes.put("class", "test");
        assertFalse(element.hasClass("testClass"));
    }
}
