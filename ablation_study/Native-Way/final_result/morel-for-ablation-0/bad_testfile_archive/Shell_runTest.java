
package net.hydromatic.morel;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.*;

public class Shell_runTest {

    private Shell shell;
    private ConfigImpl config;
    private Terminal terminal;

    @BeforeEach
    public void setUp() throws IOException {
        config = Mockito.mock(ConfigImpl.class);
        terminal = TerminalBuilder.builder().build();
        shell = new Shell(config, terminal);
    }

    @Test
    public void testRunWithHelp() {
        when(config.help).thenReturn(true);
        shell.run();
        verify(config, times(1)).help();
        verify(terminal, times(1)).writer();
    }

    @Test
    public void testRunWithoutHelp() throws IOException {
        when(config.help).thenReturn(false);
        when(config.banner).thenReturn(true);
        when(config.directory).thenReturn(new java.io.File("."));
        when(config.valueMap).thenReturn(Map.of());
        when(config.maxUseDepth).thenReturn(1);
        when(config.echo).thenReturn(false);
        shell.run();
        verify(config, times(1)).help();
        verify(terminal, times(1)).writer();
    }
}
