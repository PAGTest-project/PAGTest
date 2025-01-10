
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.regex.Pattern;

class ExpressionFactory_createExpressionTest {

    private final ParameterTypeRegistry parameterTypeRegistry = new ParameterTypeRegistry(null);
    private final ExpressionFactory expressionFactory = new ExpressionFactory(parameterTypeRegistry);

    @Test
    void testCreateExpression_EmptyString() {
        Expression expression = expressionFactory.createExpression("");
        assertTrue(expression instanceof CucumberExpression);
        assertEquals("", expression.getSource());
    }

    @Test
    void testCreateExpression_RegularExpressionWithAnchors() {
        Expression expression = expressionFactory.createExpression("^start$");
        assertTrue(expression instanceof RegularExpression);
        assertEquals("^start$", expression.getSource());
    }

    @Test
    void testCreateExpression_RegularExpressionWithSlashes() {
        Expression expression = expressionFactory.createExpression("/regex/");
        assertTrue(expression instanceof RegularExpression);
        assertEquals("regex", expression.getSource());
    }

    @Test
    void testCreateExpression_CucumberExpression() {
        Expression expression = expressionFactory.createExpression("simple expression");
        assertTrue(expression instanceof CucumberExpression);
        assertEquals("simple expression", expression.getSource());
    }
}
