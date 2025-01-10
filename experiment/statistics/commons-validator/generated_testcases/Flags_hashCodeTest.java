
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Flags_hashCodeTest {

    @Test
    public void testHashCode() {
        Flags flags = new Flags(45);
        assertEquals(45, flags.hashCode());
    }
}
