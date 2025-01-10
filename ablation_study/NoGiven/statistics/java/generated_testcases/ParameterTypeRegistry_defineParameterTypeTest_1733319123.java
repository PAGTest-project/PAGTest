
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Collections;
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
    public void testDefineParameterType_DuplicateName_ThrowsException() {
        ParameterType<String> parameterType1 = new ParameterType<>("name", Collections.singletonList("\\w+"), String.class, (Transformer<String>) s -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("name", Collections.singletonList("\\w+"), String.class, (Transformer<String>) s -> s, false, false);

        registry.defineParameterType(parameterType1);

        final Executable testMethod = () -> registry.defineParameterType(parameterType2);

        assertThrows(DuplicateTypeNameException.class, testMethod);
    }

    @Test
    public void testDefineParameterType_AnonymousDuplicateName_ThrowsException() {
        ParameterType<String> parameterType1 = new ParameterType<>("", Collections.singletonList("\\w+"), String.class, (Transformer<String>) s -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("", Collections.singletonList("\\w+"), String.class, (Transformer<String>) s -> s, false, false);

        registry.defineParameterType(parameterType1);

        final Executable testMethod = () -> registry.defineParameterType(parameterType2);

        assertThrows(DuplicateTypeNameException.class, testMethod);
    }

    @Test
    public void testDefineParameterType_PreferentialConflict_ThrowsException() {
        ParameterType<String> parameterType1 = new ParameterType<>("name1", Collections.singletonList("\\w+"), String.class, (Transformer<String>) s -> s, false, true);
        ParameterType<String> parameterType2 = new ParameterType<>("name2", Collections.singletonList("\\w+"), String.class, (Transformer<String>) s -> s, false, true);

        registry.defineParameterType(parameterType1);

        final Executable testMethod = () -> registry.defineParameterType(parameterType2);

        assertThrows(CucumberExpressionException.class, testMethod);
    }

    @Test
    public void testDefineParameterType_Success() {
        ParameterType<String> parameterType = new ParameterType<>("name", Collections.singletonList("\\w+"), String.class, (Transformer<String>) s -> s, false, false);

        assertDoesNotThrow(() -> registry.defineParameterType(parameterType));
        assertEquals(parameterType, registry.lookupByTypeName("name"));
    }
}
