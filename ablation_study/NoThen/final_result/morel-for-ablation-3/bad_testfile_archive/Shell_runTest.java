
package net.hydromatic.morel;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class Shell_runTest {

    @Test
    public void testRunWithHelp() throws IOException {
        // Given
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, Arrays.asList("--help"));
        Terminal terminal = TerminalBuilder.builder().build();
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        // Verify that usage was called
        verify(terminal.writer(), times(1)).println(anyString());
    }

    @Test
    public void testRunWithoutHelp() throws IOException {
        // Given
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, Arrays.asList("--banner=true"));
        Terminal terminal = TerminalBuilder.builder().build();
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        // Verify that banner was printed
        verify(terminal.writer(), times(1)).println(anyString());
    }
}
