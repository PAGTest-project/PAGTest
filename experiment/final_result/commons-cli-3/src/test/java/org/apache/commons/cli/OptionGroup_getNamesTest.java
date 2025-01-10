
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionGroup_getNamesTest {

    private OptionGroup optionGroup;

    @BeforeEach
    public void setUp() {
        optionGroup = new OptionGroup();
    }

    @Test
    public void testGetNames() {
        optionGroup.addOption(OptionBuilder.create('a'));
        optionGroup.addOption(OptionBuilder.create('b'));

        Collection<String> names = optionGroup.getNames();

        assertEquals(2, names.size());
        assertTrue(names.contains("a"));
        assertTrue(names.contains("b"));
    }
}
