
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterType_weightTest {

    @Test
    void testWeightForIntegerClass() {
        ParameterType<Integer> parameterType = new ParameterType<>("intParam", "(\\d+)", Integer.class, (String arg) -> Integer.parseInt(arg));
        assertEquals(1000, parameterType.weight());
    }

    @Test
    void testWeightForNonIntegerClass() {
        ParameterType<String> parameterType = new ParameterType<>("stringParam", "(\\w+)", String.class, (String arg) -> arg);
        assertEquals(0, parameterType.weight());
    }
}
