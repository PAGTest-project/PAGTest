
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularExpression_getRegexpTest {

    @Test
    public void testGetRegexp() {
        Pattern expectedPattern = Pattern.compile("\\d+");
        RegularExpression regularExpression = new RegularExpression(expectedPattern, new ParameterTypeRegistry());
        assertEquals(expectedPattern, regularExpression.getRegexp());
    }
}
