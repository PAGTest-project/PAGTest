
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeSystem_recordTypeTest {

    @Test
    public void testRecordType() {
        TypeSystem typeSystem = new TypeSystem();

        // Given
        Collection<Map.Entry<String, Type>> argNameTypes = Arrays.asList(
            new AbstractMap.SimpleEntry<>("field1", PrimitiveType.INT),
            new AbstractMap.SimpleEntry<>("field2", PrimitiveType.STRING)
        );

        // When
        RecordLikeType result = typeSystem.recordType(argNameTypes);

        // Then
        assertEquals(2, result.argNameTypes().size());
        assertEquals(PrimitiveType.INT, result.argNameTypes().get("field1"));
        assertEquals(PrimitiveType.STRING, result.argNameTypes().get("field2"));
    }
}
