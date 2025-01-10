
package net.hydromatic.morel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
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
        List<String> args = Arrays.asList("--banner=false");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithDumbTerminal() throws IOException {
        List<String> args = Arrays.asList("--terminal=dumb");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithEcho() throws IOException {
        List<String> args = Arrays.asList("--echo");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithHelp() throws IOException {
        List<String> args = Arrays.asList("--help");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithSystemFalse() throws IOException {
        List<String> args = Arrays.asList("--system=false");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithForeign() throws IOException {
        List<String> args = Arrays.asList("--foreign=com.example.ForeignClass");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithDirectory() throws IOException {
        List<String> args = Arrays.asList("--directory=/path/to/directory");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithMaxUseDepth() throws IOException {
        List<String> args = Arrays.asList("--maxUseDepth=5");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }
}
