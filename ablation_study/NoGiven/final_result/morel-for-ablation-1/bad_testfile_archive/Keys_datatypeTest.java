
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Keys_datatypeTest {

    @Test
    public void testDatatype() {
        String name = "TestDataType";
        List<? extends Type.Key> arguments = ImmutableList.of(Keys.name("arg1"), Keys.name("arg2"));
        SortedMap<String, Type.Key> typeConstructors = new TreeMap<>();
        typeConstructors.put("constructor1", Keys.name("type1"));
        typeConstructors.put("constructor2", Keys.name("type2"));

        DataTypeKey result = Keys.datatype(name, arguments, typeConstructors);

        assertEquals(name, result.name);
        assertEquals(arguments, result.arguments);
        assertEquals(ImmutableSortedMap.copyOfSorted(typeConstructors), result.typeConstructors);
    }
}
