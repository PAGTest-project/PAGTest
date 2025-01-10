
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
        String result = JavaNames.toJavaNames("HELLO_WORLD", true);
        assertEquals("helloWorld", result);
    }

    @Test
    public void testToJavaNames_NonMethodCase() {
        String result = JavaNames.toJavaNames("hello_world", false);
        assertEquals("HelloWorld", result);
    }

    @Test
    public void testToJavaNames_MixedCase() {
        String result = JavaNames.toJavaNames("HELLO_world", true);
        assertEquals("helloWorld", result);
    }

    @Test
    public void testToJavaNames_NoUnderscore() {
        String result = JavaNames.toJavaNames("HELLOWORLD", true);
        assertEquals("helloworld", result);
    }
}
