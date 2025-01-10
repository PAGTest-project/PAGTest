
package net.hydromatic.morel;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class Shell_runTest {

    @Test
    public void testRunWithHelp() throws IOException {
        // Given
        ConfigImpl config = new ConfigImpl(false, false, false, true, true, Collections.emptyMap(), new File(""), () -> {}, -1);
        Terminal terminal = TerminalBuilder.builder().build();
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        verify(terminal.writer(), times(1)).println(anyString());
    }

    @Test
    public void testRunWithoutHelp() throws IOException {
        // Given
        ConfigImpl config = new ConfigImpl(false, false, false, true, false, Collections.emptyMap(), new File(""), () -> {}, -1);
        Terminal terminal = TerminalBuilder.builder().build();
        Shell shell = new Shell(config, terminal);

        // When
        shell.run();

        // Then
        verify(terminal.writer(), never()).println(anyString());
    }
}
