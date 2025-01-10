
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_compareToTest {

    private ParameterType<String> paramTypePrefer;
    private ParameterType<String> paramTypeNotPrefer;
    private ParameterType<String> paramTypeNullName;
    private ParameterType<String> paramTypeEmptyName;

    @BeforeEach
    public void setUp() {
        paramTypePrefer = new ParameterType<>("name1", "regexp1", String.class, (String arg) -> arg, true, true);
        paramTypeNotPrefer = new ParameterType<>("name2", "regexp2", String.class, (String arg) -> arg, true, false);
        paramTypeNullName = new ParameterType<>(null, "regexp3", String.class, (String arg) -> arg, true, false);
        paramTypeEmptyName = new ParameterType<>("", "regexp4", String.class, (String arg) -> arg, true, false);
    }

    @Test
    public void testCompareToPreferForRegexpMatch() {
        assertEquals(-1, paramTypePrefer.compareTo(paramTypeNotPrefer));
    }

    @Test
    public void testCompareToNotPreferForRegexpMatch() {
        assertEquals(1, paramTypeNotPrefer.compareTo(paramTypePrefer));
    }

    @Test
    public void testCompareToBothPreferForRegexpMatch() {
        ParameterType<String> paramTypePrefer2 = new ParameterType<>("name3", "regexp5", String.class, (String arg) -> arg, true, true);
        assertEquals(0, paramTypePrefer.compareTo(paramTypePrefer2));
    }

    @Test
    public void testCompareToNullName() {
        assertEquals(0, paramTypeNullName.compareTo(paramTypeEmptyName));
    }

    @Test
    public void testCompareToEmptyName() {
        assertEquals(0, paramTypeEmptyName.compareTo(paramTypeNullName));
    }

    @Test
    public void testCompareToNameOrder() {
        ParameterType<String> paramTypeNameOrder = new ParameterType<>("name0", "regexp6", String.class, (String arg) -> arg, true, false);
        assertEquals(-1, paramTypeNameOrder.compareTo(paramTypeNotPrefer));
    }
}
