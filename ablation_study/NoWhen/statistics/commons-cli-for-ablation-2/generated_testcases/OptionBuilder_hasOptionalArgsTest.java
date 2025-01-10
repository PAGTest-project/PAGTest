
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class OptionBuilder_hasOptionalArgsTest {

    @Test
    public void testHasOptionalArgs() {
        OptionBuilder.hasOptionalArgs();
        Option option = OptionBuilder.create("opt");
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }

    @Test
    public void testHasOptionalArgsWithCreate() {
        OptionBuilder.hasOptionalArgs();
        Option option = OptionBuilder.create("opt");
        assertEquals(Option.UNLIMITED_VALUES, option.getArgs());
        assertTrue(option.hasOptionalArg());
    }
}
