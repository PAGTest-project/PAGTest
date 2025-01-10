
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(-1, paramType1.compareTo(paramType2));
    }

    @Test
    public void testCompareTo_PreferForRegexpMatch_False() {
        assertEquals(1, paramType2.compareTo(paramType1));
    }

    @Test
    public void testCompareTo_SamePreferForRegexpMatch_DifferentNames() {
        assertEquals(-1, paramType1.compareTo(paramType3));
    }

    @Test
    public void testCompareTo_SamePreferForRegexpMatch_SameNames() {
        assertEquals(0, paramType1.compareTo(paramType1));
    }

    @Test
    public void testCompareTo_DifferentPreferForRegexpMatch_SameNames() {
        assertEquals(1, paramType2.compareTo(paramType4));
    }
}
