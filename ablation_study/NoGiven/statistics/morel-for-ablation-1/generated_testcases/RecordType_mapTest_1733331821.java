
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecordType_mapTest {

    @Test
    public void testMapWithSingleEntry() {
        // Given
        String name = "key1";
        String value = "value1";

        // When
        SortedMap<String, String> result = RecordType.map(name, value);

        // Then
        assertEquals(1, result.size());
        assertEquals(value, result.get(name));
    }

    @Test
    public void testMapWithMultipleEntries() {
        // Given
        String name1 = "key1";
        String value1 = "value1";
        String name2 = "key2";
        String value2 = "value2";

        // When
        SortedMap<String, String> result = RecordType.map(name1, value1, name2, value2);

        // Then
        assertEquals(2, result.size());
        assertEquals(value1, result.get(name1));
        assertEquals(value2, result.get(name2));
    }

    @Test
    public void testMapWithEmptyEntries() {
        // Given
        String name = "key1";
        String value = "value1";

        // When
        SortedMap<String, String> result = RecordType.map(name, value);

        // Then
        assertEquals(1, result.size());
        assertEquals(value, result.get(name));
    }
}
