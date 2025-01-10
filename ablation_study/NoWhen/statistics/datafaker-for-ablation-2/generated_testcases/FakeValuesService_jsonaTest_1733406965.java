
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static net.datafaker.transformations.Field.field;
import java.util.Arrays;

class FakeValuesService_jsonaTest {

    @Test
    void testJsona_validInput() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        String result = fakeValuesService.jsona("3", "name", "John", "2", "age", "30", "1", "isActive", "true");
        assertNotNull(result);
        assertTrue(result.contains("John"));
        assertTrue(result.contains("30"));
        assertTrue(result.contains("true"));
    }

    @Test
    void testJsona_invalidInput() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.jsona("1", "name", "John", "2", "age", "30");
        });
        String expectedMessage = "Total number of field names and field values should be dividable by 3";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testJsona_nullFieldExpression() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        String result = fakeValuesService.jsona("0", "name", null, "2", "age", "30", "1", "isActive", "true");
        assertNotNull(result);
        assertTrue(result.contains("null"));
        assertTrue(result.contains("30"));
        assertTrue(result.contains("true"));
    }
}
