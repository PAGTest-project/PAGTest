
package net.datafaker.service;

import net.datafaker.transformations.CsvTransformer;
import net.datafaker.transformations.Field;
import net.datafaker.transformations.Schema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_csvTest {

    @Test
    public void testCsv_evenColumns() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        String result = fakeValuesService.csv(",", '"', true, 10, "name", "John", "age", "30");
        assertNotNull(result);
    }

    @Test
    public void testCsv_oddColumns() {
        FakeValuesService fakeValuesService = new FakeValuesService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.csv(",", '"', true, 10, "name", "John", "age");
        });
        assertTrue(exception.getMessage().contains("Total number of column names and column values should be even"));
    }
}
