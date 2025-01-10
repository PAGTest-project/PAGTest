
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Text_textTest {

    private Text textProvider;

    @BeforeEach
    public void setUp() {
        textProvider = new Text(new BaseProviders());
    }

    @Test
    void testTextWithMinimumLength() {
        int minimumLength = 5;
        int maximumLength = 10;
        boolean includeUppercase = true;
        boolean includeSpecial = true;
        boolean includeDigit = true;

        String result = textProvider.text(minimumLength, maximumLength, includeUppercase, includeSpecial, includeDigit);
        assertTrue(result.length() >= minimumLength && result.length() <= maximumLength);
    }

    @Test
    void testTextWithoutUppercase() {
        int minimumLength = 5;
        int maximumLength = 10;
        boolean includeUppercase = false;
        boolean includeSpecial = true;
        boolean includeDigit = true;

        String result = textProvider.text(minimumLength, maximumLength, includeUppercase, includeSpecial, includeDigit);
        assertTrue(result.matches("[^A-Z]*"));
    }

    @Test
    void testTextWithoutSpecial() {
        int minimumLength = 5;
        int maximumLength = 10;
        boolean includeUppercase = true;
        boolean includeSpecial = false;
        boolean includeDigit = true;

        String result = textProvider.text(minimumLength, maximumLength, includeUppercase, includeSpecial, includeDigit);
        assertTrue(result.matches("[^@#$%^&*]*"));
    }

    @Test
    void testTextWithoutDigit() {
        int minimumLength = 5;
        int maximumLength = 10;
        boolean includeUppercase = true;
        boolean includeSpecial = true;
        boolean includeDigit = false;

        String result = textProvider.text(minimumLength, maximumLength, includeUppercase, includeSpecial, includeDigit);
        assertTrue(result.matches("[^0-9]*"));
    }

    @Test
    void testTextWithExactLength() {
        int minimumLength = 7;
        int maximumLength = 7;
        boolean includeUppercase = true;
        boolean includeSpecial = true;
        boolean includeDigit = true;

        String result = textProvider.text(minimumLength, maximumLength, includeUppercase, includeSpecial, includeDigit);
        assertEquals(7, result.length());
    }
}
