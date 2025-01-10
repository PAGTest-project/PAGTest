
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class CucumberExpression_matchTest {

    @Test
    public void testMatch_ReturnsNullWhenGroupIsNull() {
        // Given
        TreeRegexp treeRegexp = mock(TreeRegexp.class);
        when(treeRegexp.match(anyString())).thenReturn(null);

        ParameterTypeRegistry parameterTypeRegistry = mock(ParameterTypeRegistry.class);
        CucumberExpression cucumberExpression = new CucumberExpression("expression", parameterTypeRegistry) {
            @Override
            TreeRegexp getTreeRegexp() {
                return treeRegexp;
            }
        };

        // When
        List<Argument<?>> result = cucumberExpression.match("text");

        // Then
        assertNull(result);
    }

    @Test
    public void testMatch_ReturnsArgumentsWhenGroupIsNotNull() {
        // Given
        Group group = mock(Group.class);
        TreeRegexp treeRegexp = mock(TreeRegexp.class);
        when(treeRegexp.match(anyString())).thenReturn(group);

        ParameterTypeRegistry parameterTypeRegistry = mock(ParameterTypeRegistry.class);
        ParameterType<?> parameterType = mock(ParameterType.class);
        when(parameterType.isAnonymous()).thenReturn(false);
        when(parameterTypeRegistry.getDefaultParameterTransformer()).thenReturn(mock(ParameterByTypeTransformer.class));

        CucumberExpression cucumberExpression = new CucumberExpression("expression", parameterTypeRegistry) {
            @Override
            TreeRegexp getTreeRegexp() {
                return treeRegexp;
            }

            @Override
            List<ParameterType<?>> getParameterTypes() {
                return List.of(parameterType);
            }
        };

        // When
        List<Argument<?>> result = cucumberExpression.match("text");

        // Then
        assertNotNull(result);
    }
}
