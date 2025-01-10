
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_getKeyTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = new Option("short", "long", false, "description");
    }

    @Test
    public void testGetKeyWithShortOption() {
        option.setLongOpt(null);
        assertEquals("short", option.getKey());
    }

    @Test
    public void testGetKeyWithLongOption() {
        option.setLongOpt("long");
        assertEquals("long", option.getKey());
    }
}
