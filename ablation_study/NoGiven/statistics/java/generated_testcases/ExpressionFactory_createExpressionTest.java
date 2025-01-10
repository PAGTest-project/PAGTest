
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ExpressionFactory_createExpressionTest {

    private ExpressionFactory expressionFactory;
    private ParameterTypeRegistry parameterTypeRegistry;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(Locale.getDefault());
        expressionFactory = new ExpressionFactory(parameterTypeRegistry);
    }

    @Test
    public void testCreateExpression_EmptyString() {
        Expression expression = expressionFactory.createExpression("");
        assertInstanceOf(CucumberExpression.class, expression);
    }

    @Test
    public void testCreateExpression_RegularExpressionWithAnchors() {
        Expression expression = expressionFactory.createExpression("^start$");
        assertInstanceOf(RegularExpression.class, expression);
        assertTrue(expression.getRegexp().pattern().startsWith("^"));
        assertTrue(expression.getRegexp().pattern().endsWith("$"));
    }

    @Test
    public void testCreateExpression_RegularExpressionWithSlashes() {
        Expression expression = expressionFactory.createExpression("/regex/");
        assertInstanceOf(RegularExpression.class, expression);
        assertTrue(expression.getRegexp().pattern().equals("regex"));
    }

    @Test
    public void testCreateExpression_CucumberExpression() {
        Expression expression = expressionFactory.createExpression("simple expression");
        assertInstanceOf(CucumberExpression.class, expression);
    }
}
