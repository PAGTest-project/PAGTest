
package net.hydromatic.morel;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.hydromatic.morel.foreign.ForeignValue;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Shell_parseTest {

    @Test
    public void testParse() {
        Shell.ConfigImpl initialConfig = new Shell.ConfigImpl(true, false, true, false, false, ImmutableMap.of(), new File(""), () -> {}, -1);
        List<String> argList = ImmutableList.of(
            "--banner=false",
            "--terminal=dumb",
            "--echo",
            "--help",
            "--system=false",
            "--foreign=net.hydromatic.morel.foreign.Calcite",
            "--directory=/tmp",
            "--maxUseDepth=10"
        );

        Shell.Config result = Shell.parse(initialConfig, argList);

        assertTrue(result instanceof Shell.ConfigImpl);
        Shell.ConfigImpl resultImpl = (Shell.ConfigImpl) result;

        assertEquals(false, resultImpl.banner);
        assertEquals(true, resultImpl.dumb);
        assertEquals(true, resultImpl.echo);
        assertEquals(true, resultImpl.help);
        assertEquals(false, resultImpl.system);
        assertEquals(new File("/tmp"), resultImpl.directory);
        assertEquals(10, resultImpl.maxUseDepth);

        Map<String, ForeignValue> valueMap = resultImpl.valueMap;
        assertTrue(valueMap.containsKey("net.hydromatic.morel.foreign.Calcite"));
    }
}
