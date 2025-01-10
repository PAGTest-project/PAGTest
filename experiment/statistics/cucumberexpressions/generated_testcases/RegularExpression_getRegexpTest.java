
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularExpression_getRegexpTest {

    @Test
    public void testGetRegexp() {
        Pattern expectedPattern = Pattern.compile("\\d+");
        ParameterTypeRegistry parameterTypeRegistry = new ParameterTypeRegistry(java.util.Locale.getDefault());
        RegularExpression regularExpression = new RegularExpression(expectedPattern, parameterTypeRegistry);
        assertEquals(expectedPattern, regularExpression.getRegexp());
    }
}
