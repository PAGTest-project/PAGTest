
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Option_getValueTest {

    private Option option;

    @BeforeEach
    public void setUp() {
        option = new Option("f", null);
        option.setArgs(Option.UNLIMITED_VALUES);
    }

    @Test
    public void testGetValueWithDefaultValue() {
        // Given
        option.clearValues();

        // When
        String result = option.getValue("default");

        // Then
        assertEquals("default", result);
    }

    @Test
    public void testGetValueWithPopulatedValues() {
        // Given
        option.clearValues();
        option.processValue("foo");

        // When
        String result = option.getValue("default");

        // Then
        assertEquals("foo", result);
    }
}
