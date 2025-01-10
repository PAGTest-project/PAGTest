
package net.hydromatic.morel.type;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedMap;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.SortedMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TupleType_argNameTypesTest {

    @Test
    public void testArgNameTypes() {
        // Given
        List<Type> argTypes = ImmutableList.of(new MockType("Type1"), new MockType("Type2"));
        TupleType tupleType = new TupleType(argTypes);

        // When
        SortedMap<String, Type> result = tupleType.argNameTypes();

        // Then
        ImmutableSortedMap<String, Type> expected = ImmutableSortedMap.<String, Type>orderedBy(RecordType.ORDERING)
                .put("1", new MockType("Type1"))
                .put("2", new MockType("Type2"))
                .build();

        assertEquals(expected, result);
    }

    private static class MockType extends Type {
        private final String name;

        MockType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
