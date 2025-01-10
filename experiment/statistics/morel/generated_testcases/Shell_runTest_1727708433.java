
package net.hydromatic.morel;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.mockito.Mockito.*;
import com.google.common.collect.ImmutableMap;

public class Shell_runTest {

    @Test
    public void testRunWithHelp() throws IOException {
        // Given
        ConfigImpl config = mock(ConfigImpl.class);
        Terminal terminal = mock(Terminal.class);
        Shell shell = new Shell(config, terminal);

        when(config.help).thenReturn(true);

        // When
        shell.run();

        // Then
        verify(terminal.writer(), times(1)).println(anyString());
    }

    @Test
    public void testRunWithoutHelp() throws IOException {
        // Given
        ConfigImpl config = mock(ConfigImpl.class);
        Terminal terminal = mock(Terminal.class);
        Shell shell = new Shell(config, terminal);

        when(config.help).thenReturn(false);
        when(config.banner).thenReturn(false);
        when(config.directory).thenReturn(new File("."));
        when(config.maxUseDepth).thenReturn(1);
        when(config.echo).thenReturn(false);
        when(config.valueMap).thenReturn(ImmutableMap.of());

        // When
        shell.run();

        // Then
        verify(terminal.writer(), never()).println(anyString());
    }
}
