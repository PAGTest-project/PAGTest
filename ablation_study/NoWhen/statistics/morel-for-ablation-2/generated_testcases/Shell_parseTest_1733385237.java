
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
        // Given
        Shell.ConfigImpl initialConfig = new Shell.ConfigImpl(true, false, true, false, false, ImmutableMap.of(), new File(""), () -> {}, -1);
        List<String> argList = ImmutableList.of(
            "--banner=false",
            "--terminal=dumb",
            "--echo",
            "--help",
            "--system=false",
            "--foreign=net.hydromatic.morel.foreign.Calcite",
            "--directory=testDir",
            "--maxUseDepth=10"
        );

        // When
        Shell.Config resultConfig = Shell.parse(initialConfig, argList);

        // Then
        assertTrue(resultConfig instanceof Shell.ConfigImpl);
        Shell.ConfigImpl resultConfigImpl = (Shell.ConfigImpl) resultConfig;
        assertEquals(false, resultConfigImpl.banner);
        assertEquals(true, resultConfigImpl.dumb);
        assertEquals(true, resultConfigImpl.echo);
        assertEquals(true, resultConfigImpl.help);
        assertEquals(false, resultConfigImpl.system);
        assertEquals(new File("testDir"), resultConfigImpl.directory);
        assertEquals(10, resultConfigImpl.maxUseDepth);

        // Verify valueMap
        Map<String, ForeignValue> valueMap = resultConfigImpl.valueMap;
        assertEquals(1, valueMap.size());
        assertTrue(valueMap.containsKey("net.hydromatic.morel.foreign.Calcite"));
    }
}
