
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_compareToTest {

    private ParameterType<String> parameterType1;
    private ParameterType<String> parameterType2;
    private ParameterType<String> parameterType3;
    private ParameterType<String> parameterType4;

    @BeforeEach
    public void setUp() {
        parameterType1 = new ParameterType<>("name1", "regexp1", String.class, (String arg) -> arg, false, true);
        parameterType2 = new ParameterType<>("name2", "regexp2", String.class, (String arg) -> arg, false, false);
        parameterType3 = new ParameterType<>("name3", "regexp3", String.class, (String arg) -> arg, true, false);
        parameterType4 = new ParameterType<>("name4", "regexp4", String.class, (String arg) -> arg, true, true);
    }

    @Test
    public void testCompareTo_PreferForRegexpMatch_True() {
        assertEquals(-1, parameterType3.compareTo(parameterType2));
    }

    @Test
    public void testCompareTo_PreferForRegexpMatch_False() {
        assertEquals(1, parameterType2.compareTo(parameterType3));
    }

    @Test
    public void testCompareTo_SamePreferForRegexpMatch_DifferentNames() {
        assertEquals(-1, parameterType1.compareTo(parameterType2));
    }

    @Test
    public void testCompareTo_SamePreferForRegexpMatch_SameNames() {
        assertEquals(0, parameterType1.compareTo(parameterType1));
    }

    @Test
    public void testCompareTo_NullName() {
        ParameterType<String> nullNameParameterType = new ParameterType<>(null, "regexp", String.class, (String arg) -> arg, false, false);
        assertEquals(-1, nullNameParameterType.compareTo(parameterType1));
    }
}
