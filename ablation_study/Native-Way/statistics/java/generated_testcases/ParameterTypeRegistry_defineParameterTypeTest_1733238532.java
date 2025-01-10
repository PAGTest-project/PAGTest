
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Locale;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterTypeRegistry_defineParameterTypeTest {
    private ParameterTypeRegistry parameterTypeRegistry;

    @BeforeEach
    public void setUp() {
        parameterTypeRegistry = new ParameterTypeRegistry(Locale.ENGLISH);
    }

    @Test
    public void testDefineParameterTypeWithValidName() {
        ParameterType<String> parameterType = new ParameterType<>("testName", "testRegex", String.class, (String s) -> s, false, false);
        parameterTypeRegistry.defineParameterType(parameterType);
        assertNotNull(parameterTypeRegistry.lookupByTypeName("testName"));
    }

    @Test
    public void testDefineParameterTypeWithDuplicateName() {
        ParameterType<String> parameterType1 = new ParameterType<>("testName", "testRegex", String.class, (String s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("testName", "testRegex", String.class, (String s) -> s, false, false);
        parameterTypeRegistry.defineParameterType(parameterType1);

        final Executable testMethod = () -> parameterTypeRegistry.defineParameterType(parameterType2);
        assertThrows(DuplicateTypeNameException.class, testMethod);
    }

    @Test
    public void testDefineParameterTypeWithAnonymousName() {
        ParameterType<String> parameterType = new ParameterType<>("", "testRegex", String.class, (String s) -> s, false, false);
        parameterTypeRegistry.defineParameterType(parameterType);
        assertNull(parameterTypeRegistry.lookupByTypeName(""));
    }

    @Test
    public void testDefineParameterTypeWithPreferentialConflict() {
        ParameterType<String> parameterType1 = new ParameterType<>("testName1", "testRegex", String.class, (String s) -> s, false, true);
        ParameterType<String> parameterType2 = new ParameterType<>("testName2", "testRegex", String.class, (String s) -> s, false, true);
        parameterTypeRegistry.defineParameterType(parameterType1);

        final Executable testMethod = () -> parameterTypeRegistry.defineParameterType(parameterType2);
        assertThrows(CucumberExpressionException.class, testMethod);
    }

    @Test
    public void testDefineParameterTypeWithNonPreferentialConflict() {
        ParameterType<String> parameterType1 = new ParameterType<>("testName1", "testRegex", String.class, (String s) -> s, false, false);
        ParameterType<String> parameterType2 = new ParameterType<>("testName2", "testRegex", String.class, (String s) -> s, false, false);
        parameterTypeRegistry.defineParameterType(parameterType1);
        parameterTypeRegistry.defineParameterType(parameterType2);
        assertNotNull(parameterTypeRegistry.lookupByRegexp("testRegex", Pattern.compile("testRegex"), "testText"));
    }
}
