
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeSystem_typesForTest {

    @Test
    void testTypesFor() {
        TypeSystem typeSystem = new TypeSystem();

        // Given
        Map<String, Type.Key> keys = new HashMap<>();
        keys.put("key1", new Type.Key() {
            @Override
            public Type toType(TypeSystem typeSystem) {
                return new Type() {};
            }
        });
        keys.put("key2", new Type.Key() {
            @Override
            public Type toType(TypeSystem typeSystem) {
                return new Type() {};
            }
        });

        // When
        SortedMap<String, Type> result = typeSystem.typesFor(keys);

        // Then
        assertEquals(2, result.size());
        assertEquals(ImmutableSortedMap.of("key1", new Type() {}, "key2", new Type() {}), result);
    }
}
