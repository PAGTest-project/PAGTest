
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterTypeRegistry_defineParameterTypeTest {
    private ParameterTypeRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new ParameterTypeRegistry(Locale.ENGLISH);
    }

    @Test
    public void testDefineParameterTypeWithDuplicateName() {
        ParameterType<String> parameterType1 = new ParameterType<>("name", ".*", String.class, (s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("name", ".*", String.class, (s) -> s, false, false);

        registry.defineParameterType(parameterType1);
        assertThrows(DuplicateTypeNameException.class, () -> registry.defineParameterType(parameterType2));
    }

    @Test
    public void testDefineParameterTypeWithAnonymousDuplicateName() {
        ParameterType<String> parameterType1 = new ParameterType<>("", ".*", String.class, (s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("", ".*", String.class, (s) -> s, false, false);

        registry.defineParameterType(parameterType1);
        assertThrows(DuplicateTypeNameException.class, () -> registry.defineParameterType(parameterType2));
    }

    @Test
    public void testDefineParameterTypeWithPreferentialConflict() {
        ParameterType<String> parameterType1 = new ParameterType<>("name1", ".*", String.class, (s) -> s, false, true);
        ParameterType<String> parameterType2 = new ParameterType<>("name2", ".*", String.class, (s) -> s, false, true);

        registry.defineParameterType(parameterType1);
        assertThrows(CucumberExpressionException.class, () -> registry.defineParameterType(parameterType2));
    }

    @Test
    public void testDefineParameterTypeSuccess() {
        ParameterType<String> parameterType = new ParameterType<>("name", ".*", String.class, (s) -> s, false, false);
        assertDoesNotThrow(() -> registry.defineParameterType(parameterType));
    }
}
