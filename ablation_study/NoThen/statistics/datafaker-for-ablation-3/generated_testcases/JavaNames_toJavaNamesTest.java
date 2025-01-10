
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JavaNames_toJavaNamesTest {

    @Test
    void testToJavaNames_NullInput() {
        assertNull(JavaNames.toJavaNames(null, true));
        assertNull(JavaNames.toJavaNames(null, false));
    }

    @Test
    void testToJavaNames_EmptyInput() {
        assertEquals("", JavaNames.toJavaNames("", true));
        assertEquals("", JavaNames.toJavaNames("", false));
    }

    @Test
    void testToJavaNames_MethodCase() {
        assertEquals("helloWorld", JavaNames.toJavaNames("hello_world", true));
    }

    @Test
    void testToJavaNames_NonMethodCase() {
        assertEquals("HelloWorld", JavaNames.toJavaNames("hello_world", false));
    }

    @Test
    void testToJavaNames_MixedCase() {
        assertEquals("helloWorld123", JavaNames.toJavaNames("hello_world_123", true));
        assertEquals("HelloWorld123", JavaNames.toJavaNames("hello_world_123", false));
    }
}
