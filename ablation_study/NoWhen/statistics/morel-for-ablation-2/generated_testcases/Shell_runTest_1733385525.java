
package net.hydromatic.morel;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class Shell_runTest {

    @Test
    public void testRunWithHelp() throws IOException {
        // Given
        Shell.ConfigImpl config = new Shell.ConfigImpl(false, false, false, true, true, ImmutableMap.of(), new File(""), Runnables.doNothing(), -1);
        Terminal terminal = TerminalBuilder.builder().build();
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        // Verify that usage method is called
        verify(terminal.writer(), times(1)).println(anyString());
    }

    @Test
    public void testRunWithoutHelp() throws IOException {
        // Given
        Shell.ConfigImpl config = new Shell.ConfigImpl(false, false, false, true, false, ImmutableMap.of(), new File(""), Runnables.doNothing(), -1);
        Terminal terminal = TerminalBuilder.builder().build();
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        // Verify that usage method is not called
        verify(terminal.writer(), never()).println(anyString());
    }
}
