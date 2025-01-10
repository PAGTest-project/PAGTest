
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
    public void testParseWithBannerFalse() {
        List<String> argList = ImmutableList.of("--banner=false");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(false, ((Shell.ConfigImpl) config).banner);
    }

    @Test
    public void testParseWithTerminalDumb() {
        List<String> argList = ImmutableList.of("--terminal=dumb");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(true, ((Shell.ConfigImpl) config).dumb);
    }

    @Test
    public void testParseWithEcho() {
        List<String> argList = ImmutableList.of("--echo");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(true, ((Shell.ConfigImpl) config).echo);
    }

    @Test
    public void testParseWithHelp() {
        List<String> argList = ImmutableList.of("--help");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(true, ((Shell.ConfigImpl) config).help);
    }

    @Test
    public void testParseWithSystemFalse() {
        List<String> argList = ImmutableList.of("--system=false");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(false, ((Shell.ConfigImpl) config).system);
    }

    @Test
    public void testParseWithForeign() {
        List<String> argList = ImmutableList.of("--foreign=com.example.TestClass");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        // Assuming instantiate method works correctly, we just check if the method is called
        // and the valueMap is built correctly.
        assertEquals(1, ((Shell.ConfigImpl) config).valueMap.size());
    }

    @Test
    public void testParseWithDirectory() {
        List<String> argList = ImmutableList.of("--directory=/test/dir");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(new File("/test/dir"), ((Shell.ConfigImpl) config).directory);
    }

    @Test
    public void testParseWithMaxUseDepth() {
        List<String> argList = ImmutableList.of("--maxUseDepth=5");
        Shell.Config config = Shell.parse(Shell.ConfigImpl.DEFAULT, argList);
        assertEquals(5, ((Shell.ConfigImpl) config).maxUseDepth);
    }
}
