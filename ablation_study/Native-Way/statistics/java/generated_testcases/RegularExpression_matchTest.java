
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

public class RegularExpression_matchTest {
    private RegularExpression regularExpression;
    private ParameterTypeRegistry parameterTypeRegistry;

    @BeforeEach
    public void setUp() {
        Pattern pattern = Pattern.compile("\\d+");
        parameterTypeRegistry = new ParameterTypeRegistry(java.util.Locale.getDefault());
        regularExpression = new RegularExpression(pattern, parameterTypeRegistry);
    }

    @Test
    public void testMatchWithValidText() {
        String text = "123";
        assertNotNull(regularExpression.match(text));
    }

    @Test
    public void testMatchWithInvalidText() {
        String text = "abc";
        assertNull(regularExpression.match(text));
    }

    @Test
    public void testMatchWithTypeHint() {
        String text = "123";
        assertNotNull(regularExpression.match(text, Integer.class));
    }

    @Test
    public void testMatchWithMultipleTypeHints() {
        String text = "123";
        assertNotNull(regularExpression.match(text, Integer.class, String.class));
    }

    @Test
    public void testMatchWithNoTypeHints() {
        String text = "123";
        assertNotNull(regularExpression.match(text));
    }

    @Test
    public void testMatchWithEmptyText() {
        String text = "";
        assertNull(regularExpression.match(text));
    }

    @Test
    public void testMatchWithNullText() {
        String text = null;
        assertThrows(NullPointerException.class, () -> regularExpression.match(text));
    }
}
