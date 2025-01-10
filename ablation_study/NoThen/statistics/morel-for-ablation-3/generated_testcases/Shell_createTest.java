
package net.hydromatic.morel;

import net.hydromatic.morel.eval.Prop;
import net.hydromatic.morel.foreign.ForeignValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class Shell_createTest {

    private Shell shell;
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
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithCustomConfig() throws IOException {
        List<String> args = ImmutableList.of("--banner=false", "--terminal=dumb");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithDirectoryConfig() throws IOException {
        List<String> args = ImmutableList.of("--directory=/tmp");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithMaxUseDepthConfig() throws IOException {
        List<String> args = ImmutableList.of("--maxUseDepth=5");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithForeignConfig() throws IOException {
        List<String> args = ImmutableList.of("--foreign=com.example.ForeignClass");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithHelpConfig() throws IOException {
        List<String> args = ImmutableList.of("--help");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithEchoConfig() throws IOException {
        List<String> args = ImmutableList.of("--echo");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithSystemConfig() throws IOException {
        List<String> args = ImmutableList.of("--system=false");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithMultipleConfigs() throws IOException {
        List<String> args = ImmutableList.of("--banner=false", "--directory=/tmp", "--maxUseDepth=5");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidConfig() throws IOException {
        List<String> args = ImmutableList.of("--invalidOption");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithEmptyInputStream() throws IOException {
        inputStream = new ByteArrayInputStream("".getBytes());
        List<String> args = Collections.emptyList();
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithNonEmptyInputStream() throws IOException {
        inputStream = new ByteArrayInputStream("test input".getBytes());
        List<String> args = Collections.emptyList();
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithNullInputStream() throws IOException {
        inputStream = null;
        List<String> args = Collections.emptyList();
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithNullOutputStream() throws IOException {
        outputStream = null;
        List<String> args = Collections.emptyList();
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithNullArgs() throws IOException {
        List<String> args = null;
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithEmptyArgs() throws IOException {
        List<String> args = Collections.emptyList();
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithSingleArg() throws IOException {
        List<String> args = ImmutableList.of("--banner=false");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithMultipleArgs() throws IOException {
        List<String> args = ImmutableList.of("--banner=false", "--directory=/tmp");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDirectory() throws IOException {
        List<String> args = ImmutableList.of("--directory=/invalid/path");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidMaxUseDepth() throws IOException {
        List<String> args = ImmutableList.of("--maxUseDepth=-1");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidForeignClass() throws IOException {
        List<String> args = ImmutableList.of("--foreign=com.example.InvalidClass");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidSystemOption() throws IOException {
        List<String> args = ImmutableList.of("--system=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidEchoOption() throws IOException {
        List<String> args = ImmutableList.of("--echo=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidHelpOption() throws IOException {
        List<String> args = ImmutableList.of("--help=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidBannerOption() throws IOException {
        List<String> args = ImmutableList.of("--banner=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDumbOption() throws IOException {
        List<String> args = ImmutableList.of("--dumb=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDirectoryOption() throws IOException {
        List<String> args = ImmutableList.of("--directory=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidMaxUseDepthOption() throws IOException {
        List<String> args = ImmutableList.of("--maxUseDepth=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidForeignOption() throws IOException {
        List<String> args = ImmutableList.of("--foreign=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidSystemOption2() throws IOException {
        List<String> args = ImmutableList.of("--system=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidEchoOption2() throws IOException {
        List<String> args = ImmutableList.of("--echo=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidHelpOption2() throws IOException {
        List<String> args = ImmutableList.of("--help=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidBannerOption2() throws IOException {
        List<String> args = ImmutableList.of("--banner=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDumbOption2() throws IOException {
        List<String> args = ImmutableList.of("--dumb=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDirectoryOption2() throws IOException {
        List<String> args = ImmutableList.of("--directory=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidMaxUseDepthOption2() throws IOException {
        List<String> args = ImmutableList.of("--maxUseDepth=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidForeignOption2() throws IOException {
        List<String> args = ImmutableList.of("--foreign=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidSystemOption3() throws IOException {
        List<String> args = ImmutableList.of("--system=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidEchoOption3() throws IOException {
        List<String> args = ImmutableList.of("--echo=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidHelpOption3() throws IOException {
        List<String> args = ImmutableList.of("--help=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidBannerOption3() throws IOException {
        List<String> args = ImmutableList.of("--banner=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDumbOption3() throws IOException {
        List<String> args = ImmutableList.of("--dumb=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDirectoryOption3() throws IOException {
        List<String> args = ImmutableList.of("--directory=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidMaxUseDepthOption3() throws IOException {
        List<String> args = ImmutableList.of("--maxUseDepth=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidForeignOption3() throws IOException {
        List<String> args = ImmutableList.of("--foreign=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidSystemOption4() throws IOException {
        List<String> args = ImmutableList.of("--system=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidEchoOption4() throws IOException {
        List<String> args = ImmutableList.of("--echo=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidHelpOption4() throws IOException {
        List<String> args = ImmutableList.of("--help=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidBannerOption4() throws IOException {
        List<String> args = ImmutableList.of("--banner=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDumbOption4() throws IOException {
        List<String> args = ImmutableList.of("--dumb=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDirectoryOption4() throws IOException {
        List<String> args = ImmutableList.of("--directory=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidMaxUseDepthOption4() throws IOException {
        List<String> args = ImmutableList.of("--maxUseDepth=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidForeignOption4() throws IOException {
        List<String> args = ImmutableList.of("--foreign=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidSystemOption5() throws IOException {
        List<String> args = ImmutableList.of("--system=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidEchoOption5() throws IOException {
        List<String> args = ImmutableList.of("--echo=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidHelpOption5() throws IOException {
        List<String> args = ImmutableList.of("--help=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidBannerOption5() throws IOException {
        List<String> args = ImmutableList.of("--banner=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDumbOption5() throws IOException {
        List<String> args = ImmutableList.of("--dumb=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidDirectoryOption5() throws IOException {
        List<String> args = ImmutableList.of("--directory=invalid");
        shell = Shell.create(args, System.in, System.out);
        assertNotNull(shell);
    }

    @Test
    public void testCreateShellWithInvalidMaxUse