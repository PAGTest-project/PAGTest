
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Keys_toProgressiveTest {

    @Test
    void testToProgressiveWithRecordKey() {
        // Given
        Type.Key recordKey = Keys.record(ImmutableSortedMap.of("field1", Keys.name("type1")));

        // When
        Type.Key result = Keys.toProgressive(recordKey);

        // Then
        assertEquals(ProgressiveRecordKey.class, result.getClass());
    }

    @Test
    void testToProgressiveWithNonRecordKey() {
        // Given
        Type.Key nonRecordKey = Keys.name("nonRecordType");

        // When
        Type.Key result = Keys.toProgressive(nonRecordKey);

        // Then
        assertEquals(nonRecordKey, result);
    }
}
