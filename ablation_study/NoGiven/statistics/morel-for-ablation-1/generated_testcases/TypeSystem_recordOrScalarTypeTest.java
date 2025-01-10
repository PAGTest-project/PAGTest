
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeSystem_recordOrScalarTypeTest {

    @Test
    public void testRecordOrScalarType_SingleElement() {
        TypeSystem typeSystem = new TypeSystem();
        Type type = new PrimitiveType("int");
        Collection<Map.Entry<String, Type>> argNameTypes = ImmutableList.of(
                new AbstractMap.SimpleEntry<>("field1", type)
        );

        Type result = typeSystem.recordOrScalarType(argNameTypes);

        assertEquals(type, result);
    }

    @Test
    public void testRecordOrScalarType_MultipleElements() {
        TypeSystem typeSystem = new TypeSystem();
        Type type1 = new PrimitiveType("int");
        Type type2 = new PrimitiveType("string");
        Collection<Map.Entry<String, Type>> argNameTypes = ImmutableList.of(
                new AbstractMap.SimpleEntry<>("field1", type1),
                new AbstractMap.SimpleEntry<>("field2", type2)
        );

        Type result = typeSystem.recordOrScalarType(argNameTypes);

        assertEquals(typeSystem.recordType(argNameTypes), result);
    }
}
