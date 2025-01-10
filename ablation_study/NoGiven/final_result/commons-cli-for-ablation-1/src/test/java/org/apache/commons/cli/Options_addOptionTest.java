
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Options_addOptionTest {

    private Options options;
    private Option option;

    @BeforeEach
    public void setUp() {
        options = new Options();
        option = mock(Option.class);
    }

    @Test
    public void testAddOption_withLongOptAndRequired() {
        when(option.getKey()).thenReturn("key");
        when(option.hasLongOpt()).thenReturn(true);
        when(option.getLongOpt()).thenReturn("longOpt");
        when(option.isRequired()).thenReturn(true);

        options.addOption(option);

        assertTrue(options.hasOption("key"));
        assertTrue(options.hasLongOption("longOpt"));
        assertEquals(1, options.getRequiredOptions().size());
    }

    @Test
    public void testAddOption_withoutLongOptAndNotRequired() {
        when(option.getKey()).thenReturn("key");
        when(option.hasLongOpt()).thenReturn(false);
        when(option.isRequired()).thenReturn(false);

        options.addOption(option);

        assertTrue(options.hasOption("key"));
        assertEquals(0, options.getRequiredOptions().size());
    }
}
