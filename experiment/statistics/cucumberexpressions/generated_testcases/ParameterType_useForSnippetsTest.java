
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterType_useForSnippetsTest {

    private ParameterType<?> parameterType;

    @BeforeEach
    public void setUp() {
        parameterType = new ParameterType<>("name", "regexp", String.class, (String arg) -> arg, true, false);
    }

    @Test
    public void testUseForSnippetsTrue() {
        assertTrue(parameterType.useForSnippets());
    }

    @Test
    public void testUseForSnippetsFalse() {
        ParameterType<?> parameterTypeFalse = new ParameterType<>("name", "regexp", String.class, (String arg) -> arg, false, false);
        assertFalse(parameterTypeFalse.useForSnippets());
    }
}
