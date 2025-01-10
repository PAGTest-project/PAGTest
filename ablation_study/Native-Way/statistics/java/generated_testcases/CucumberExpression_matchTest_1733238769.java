
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class CucumberExpression_matchTest {

    private CucumberExpression cucumberExpression;
    private ParameterTypeRegistry parameterTypeRegistry;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(Locale.ENGLISH);
        cucumberExpression = new CucumberExpression("three (.*) mice", parameterTypeRegistry);
    }

    @Test
    public void testMatchSuccess() {
        String text = "three blind mice";
        Type[] typeHints = {String.class};
        List<Argument<?>> arguments = cucumberExpression.match(text, typeHints);
        assertNotNull(arguments);
        assertEquals(1, arguments.size());
        assertEquals("blind", arguments.get(0).getValue());
    }

    @Test
    public void testMatchFailure() {
        String text = "three fast mice";
        Type[] typeHints = {Integer.class};
        List<Argument<?>> arguments = cucumberExpression.match(text, typeHints);
        assertNull(arguments);
    }

    @Test
    public void testMatchWithAnonymousParameterType() {
        String text = "three blind mice";
        Type[] typeHints = {};
        List<Argument<?>> arguments = cucumberExpression.match(text, typeHints);
        assertNotNull(arguments);
        assertEquals(1, arguments.size());
        assertEquals("blind", arguments.get(0).getValue());
    }
}
