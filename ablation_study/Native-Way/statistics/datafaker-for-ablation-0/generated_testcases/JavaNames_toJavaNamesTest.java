
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaNames_toJavaNamesTest {

    @Test
    public void testToJavaNames_NullInput() {
        String result = JavaNames.toJavaNames(null, true);
        assertEquals(null, result);
    }

    @Test
    public void testToJavaNames_EmptyInput() {
        String result = JavaNames.toJavaNames("", true);
        assertEquals("", result);
    }

    @Test
    public void testToJavaNames_MethodCase() {
        String result = JavaNames.toJavaNames("some_string", true);
        assertEquals("someString", result);
    }

    @Test
    public void testToJavaNames_NonMethodCase() {
        String result = JavaNames.toJavaNames("some_string", false);
        assertEquals("SomeString", result);
    }

    @Test
    public void testToJavaNames_MixedCase() {
        String result = JavaNames.toJavaNames("some_Mixed_String", true);
        assertEquals("someMixedString", result);
    }
}
