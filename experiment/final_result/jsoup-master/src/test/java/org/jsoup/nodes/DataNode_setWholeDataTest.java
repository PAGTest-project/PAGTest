
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DataNode_setWholeDataTest {

    @Test
    void testSetWholeData() {
        // Given
        DataNode dataNode = new DataNode("initialData");

        // When
        DataNode updatedNode = dataNode.setWholeData("newData");

        // Then
        assertEquals("newData", updatedNode.getWholeData());
    }
}
