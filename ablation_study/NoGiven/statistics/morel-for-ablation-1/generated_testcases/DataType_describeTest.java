
package net.hydromatic.morel.type;

import net.hydromatic.morel.ast.Op;
import org.junit.jupiter.api.Test;
import java.util.SortedMap;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DataType_describeTest {

    @Test
    void testDescribeSingleConstructor() {
        SortedMap<String, DataType.Key> typeConstructors = new TreeMap<>();
        typeConstructors.put("Empty", new DataType.Key(Op.DUMMY_TYPE, "Empty", 0));

        DataType dataType = new DataType("Tree", "tree", ImmutableList.of(), typeConstructors);

        StringBuilder buf = new StringBuilder();
        dataType.describe(buf);

        assertEquals("datatype tree = Empty", buf.toString());
    }

    @Test
    void testDescribeMultipleConstructors() {
        SortedMap<String, DataType.Key> typeConstructors = new TreeMap<>();
        typeConstructors.put("Empty", new DataType.Key(Op.DUMMY_TYPE, "Empty", 0));
        typeConstructors.put("Node", new DataType.Key(Op.DATA_TYPE, "Node", 3));

        DataType dataType = new DataType("Tree", "tree", ImmutableList.of(), typeConstructors);

        StringBuilder buf = new StringBuilder();
        dataType.describe(buf);

        assertEquals("datatype tree = Empty | Node of ", buf.toString());
    }
}
