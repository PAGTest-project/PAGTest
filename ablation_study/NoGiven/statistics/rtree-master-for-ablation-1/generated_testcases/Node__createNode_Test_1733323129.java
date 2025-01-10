
package com.github.davidmoten.rtree.fbs.generated;

import com.google.flatbuffers.FlatBufferBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

public class Node__createNode_Test {

    @Test
    public void testCreateNode_() {
        FlatBufferBuilder builder = new FlatBufferBuilder(1024);

        // Given
        int mbbOffset = 4; // Example offset
        int[] childrenData = {8, 12}; // Example data for children
        int[] entriesData = {16, 20}; // Example data for entries

        // When
        int childrenOffset = Node_.createChildrenVector(builder, childrenData);
        int entriesOffset = Node_.createEntriesVector(builder, entriesData);
        Node_.startNode_(builder);
        int nodeOffset = Node_.createNode_(builder, mbbOffset, childrenOffset, entriesOffset);

        // Then
        assertEquals(nodeOffset, Node_.endNode_(builder));
    }
}
