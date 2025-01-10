
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class RegularExpression_matchTest {

    private RegularExpression regularExpression;
    private ParameterTypeRegistry parameterTypeRegistry;
    private TreeRegexp treeRegexp;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = mock(ParameterTypeRegistry.class);
        treeRegexp = mock(TreeRegexp.class);
        regularExpression = new RegularExpression(Pattern.compile("somePattern"), parameterTypeRegistry);
    }

    @Test
    public void testMatch_NoMatch() {
        when(treeRegexp.match(anyString())).thenReturn(null);
        List<Argument<?>> result = regularExpression.match("someText");
        assertNull(result);
    }

    @Test
    public void testMatch_WithMatch() {
        Group group = mock(Group.class);
        GroupBuilder groupBuilder = mock(GroupBuilder.class);
        ParameterType<?> parameterType = mock(ParameterType.class);
        ParameterByTypeTransformer defaultTransformer = mock(ParameterByTypeTransformer.class);

        when(treeRegexp.match(anyString())).thenReturn(group);
        when(treeRegexp.getGroupBuilder()).thenReturn(groupBuilder);
        when(groupBuilder.getChildren()).thenReturn(List.of(groupBuilder));
        when(groupBuilder.getSource()).thenReturn("someRegexp");
        when(parameterTypeRegistry.lookupByRegexp(anyString(), any(Pattern.class), anyString())).thenReturn(parameterType);
        when(parameterTypeRegistry.getDefaultParameterTransformer()).thenReturn(defaultTransformer);
        when(parameterType.isAnonymous()).thenReturn(false);

        List<Argument<?>> result = regularExpression.match("someText");
        assertNotNull(result);
    }
}
