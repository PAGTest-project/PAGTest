
package net.hydromatic.morel;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class Shell_runTest {

    @Test
    public void testRunWithHelp() throws IOException {
        // Given
        Shell.Config config = Shell.Config.DEFAULT.withHelp(true);
        Terminal terminal = Mockito.mock(Terminal.class);
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        verify(terminal.writer(), times(1)).println(anyString());
    }

    @Test
    public void testRunWithoutHelp() throws IOException {
        // Given
        Shell.Config config = Shell.Config.DEFAULT.withHelp(false);
        Terminal terminal = Mockito.mock(Terminal.class);
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        verify(terminal.writer(), never()).println(anyString());
    }
}
