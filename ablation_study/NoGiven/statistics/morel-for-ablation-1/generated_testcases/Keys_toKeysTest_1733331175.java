
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Keys_toKeysTest {

    @Test
    void testToKeys() {
        // Given
        SortedMap<String, Type> nameTypes = new TreeMap<>();
        nameTypes.put("key1", new RecordType(ImmutableSortedMap.of("field1", new RecordType(ImmutableSortedMap.of()))));
        nameTypes.put("key2", new RecordType(ImmutableSortedMap.of("field2", new RecordType(ImmutableSortedMap.of()))));

        // When
        SortedMap<String, Type.Key> result = Keys.toKeys(nameTypes);

        // Then
        assertEquals(2, result.size());
        assertEquals(new RecordKey(ImmutableSortedMap.of("field1", new RecordType(ImmutableSortedMap.of()).key())), result.get("key1"));
        assertEquals(new RecordKey(ImmutableSortedMap.of("field2", new RecordType(ImmutableSortedMap.of()).key())), result.get("key2"));
    }
}
