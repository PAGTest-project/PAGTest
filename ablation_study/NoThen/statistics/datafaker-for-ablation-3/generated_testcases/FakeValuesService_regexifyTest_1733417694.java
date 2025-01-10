
package net.datafaker.service;

import com.github.curiousoddman.rgxgen.RgxGen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FakeValuesService_regexifyTest {
    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, new RandomService());
    }

    @Test
    public void testRegexifyWithExistingRegex() {
        String regex = "[a-z]{5}";
        String result = fakeValuesService.regexify(regex, context);
        assertNotNull(result);
        assertEquals(5, result.length());
    }

    @Test
    public void testRegexifyWithNewRegex() {
        String regex = "[0-9]{3}";
        String result = fakeValuesService.regexify(regex, context);
        assertNotNull(result);
        assertEquals(3, result.length());
    }

    @Test
    public void testRegexifyWithEmptyRegex() {
        String regex = "";
        String result = fakeValuesService.regexify(regex, context);
        assertNotNull(result);
        assertEquals(0, result.length());
    }
}
