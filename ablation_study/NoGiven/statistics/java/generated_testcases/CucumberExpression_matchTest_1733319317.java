
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class CucumberExpression_matchTest {

    private CucumberExpression cucumberExpression;
    private ParameterTypeRegistry parameterTypeRegistry;
    private TreeRegexp treeRegexp;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = mock(ParameterTypeRegistry.class);
        treeRegexp = mock(TreeRegexp.class);
        cucumberExpression = new CucumberExpression("expression", parameterTypeRegistry) {
            @Override
            TreeRegexp treeRegexp() {
                return treeRegexp;
            }
        };
    }

    @Test
    public void testMatch_NoMatch() {
        when(treeRegexp.match(anyString())).thenReturn(null);

        List<Argument<?>> result = cucumberExpression.match("text");

        assertNull(result);
    }

    @Test
    public void testMatch_WithMatch() {
        Group group = mock(Group.class);
        ParameterType<?> parameterType = mock(ParameterType.class);
        ParameterByTypeTransformer transformer = mock(ParameterByTypeTransformer.class);

        when(treeRegexp.match(anyString())).thenReturn(group);
        when(parameterTypeRegistry.getDefaultParameterTransformer()).thenReturn(transformer);
        when(parameterType.isAnonymous()).thenReturn(true);
        when(parameterType.deAnonymize(any(), any())).thenReturn(parameterType);

        cucumberExpression.parameterTypes.add(parameterType);

        List<Argument<?>> result = cucumberExpression.match("text");

        assertNotNull(result);
    }
}
