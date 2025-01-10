
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_jsonaTest {
    private FakeValuesService fakeValuesService;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
    }

    @Test
    public void testJsonaValidInput() {
        String[] fieldExpressions = {"3", "name", "John", "2", "age", "30", "1", "city", "New York"};
        String result = fakeValuesService.jsona(fieldExpressions);
        assertNotNull(result);
        assertTrue(result.contains("name"));
        assertTrue(result.contains("age"));
        assertTrue(result.contains("city"));
    }

    @Test
    public void testJsonaInvalidInputLength() {
        String[] fieldExpressions = {"3", "name", "John", "2", "age", "30"};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.jsona(fieldExpressions);
        });
        assertTrue(exception.getMessage().contains("Total number of field names and field values should be dividable by 3"));
    }

    @Test
    public void testJsonaNullFieldExpression() {
        String[] fieldExpressions = {"3", "name", "John", "2", "age", "30", "1", null, "New York"};
        String result = fakeValuesService.jsona(fieldExpressions);
        assertNotNull(result);
        assertTrue(result.contains("name"));
        assertTrue(result.contains("age"));
        assertFalse(result.contains("city"));
    }

    @Test
    public void testJsonaNonPositiveIntegerFieldExpression() {
        String[] fieldExpressions = {"0", "name", "John", "2", "age", "30", "1", "city", "New York"};
        String result = fakeValuesService.jsona(fieldExpressions);
        assertNotNull(result);
        assertFalse(result.contains("name"));
        assertTrue(result.contains("age"));
        assertTrue(result.contains("city"));
    }
}
