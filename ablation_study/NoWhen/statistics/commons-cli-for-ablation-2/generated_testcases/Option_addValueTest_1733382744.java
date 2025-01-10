
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class Option_addValueTest {

    @Test
    public void testAddValueDeprecated() {
        final Option option = new Option("f", null);
        assertThrows(UnsupportedOperationException.class, () -> option.addValue("testValue"));
    }
}
