
package net.hydromatic.morel;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Shell_createTest {

    private InputStream inputStream;
    private OutputStream outputStream;

    @BeforeEach
    public void setUp() {
        inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void testCreateShellWithDefaultConfig() throws IOException {
        List<String> args = Collections.emptyList();
        Shell shell = createShell(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithCustomConfig() throws IOException {
        List<String> args = Collections.singletonList("--banner=false");
        Shell shell = createShell(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    private Shell createShell(List<String> args, InputStream in, OutputStream out) throws IOException {
        final Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, args);
        final TerminalBuilder builder = TerminalBuilder.builder();
        builder.streams(in, out);
        final Shell.ConfigImpl configImpl = (Shell.ConfigImpl) config;
        builder.system(configImpl.system);
        builder.dumb(configImpl.dumb);
        if (configImpl.dumb) {
            builder.type("dumb");
        }
        final Terminal terminal = builder.build();
        return new Shell(config, terminal);
    }
}
