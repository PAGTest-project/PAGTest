
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Locale;

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
    public void testExamplifyWithLettersOnly() {
        String example = "abc";
        String result = fakeValuesService.examplify(example, context);
        assertNotNull(result);
        assertNotEquals(example, result);
        assertTrue(result.matches("[a-zA-Z]+"));
    }

    @Test
    public void testExamplifyWithDigitsOnly() {
        String example = "123";
        String result = fakeValuesService.examplify(example, context);
        assertNotNull(result);
        assertNotEquals(example, result);
        assertTrue(result.matches("[0-9]+"));
    }

    @Test
    public void testExamplifyWithMixedCharacters() {
        String example = "a1b2c3";
        String result = fakeValuesService.examplify(example, context);
        assertNotNull(result);
        assertNotEquals(example, result);
        assertTrue(result.matches("[a-zA-Z0-9]+"));
    }

    @Test
    public void testExamplifyWithEmptyString() {
        String example = "";
        String result = fakeValuesService.examplify(example, context);
        assertNotNull(result);
        assertEquals(example, result);
    }
}
