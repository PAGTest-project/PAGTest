
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CucumberExpression_getRegexpTest {

    private ParameterTypeRegistry parameterTypeRegistry;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(new Locale("en"));
    }

    @Test
    public void testGetRegexp() {
        String expression = "I have {int} cucumbers";
        CucumberExpression cucumberExpression = new CucumberExpression(expression, parameterTypeRegistry);

        Pattern expectedPattern = Pattern.compile("I have (-?\\d+) cucumbers");
        assertEquals(expectedPattern.pattern(), cucumberExpression.getRegexp().pattern());
    }
}
