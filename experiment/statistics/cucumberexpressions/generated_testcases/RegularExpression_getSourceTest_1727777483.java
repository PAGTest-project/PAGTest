
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularExpression_getSourceTest {

    @Test
    public void testGetSource() {
        Pattern pattern = Pattern.compile("\\d+");
        ParameterTypeRegistry parameterTypeRegistry = new ParameterTypeRegistry();
        RegularExpression regularExpression = new RegularExpression(pattern, parameterTypeRegistry);

        String source = regularExpression.getSource();

        assertEquals("\\d+", source);
    }
}
