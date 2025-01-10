
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class CucumberExpression_matchTest {

    @Test
    public void testMatch_SuccessfulMatch() {
        // Given
        String text = "some text";
        Type[] typeHints = {String.class};

        TreeRegexp treeRegexp = mock(TreeRegexp.class);
        Group group = mock(Group.class);
        ParameterTypeRegistry parameterTypeRegistry = mock(ParameterTypeRegistry.class);
        ParameterByTypeTransformer defaultTransformer = mock(ParameterByTypeTransformer.class);
        ParameterType<?> parameterType = mock(ParameterType.class);

        when(treeRegexp.match(text)).thenReturn(group);
        when(parameterTypeRegistry.getDefaultParameterTransformer()).thenReturn(defaultTransformer);
        when(parameterType.isAnonymous()).thenReturn(false);

        CucumberExpression cucumberExpression = spy(new CucumberExpression("expression", parameterTypeRegistry));
        doReturn(treeRegexp).when(cucumberExpression).treeRegexp;
        doReturn(parameterType).when(cucumberExpression).parameterTypes.get(0);

        // When
        List<Argument<?>> result = cucumberExpression.match(text, typeHints);

        // Then
        assertNotNull(result);
    }

    @Test
    public void testMatch_NoMatch() {
        // Given
        String text = "some text";
        Type[] typeHints = {String.class};

        TreeRegexp treeRegexp = mock(TreeRegexp.class);
        ParameterTypeRegistry parameterTypeRegistry = mock(ParameterTypeRegistry.class);

        when(treeRegexp.match(text)).thenReturn(null);

        CucumberExpression cucumberExpression = spy(new CucumberExpression("expression", parameterTypeRegistry));
        doReturn(treeRegexp).when(cucumberExpression).treeRegexp;

        // When
        List<Argument<?>> result = cucumberExpression.match(text, typeHints);

        // Then
        assertNull(result);
    }
}
