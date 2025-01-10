
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class CucumberExpression_matchTest {

    @Test
    public void testMatch_NoMatch() {
        // Given
        TreeRegexp treeRegexp = Mockito.mock(TreeRegexp.class);
        when(treeRegexp.match(Mockito.anyString())).thenReturn(null);

        ParameterTypeRegistry parameterTypeRegistry = Mockito.mock(ParameterTypeRegistry.class);
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
    public void testMatch_WithMatch() {
        // Given
        Group group = Mockito.mock(Group.class);
        TreeRegexp treeRegexp = Mockito.mock(TreeRegexp.class);
        when(treeRegexp.match(Mockito.anyString())).thenReturn(group);

        ParameterTypeRegistry parameterTypeRegistry = Mockito.mock(ParameterTypeRegistry.class);
        ParameterType<?> parameterType = Mockito.mock(ParameterType.class);
        when(parameterType.isAnonymous()).thenReturn(false);
        when(parameterTypeRegistry.lookupByTypeName(Mockito.anyString())).thenReturn(parameterType);

        CucumberExpression cucumberExpression = new CucumberExpression("expression", parameterTypeRegistry) {
            @Override
            TreeRegexp getTreeRegexp() {
                return treeRegexp;
            }
        };

        // When
        List<Argument<?>> result = cucumberExpression.match("text");

        // Then
        assertNotNull(result);
    }
}
