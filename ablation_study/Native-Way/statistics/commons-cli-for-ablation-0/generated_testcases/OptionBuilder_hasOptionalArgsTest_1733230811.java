
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgsTest {

    @Test
    public void testHasOptionalArgs() {
        OptionBuilder.reset();
        OptionBuilder.hasOptionalArgs();
        assertEquals(Option.UNLIMITED_VALUES, OptionBuilder.argCount);
        assertTrue(OptionBuilder.optionalArg);
    }
}
