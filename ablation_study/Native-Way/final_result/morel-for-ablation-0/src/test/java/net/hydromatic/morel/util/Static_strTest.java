
package net.hydromatic.morel.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_strTest {
    private StringBuilder builder;

    @BeforeEach
    public void setUp() {
        builder = new StringBuilder();
    }

    @Test
    public void testStrWithNonEmptyStringBuilder() {
        builder.append("Hello, World!");
        assertEquals("Hello, World!", Static.str(builder));
        assertEquals(0, builder.length());
    }

    @Test
    public void testStrWithEmptyStringBuilder() {
        assertEquals("", Static.str(builder));
        assertEquals(0, builder.length());
    }

    @Test
    public void testStrWithMultipleCalls() {
        builder.append("First");
        assertEquals("First", Static.str(builder));
        assertEquals(0, builder.length());

        builder.append("Second");
        assertEquals("Second", Static.str(builder));
        assertEquals(0, builder.length());
    }
}
