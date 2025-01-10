
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_addValueTest {
    private Option option;

    @BeforeEach
    public void setUp() {
        option = new Option("f", null);
    }

    @Test
    public void testAddValue() {
        assertThrows(UnsupportedOperationException.class, () -> option.addValue("value1"));
    }

    @Test
    public void testAddValueAfterClearValues() {
        option.clearValues();
        assertThrows(UnsupportedOperationException.class, () -> option.addValue("value2"));
    }

    @Test
    public void testAddValueWithAcceptsArg() {
        option.clearValues();
        if (option.acceptsArg()) {
            assertThrows(UnsupportedOperationException.class, () -> option.addValue("value3"));
        }
    }

    @Test
    public void testAddValueWithRequiresArg() {
        option.clearValues();
        if (option.requiresArg()) {
            assertThrows(UnsupportedOperationException.class, () -> option.addValue("value4"));
        }
    }
}
