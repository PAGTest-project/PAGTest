
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Text_textTest {

    private Text textProvider;

    @BeforeEach
    public void setUp() {
        textProvider = new Text(new BaseProviders());
    }

    @Test
    void testTextWithMinimumLength() {
        String result = textProvider.text(5, 10, false, false, false);
        assertNotNull(result);
        assertTrue(result.length() >= 5 && result.length() <= 10);
    }

    @Test
    void testTextWithMaximumLength() {
        String result = textProvider.text(15, 20, false, false, false);
        assertNotNull(result);
        assertTrue(result.length() >= 15 && result.length() <= 20);
    }

    @Test
    void testTextWithUppercase() {
        String result = textProvider.text(10, 10, true, false, false);
        assertNotNull(result);
        assertTrue(result.length() == 10);
        assertTrue(result.matches(".*[A-Z].*"));
    }

    @Test
    void testTextWithSpecialCharacters() {
        String result = textProvider.text(10, 10, false, true, false);
        assertNotNull(result);
        assertTrue(result.length() == 10);
        assertTrue(result.matches(".*[!@#$%^&*].*"));
    }

    @Test
    void testTextWithDigits() {
        String result = textProvider.text(10, 10, false, false, true);
        assertNotNull(result);
        assertTrue(result.length() == 10);
        assertTrue(result.matches(".*[0-9].*"));
    }

    @Test
    void testTextWithAllOptions() {
        String result = textProvider.text(15, 15, true, true, true);
        assertNotNull(result);
        assertTrue(result.length() == 15);
        assertTrue(result.matches(".*[A-Z].*"));
        assertTrue(result.matches(".*[!@#$%^&*].*"));
        assertTrue(result.matches(".*[0-9].*"));
    }

    @Test
    void testTextWithNoOptions() {
        String result = textProvider.text(10, 10, false, false, false);
        assertNotNull(result);
        assertTrue(result.length() == 10);
        assertFalse(result.matches(".*[A-Z].*"));
        assertFalse(result.matches(".*[!@#$%^&*].*"));
        assertFalse(result.matches(".*[0-9].*"));
    }
}
