
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterType_compareToTest {

    private ParameterType<String> paramType1;
    private ParameterType<String> paramType2;
    private ParameterType<String> paramType3;
    private ParameterType<String> paramType4;

    @BeforeEach
    public void setUp() {
        paramType1 = new ParameterType<>("name1", "regexp1", String.class, (String arg) -> arg, false, true);
        paramType2 = new ParameterType<>("name2", "regexp2", String.class, (String arg) -> arg, false, false);
        paramType3 = new ParameterType<>("name3", "regexp3", String.class, (String arg) -> arg, false, true);
        paramType4 = new ParameterType<>("name4", "regexp4", String.class, (String arg) -> arg, false, false);
    }

    @Test
    public void testCompareTo_PreferForRegexpMatch_True() {
        assertTrue(paramType1.compareTo(paramType2) < 0);
    }

    @Test
    public void testCompareTo_PreferForRegexpMatch_False() {
        assertTrue(paramType2.compareTo(paramType1) > 0);
    }

    @Test
    public void testCompareTo_SamePreferForRegexpMatch_DifferentNames() {
        assertTrue(paramType1.compareTo(paramType3) < 0);
    }

    @Test
    public void testCompareTo_SamePreferForRegexpMatch_SameNames() {
        ParameterType<String> paramType5 = new ParameterType<>("name1", "regexp5", String.class, (String arg) -> arg, false, true);
        assertEquals(0, paramType1.compareTo(paramType5));
    }

    @Test
    public void testCompareTo_NullNames() {
        ParameterType<String> paramType6 = new ParameterType<>(null, "regexp6", String.class, (String arg) -> arg, false, true);
        ParameterType<String> paramType7 = new ParameterType<>(null, "regexp7", String.class, (String arg) -> arg, false, false);
        assertTrue(paramType6.compareTo(paramType7) < 0);
    }
}
