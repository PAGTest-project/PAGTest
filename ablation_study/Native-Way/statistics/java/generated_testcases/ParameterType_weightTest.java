
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterType_weightTest {

    @Test
    void testWeightForIntegerClass() {
        ParameterType<Integer> parameterType = new ParameterType<>("intParam", "(\\d+)", Integer.class, (String s) -> Integer.parseInt(s));
        assertEquals(1000, parameterType.weight());
    }

    @Test
    void testWeightForNonIntegerType() {
        ParameterType<String> parameterType = new ParameterType<>("stringParam", "(\\w+)", String.class, (String s) -> s);
        assertEquals(0, parameterType.weight());
    }
}
