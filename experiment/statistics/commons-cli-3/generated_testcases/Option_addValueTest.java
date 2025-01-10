
package org.apache.commons.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Option_addValueTest {

    @Test
    public void testAddValue() {
        final Option option = new Option("f", null);
        assertThrows(UnsupportedOperationException.class, () -> option.addValue("testValue"));
    }
}
