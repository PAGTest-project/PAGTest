
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Keys_progressiveRecordTest {

    @Test
    public void testProgressiveRecord() {
        // Given
        SortedMap<String, Type.Key> argNameTypes = new TreeMap<>();
        argNameTypes.put("field1", Keys.name("Type1"));
        argNameTypes.put("field2", Keys.name("Type2"));

        // When
        Type.Key result = Keys.progressiveRecord(argNameTypes);

        // Then
        assertEquals(new ProgressiveRecordKey(ImmutableSortedMap.copyOfSorted(argNameTypes)), result);
    }
}
