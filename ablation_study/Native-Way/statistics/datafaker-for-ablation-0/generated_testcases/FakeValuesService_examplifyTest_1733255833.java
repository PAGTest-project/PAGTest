
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FakeValuesService_examplifyTest {
    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, new RandomService());
    }

    @Test
    public void testExamplifyWithNullExample() {
        String result = fakeValuesService.examplify(null, context);
        assertNull(result);
    }

    @Test
    public void testExamplifyWithLetters() {
        String example = "abc";
        String result = fakeValuesService.examplify(example, context);
        assertEquals(3, result.length());
        for (char c : result.toCharArray()) {
            assert Character.isLetter(c);
        }
    }

    @Test
    public void testExamplifyWithDigits() {
        String example = "123";
        String result = fakeValuesService.examplify(example, context);
        assertEquals(3, result.length());
        for (char c : result.toCharArray()) {
            assert Character.isDigit(c);
        }
    }

    @Test
    public void testExamplifyWithMixedCharacters() {
        String example = "a1b2c3";
        String result = fakeValuesService.examplify(example, context);
        assertEquals(6, result.length());
        for (int i = 0; i < result.length(); i++) {
            if (i % 2 == 0) {
                assert Character.isLetter(result.charAt(i));
            } else {
                assert Character.isDigit(result.charAt(i));
            }
        }
    }

    @Test
    public void testExamplifyWithSpecialCharacters() {
        String example = "a!b@c#";
        String result = fakeValuesService.examplify(example, context);
        assertEquals("a!b@c#", result);
    }
}
