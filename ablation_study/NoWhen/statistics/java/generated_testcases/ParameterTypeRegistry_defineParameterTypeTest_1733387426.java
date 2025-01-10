
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterTypeRegistry_defineParameterTypeTest {
    private ParameterTypeRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new ParameterTypeRegistry(Locale.ENGLISH);
    }

    @Test
    public void testDefineParameterTypeWithUniqueName() {
        ParameterType<String> parameterType = new ParameterType<>("uniqueName", "uniqueRegex", String.class, (String s) -> s, false, false);
        registry.defineParameterType(parameterType);
        assertNotNull(registry.lookupByTypeName("uniqueName"));
    }

    @Test
    public void testDefineParameterTypeWithDuplicateName() {
        ParameterType<String> parameterType1 = new ParameterType<>("duplicateName", "duplicateRegex1", String.class, (String s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("duplicateName", "duplicateRegex2", String.class, (String s) -> s, false, false);
        registry.defineParameterType(parameterType1);
        assertThrows(DuplicateTypeNameException.class, () -> registry.defineParameterType(parameterType2));
    }

    @Test
    public void testDefineParameterTypeWithAnonymousName() {
        ParameterType<String> parameterType = new ParameterType<>("", "anonymousRegex", String.class, (String s) -> s, false, false);
        registry.defineParameterType(parameterType);
        assertNull(registry.lookupByTypeName(""));
    }

    @Test
    public void testDefineParameterTypeWithPreferentialRegexpMatch() {
        ParameterType<String> parameterType1 = new ParameterType<>("name1", "preferentialRegex", String.class, (String s) -> s, false, true);
        ParameterType<String> parameterType2 = new ParameterType<>("name2", "preferentialRegex", String.class, (String s) -> s, false, true);
        registry.defineParameterType(parameterType1);
        assertThrows(CucumberExpressionException.class, () -> registry.defineParameterType(parameterType2));
    }

    @Test
    public void testDefineParameterTypeWithNonPreferentialRegexpMatch() {
        ParameterType<String> parameterType1 = new ParameterType<>("name1", "nonPreferentialRegex", String.class, (String s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("name2", "nonPreferentialRegex", String.class, (String s) -> s, false, false);
        registry.defineParameterType(parameterType1);
        registry.defineParameterType(parameterType2);
        assertNotNull(registry.lookupByRegexp("nonPreferentialRegex", Pattern.compile("nonPreferentialRegex"), "test"));
    }
}
