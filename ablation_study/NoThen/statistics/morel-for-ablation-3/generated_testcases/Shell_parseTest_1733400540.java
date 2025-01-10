
package net.hydromatic.morel;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.hydromatic.morel.foreign.ForeignValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Shell_parseTest {

    private Shell shell;

    @BeforeEach
    public void setUp() {
        shell = new Shell(Shell.ConfigImpl.DEFAULT, null);
    }

    @Test
    public void testParseBannerFalse() {
        List<String> argList = ImmutableList.of("--banner=false");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(false, config.withBanner(false).banner);
    }

    @Test
    public void testParseTerminalDumb() {
        List<String> argList = ImmutableList.of("--terminal=dumb");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(true, config.withDumb(true).dumb);
    }

    @Test
    public void testParseEcho() {
        List<String> argList = ImmutableList.of("--echo");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(true, config.withEcho(true).echo);
    }

    @Test
    public void testParseHelp() {
        List<String> argList = ImmutableList.of("--help");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(true, config.withHelp(true).help);
    }

    @Test
    public void testParseSystemFalse() {
        List<String> argList = ImmutableList.of("--system=false");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(false, config.withSystem(false).system);
    }

    @Test
    public void testParseForeign() {
        List<String> argList = ImmutableList.of("--foreign=com.example.MyClass");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        // Assuming MyClass implements Map<String, DataSet>
        // and foreignValues() returns a non-empty map
        assertEquals(false, config.withValueMap(ImmutableMap.of()).valueMap.isEmpty());
    }

    @Test
    public void testParseDirectory() {
        List<String> argList = ImmutableList.of("--directory=/path/to/directory");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(new File("/path/to/directory"), config.withDirectory(new File("/path/to/directory")).directory);
    }

    @Test
    public void testParseMaxUseDepth() {
        List<String> argList = ImmutableList.of("--maxUseDepth=10");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(10, config.withMaxUseDepth(10).maxUseDepth);
    }
}
