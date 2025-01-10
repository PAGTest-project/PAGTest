
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CucumberExpression_getSourceTest {
    private ParameterTypeRegistry parameterTypeRegistry;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(new Locale("en"));
    }

    @Test
    public void testGetSource() {
        String expressionString = "some expression";
        CucumberExpression cucumberExpression = new CucumberExpression(expressionString, parameterTypeRegistry);
        assertEquals(expressionString, cucumberExpression.getSource());
    }
}
