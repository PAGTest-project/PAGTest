
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_getOptionsTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testGetOptions() {
        assertFalse(optionGroup.isSelected());

        Option optionA = OptionBuilder.create('a');
        Option optionB = OptionBuilder.create('b');

        optionGroup.addOption(optionA);
        optionGroup.addOption(optionB);

        Collection<Option> options = optionGroup.getOptions();
        assertNotNull(options, "null options");
        assertEquals(2, options.size());
        assertTrue(options.contains(optionA));
        assertTrue(options.contains(optionB));
    }
}
