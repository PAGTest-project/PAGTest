
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_getNameTest {

    @Test
    public void testGetName() {
        ParameterType<String> parameterType = new ParameterType<>("testName", "testRegexp", String.class, (String arg) -> arg);
        assertEquals("testName", parameterType.getName());
    }
}
