
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_getTypeTest {

    private ParameterType<String> parameterType;

    @BeforeEach
    public void setUp() {
        parameterType = new ParameterType<>("testName", "testRegexp", String.class, s -> s, true, false);
    }

    @Test
    public void getType_returnsCorrectType() {
        Type expectedType = String.class;
        Type actualType = parameterType.getType();
        assertEquals(expectedType, actualType);
    }
}
