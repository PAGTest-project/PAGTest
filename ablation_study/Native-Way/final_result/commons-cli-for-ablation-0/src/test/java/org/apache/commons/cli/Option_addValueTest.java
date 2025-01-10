
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
        assertThrows(UnsupportedOperationException.class, () -> option.addValue("testValue"));
    }
}
