
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

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
    public void testDefineParameterTypeWithDuplicateName() {
        ParameterType<String> parameterType1 = new ParameterType<>("name", ".*", String.class, (String s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("name", ".*", String.class, (String s) -> s, false, false);

        registry.defineParameterType(parameterType1);

        assertThrows(DuplicateTypeNameException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                registry.defineParameterType(parameterType2);
            }
        });
    }

    @Test
    public void testDefineParameterTypeWithDuplicateAnonymousName() {
        ParameterType<String> parameterType1 = new ParameterType<>("", ".*", String.class, (String s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("", ".*", String.class, (String s) -> s, false, false);

        registry.defineParameterType(parameterType1);

        assertThrows(DuplicateTypeNameException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                registry.defineParameterType(parameterType2);
            }
        });
    }

    @Test
    public void testDefineParameterTypeWithPreferentialConflict() {
        ParameterType<String> parameterType1 = new ParameterType<>("name1", ".*", String.class, (String s) -> s, false, true);
        ParameterType<String> parameterType2 = new ParameterType<>("name2", ".*", String.class, (String s) -> s, false, true);

        registry.defineParameterType(parameterType1);

        assertThrows(CucumberExpressionException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                registry.defineParameterType(parameterType2);
            }
        });
    }

    @Test
    public void testDefineParameterTypeSuccess() {
        ParameterType<String> parameterType = new ParameterType<>("name", ".*", String.class, (String s) -> s, false, false);

        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                registry.defineParameterType(parameterType);
            }
        });
    }
}
