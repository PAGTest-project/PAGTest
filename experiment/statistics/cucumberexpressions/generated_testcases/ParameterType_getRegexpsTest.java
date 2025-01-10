
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_getRegexpsTest {

    @Test
    public void testGetRegexps() {
        List<String> expectedRegexps = Arrays.asList("\\d+", "\\w+");
        ParameterType<String> parameterType = new ParameterType<>("testName", expectedRegexps, String.class, (String arg) -> arg, true, false);
        List<String> actualRegexps = parameterType.getRegexps();
        assertEquals(expectedRegexps, actualRegexps);
    }
}
