
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterType_compareToTest {

    @Test
    public void testCompareTo_preferForRegexpMatch() {
        ParameterType<String> paramType1 = new ParameterType<>("name1", "regexp1", String.class, (CaptureGroupTransformer<String>) s -> s, true, true);
        ParameterType<String> paramType2 = new ParameterType<>("name2", "regexp2", String.class, (CaptureGroupTransformer<String>) s -> s, true, false);

        assertEquals(-1, paramType1.compareTo(paramType2));
        assertEquals(1, paramType2.compareTo(paramType1));
    }

    @Test
    public void testCompareTo_nameComparison() {
        ParameterType<String> paramType1 = new ParameterType<>("name1", "regexp1", String.class, (CaptureGroupTransformer<String>) s -> s, false, false);
        ParameterType<String> paramType2 = new ParameterType<>("name2", "regexp2", String.class, (CaptureGroupTransformer<String>) s -> s, false, false);

        assertEquals(-1, paramType1.compareTo(paramType2));
        assertEquals(1, paramType2.compareTo(paramType1));
    }

    @Test
    public void testCompareTo_nullName() {
        ParameterType<String> paramType1 = new ParameterType<>(null, "regexp1", String.class, (CaptureGroupTransformer<String>) s -> s, false, false);
        ParameterType<String> paramType2 = new ParameterType<>("name2", "regexp2", String.class, (CaptureGroupTransformer<String>) s -> s, false, false);

        assertEquals(-1, paramType1.compareTo(paramType2));
        assertEquals(1, paramType2.compareTo(paramType1));
    }
}
