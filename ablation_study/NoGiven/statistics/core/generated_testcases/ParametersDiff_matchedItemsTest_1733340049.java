
package org.openapitools.openapidiff.core.compare;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParametersDiff_matchedItemsTest {

    @Test
    public void testMatchedItems_SingleMatch() {
        ParametersDiff parametersDiff = new ParametersDiff(null);
        String string = "/path/{param1}";
        String regex = "\\{([^/]+)}";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("param1");

        ArrayList<String> result = parametersDiff.matchedItems(string, regex);
        assertEquals(expected, result);
    }

    @Test
    public void testMatchedItems_MultipleMatches() {
        ParametersDiff parametersDiff = new ParametersDiff(null);
        String string = "/path/{param1}/another/{param2}";
        String regex = "\\{([^/]+)}";
        ArrayList<String> expected = new ArrayList<>();
        expected.add("param1");
        expected.add("param2");

        ArrayList<String> result = parametersDiff.matchedItems(string, regex);
        assertEquals(expected, result);
    }

    @Test
    public void testMatchedItems_NoMatch() {
        ParametersDiff parametersDiff = new ParametersDiff(null);
        String string = "/path/noParams";
        String regex = "\\{([^/]+)}";
        ArrayList<String> expected = new ArrayList<>();

        ArrayList<String> result = parametersDiff.matchedItems(string, regex);
        assertEquals(expected, result);
    }
}
