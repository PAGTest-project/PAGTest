
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_weightTest {

    @Test
    public void testWeightForIntegerClass() {
        ParameterType<Integer> parameterType = new ParameterType<>("test", "(\\d+)", Integer.class, (String arg) -> Integer.parseInt(arg));
        assertEquals(1000, parameterType.weight());
    }

    @Test
    public void testWeightForNonIntegerClass() {
        ParameterType<String> parameterType = new ParameterType<>("test", "(\\w+)", String.class, (String arg) -> arg);
        assertEquals(0, parameterType.weight());
    }
}
