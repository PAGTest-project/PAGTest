
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class RegularExpression_matchTest {

    private RegularExpression regularExpression;
    private ParameterTypeRegistry parameterTypeRegistry;
    private TreeRegexp treeRegexp;
    private Group group;
    private GroupBuilder groupBuilder;
    private ParameterType<?> parameterType;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = Mockito.mock(ParameterTypeRegistry.class);
        treeRegexp = Mockito.mock(TreeRegexp.class);
        group = Mockito.mock(Group.class);
        groupBuilder = Mockito.mock(GroupBuilder.class);
        parameterType = Mockito.mock(ParameterType.class);

        when(treeRegexp.match("validText")).thenReturn(group);
        when(treeRegexp.getGroupBuilder()).thenReturn(groupBuilder);
        when(groupBuilder.getChildren()).thenReturn(List.of(groupBuilder));
        when(groupBuilder.getSource()).thenReturn("parameterTypeRegexp");
        when(parameterTypeRegistry.lookupByRegexp("parameterTypeRegexp", Pattern.compile(".*"), "validText")).thenReturn(parameterType);
        when(parameterType.isAnonymous()).thenReturn(false);

        regularExpression = new RegularExpression(Pattern.compile(".*"), parameterTypeRegistry);
    }

    @Test
    public void testMatch_ValidText() {
        List<Argument<?>> result = regularExpression.match("validText", String.class);
        assertNotNull(result);
    }

    @Test
    public void testMatch_NullGroup() {
        when(treeRegexp.match("invalidText")).thenReturn(null);
        regularExpression = new RegularExpression(Pattern.compile(".*"), parameterTypeRegistry);
        List<Argument<?>> result = regularExpression.match("invalidText", String.class);
        assertNull(result);
    }
}
