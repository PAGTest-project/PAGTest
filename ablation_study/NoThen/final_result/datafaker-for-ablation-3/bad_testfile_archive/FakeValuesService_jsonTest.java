
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_jsonTest {
    private FakeValuesService fakeValuesService;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
    }

    @Test
    public void testJsonWithEvenFields() {
        String[] fieldExpressions = {"name", "John", "age", "30"};
        String result = fakeValuesService.json(fieldExpressions);
        assertNotNull(result);
        assertTrue(result.contains("name") && result.contains("age"));
    }

    @Test
    public void testJsonWithOddFields() {
        String[] fieldExpressions = {"name", "John", "age"};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.json(fieldExpressions);
        });
        String expectedMessage = "Total number of field names and field values should be even";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testJsonWithEmptyFields() {
        String[] fieldExpressions = {};
        String result = fakeValuesService.json(fieldExpressions);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
