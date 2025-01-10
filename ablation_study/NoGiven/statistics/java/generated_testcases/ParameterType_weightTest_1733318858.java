
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_weightTest {
    private ParameterType<Integer> integerParameterType;
    private ParameterType<String> stringParameterType;

    @BeforeEach
    public void setUp() {
        integerParameterType = new ParameterType<>("integerName", "integerRegexp", Integer.class, (Transformer<Integer>) s -> Integer.valueOf(s), true, false);
        stringParameterType = new ParameterType<>("stringName", "stringRegexp", String.class, (Transformer<String>) s -> s, true, false);
    }

    @Test
    public void weight_returns1000ForIntegerType() {
        int expectedWeight = 1000;
        int actualWeight = integerParameterType.weight();
        assertEquals(expectedWeight, actualWeight);
    }

    @Test
    public void weight_returns0ForNonIntegerType() {
        int expectedWeight = 0;
        int actualWeight = stringParameterType.weight();
        assertEquals(expectedWeight, actualWeight);
    }
}
