
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TypeSystem_dataTypeSchemeTest {

    @Test
    public void testDataTypeScheme() {
        TypeSystem typeSystem = new TypeSystem();
        String name = "TestDataType";
        List<TypeVar> parameters = ImmutableList.of(new TypeVar(0));
        SortedMap<String, Type.Key> tyCons = ImmutableSortedMap.of("TestTyCon", new TypeVar(0).key());

        Type result = typeSystem.dataTypeScheme(name, parameters, tyCons);

        assertNotNull(result);
    }
}
