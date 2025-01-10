
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataNode_cloneTest {

    @Test
    public void testClone() {
        // Given
        DataNode original = new DataNode("test data");

        // When
        DataNode cloned = original.clone();

        // Then
        assertEquals(original.getWholeData(), cloned.getWholeData());
        assertNotSame(original, cloned);
    }
}
