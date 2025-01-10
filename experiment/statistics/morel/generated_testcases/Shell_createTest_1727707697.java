
package net.hydromatic.morel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Shell_createTest {

    private Shell shell;
    private ByteArrayInputStream inputStream;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        inputStream = new ByteArrayInputStream("".getBytes());
        outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void testCreateShellWithDefaultConfig() throws IOException {
        List<String> args = Arrays.asList("--banner=false", "--terminal=dumb");
        shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithCustomConfig() throws IOException {
        List<String> args = Arrays.asList("--banner=true", "--terminal=dumb", "--directory=/tmp");
        shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }
}
