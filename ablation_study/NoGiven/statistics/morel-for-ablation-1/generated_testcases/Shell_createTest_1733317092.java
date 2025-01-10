
package net.hydromatic.morel;

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
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithCustomConfig() throws IOException {
        List<String> args = Collections.singletonList("--banner=false");
        Shell shell = Shell.create(args, inputStream, outputStream);
        assertNotNull(shell);
    }
}
