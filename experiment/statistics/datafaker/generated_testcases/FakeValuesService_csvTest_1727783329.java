
package net.datafaker.service;

import net.datafaker.transformations.CsvTransformer;
import net.datafaker.transformations.Field;
import net.datafaker.transformations.Schema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakeValuesService_csvTest {

    @Test
    public void testCsv_evenColumns() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        String result = fakeValuesService.csv(",", '"', true, 10, "name", "John", "age", "30");
        assertEquals("name,age\nJohn,30\n", result);
    }

    @Test
    public void testCsv_oddColumns() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.csv(",", '"', true, 10, "name", "John", "age");
        });
    }
}
