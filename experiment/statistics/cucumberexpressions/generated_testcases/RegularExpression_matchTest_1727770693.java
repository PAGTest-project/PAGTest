
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
    private Group group;
    private GroupBuilder groupBuilder;
    private ParameterByTypeTransformer defaultTransformer;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = mock(ParameterTypeRegistry.class);
        treeRegexp = mock(TreeRegexp.class);
        group = mock(Group.class);
        groupBuilder = mock(GroupBuilder.class);
        defaultTransformer = mock(ParameterByTypeTransformer.class);

        when(parameterTypeRegistry.getDefaultParameterTransformer()).thenReturn(defaultTransformer);
        when(treeRegexp.match(anyString())).thenReturn(group);
        when(treeRegexp.getGroupBuilder()).thenReturn(groupBuilder);
        when(groupBuilder.getChildren()).thenReturn(List.of(groupBuilder));
        when(groupBuilder.getSource()).thenReturn("source");

        regularExpression = new RegularExpression(Pattern.compile(".*"), parameterTypeRegistry);
    }

    @Test
    public void testMatch_GroupIsNull() {
        when(treeRegexp.match(anyString())).thenReturn(null);
        List<Argument<?>> result = regularExpression.match("text");
        assertNull(result);
    }

    @Test
    public void testMatch_GroupIsNotNull() {
        ParameterType<?> parameterType = mock(ParameterType.class);
        when(parameterTypeRegistry.lookupByRegexp(anyString(), any(Pattern.class), anyString())).thenReturn(parameterType);
        when(parameterType.isAnonymous()).thenReturn(false);

        List<Argument<?>> result = regularExpression.match("text");
        assertNotNull(result);
    }
}
