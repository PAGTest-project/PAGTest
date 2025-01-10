
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionFactory_createExpressionTest {

    private ExpressionFactory expressionFactory;
    private ParameterTypeRegistry parameterTypeRegistry;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(null);
        expressionFactory = new ExpressionFactory(parameterTypeRegistry);
    }

    @Test
    public void testCreateExpression_EmptyString() {
        Expression expression = expressionFactory.createExpression("");
        assertTrue(expression instanceof CucumberExpression);
    }

    @Test
    public void testCreateExpression_RegularExpressionWithAnchors() {
        Expression expression = expressionFactory.createExpression("^start$");
        assertTrue(expression instanceof RegularExpression);
    }

    @Test
    public void testCreateExpression_RegularExpressionWithSlashes() {
        Expression expression = expressionFactory.createExpression("/regex/");
        assertTrue(expression instanceof RegularExpression);
    }

    @Test
    public void testCreateExpression_CucumberExpression() {
        Expression expression = expressionFactory.createExpression("some text");
        assertTrue(expression instanceof CucumberExpression);
    }
}
