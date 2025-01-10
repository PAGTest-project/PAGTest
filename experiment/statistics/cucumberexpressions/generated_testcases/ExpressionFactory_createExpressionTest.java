
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Locale;

class ExpressionFactory_createExpressionTest {

    private final ParameterTypeRegistry parameterTypeRegistry = new ParameterTypeRegistry(Locale.getDefault());
    private final ExpressionFactory expressionFactory = new ExpressionFactory(parameterTypeRegistry);

    @Test
    void testCreateExpression_EmptyString() {
        Expression expression = expressionFactory.createExpression("");
        assertTrue(expression instanceof CucumberExpression);
    }

    @Test
    void testCreateExpression_RegularExpressionWithAnchors() {
        Expression expression = expressionFactory.createExpression("^start$");
        assertTrue(expression instanceof RegularExpression);
    }

    @Test
    void testCreateExpression_RegularExpressionWithSlashes() {
        Expression expression = expressionFactory.createExpression("/regex/");
        assertTrue(expression instanceof RegularExpression);
    }

    @Test
    void testCreateExpression_CucumberExpression() {
        Expression expression = expressionFactory.createExpression("some text");
        assertTrue(expression instanceof CucumberExpression);
    }
}
