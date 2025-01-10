
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_jsonTest {

    @Test
    public void testJson_evenNumberOfFields() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        String result = fakeValuesService.json("name", "John", "age", "30");
        assertNotNull(result);
    }

    @Test
    public void testJson_oddNumberOfFields() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.json("name", "John", "age");
        });
        assertTrue(exception.getMessage().contains("even"));
    }
}
