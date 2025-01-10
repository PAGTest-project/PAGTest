
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Text_textTest {

    private Text text;

    @BeforeEach
    public void setUp() {
        text = new Text(new BaseProviders());
    }

    @Test
    void testTextWithAllOptions() {
        String result = text.text(5, 10, true, true, true);
        assertTrue(result.length() >= 5 && result.length() <= 10);
        assertTrue(result.matches(".*[A-Z].*"));
        assertTrue(result.matches(".*[!@#$%^&*].*"));
        assertTrue(result.matches(".*[0-9].*"));
    }

    @Test
    void testTextWithNoOptions() {
        String result = text.text(5, 10, false, false, false);
        assertTrue(result.length() >= 5 && result.length() <= 10);
        assertTrue(result.matches("^[a-z]*$"));
    }

    @Test
    void testTextWithOnlyUppercase() {
        String result = text.text(5, 10, true, false, false);
        assertTrue(result.length() >= 5 && result.length() <= 10);
        assertTrue(result.matches(".*[A-Z].*"));
    }

    @Test
    void testTextWithOnlySpecial() {
        String result = text.text(5, 10, false, true, false);
        assertTrue(result.length() >= 5 && result.length() <= 10);
        assertTrue(result.matches(".*[!@#$%^&*].*"));
    }

    @Test
    void testTextWithOnlyDigit() {
        String result = text.text(5, 10, false, false, true);
        assertTrue(result.length() >= 5 && result.length() <= 10);
        assertTrue(result.matches(".*[0-9].*"));
    }
}
