
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeSystem_progressiveRecordTypeTest {

    @Test
    void testProgressiveRecordType() {
        TypeSystem typeSystem = new TypeSystem();

        // Given
        Map.Entry<String, Type> entry1 = new AbstractMap.SimpleEntry<>("field1", PrimitiveType.INT);
        Map.Entry<String, Type> entry2 = new AbstractMap.SimpleEntry<>("field2", PrimitiveType.STRING);
        ImmutableSortedMap<String, Type> argNameTypes = ImmutableSortedMap.copyOf(Arrays.asList(entry1, entry2), RecordType.ORDERING);

        // When
        ProgressiveRecordType result = typeSystem.progressiveRecordType(argNameTypes);

        // Then
        assertEquals(argNameTypes, result.key().argNameTypes());
    }
}
