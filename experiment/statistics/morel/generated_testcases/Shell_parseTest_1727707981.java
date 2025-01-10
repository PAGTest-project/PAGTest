
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
        shell = new Shell(ConfigImpl.DEFAULT, null);
    }

    @Test
    public void testParseWithBannerFalse() {
        List<String> argList = ImmutableList.of("--banner=false");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        assertEquals(false, ((ConfigImpl) config).banner);
    }

    @Test
    public void testParseWithTerminalDumb() {
        List<String> argList = ImmutableList.of("--terminal=dumb");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        assertEquals(true, ((ConfigImpl) config).dumb);
    }

    @Test
    public void testParseWithEcho() {
        List<String> argList = ImmutableList.of("--echo");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        assertEquals(true, ((ConfigImpl) config).echo);
    }

    @Test
    public void testParseWithHelp() {
        List<String> argList = ImmutableList.of("--help");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        assertEquals(true, ((ConfigImpl) config).help);
    }

    @Test
    public void testParseWithSystemFalse() {
        List<String> argList = ImmutableList.of("--system=false");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        assertEquals(false, ((ConfigImpl) config).system);
    }

    @Test
    public void testParseWithForeign() {
        List<String> argList = ImmutableList.of("--foreign=com.example.TestClass");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        // Assuming instantiate method works correctly, we just check if the method is called
        // and the valueMap is built correctly.
        assertEquals(1, ((ConfigImpl) config).valueMap.size());
    }

    @Test
    public void testParseWithDirectory() {
        List<String> argList = ImmutableList.of("--directory=/test/dir");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        assertEquals(new File("/test/dir"), ((ConfigImpl) config).directory);
    }

    @Test
    public void testParseWithMaxUseDepth() {
        List<String> argList = ImmutableList.of("--maxUseDepth=5");
        Config config = Shell.parse(ConfigImpl.DEFAULT, argList);
        assertEquals(5, ((ConfigImpl) config).maxUseDepth);
    }
}
