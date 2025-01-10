
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class FakeValuesService_jsonaTest {

    @Test
    void testJsona_ValidInput() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        String result = fakeValuesService.jsona("3", "field1", "value1", "2", "field2", "value2", "1", "field3", "value3");
        assertNotNull(result);
    }

    @Test
    void testJsona_InvalidInput() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.jsona("1", "field1", "value1", "2", "field2", "value2");
        });
        String expectedMessage = "Total number of field names and field values should be dividable by 3";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void testJsona_NullFieldExpression() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        String result = fakeValuesService.jsona(null, "field1", "value1", "2", "field2", "value2", "1", "field3", "value3");
        assertNotNull(result);
    }
}
