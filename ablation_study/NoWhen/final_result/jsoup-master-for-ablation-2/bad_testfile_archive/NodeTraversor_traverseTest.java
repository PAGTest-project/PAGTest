
package org.jsoup.select;

import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class NodeTraversor_traverseTest {

    @Test
    public void testTraverse_SingleNode() {
        NodeVisitor visitor = mock(NodeVisitor.class);
        Node root = mock(Node.class);
        Node child = mock(Node.class);

        when(root.childNodeSize()).thenReturn(1);
        when(root.childNode(0)).thenReturn(child);
        when(child.nextSibling()).thenReturn(null);
        when(child.parentNode()).thenReturn(root);
        when(child.childNodeSize()).thenReturn(0);

        NodeTraversor.traverse(visitor, root);

        verify(visitor).head(root, 0);
        verify(visitor).head(child, 1);
        verify(visitor).tail(child, 1);
        verify(visitor).tail(root, 0);
    }

    @Test
    public void testTraverse_NodeRemoved() {
        NodeVisitor visitor = mock(NodeVisitor.class);
        Node root = mock(Node.class);
        Node child = mock(Node.class);
        Node sibling = mock(Node.class);

        when(root.childNodeSize()).thenReturn(2);
        when(root.childNode(0)).thenReturn(child);
        when(root.childNode(1)).thenReturn(sibling);
        when(child.nextSibling()).thenReturn(sibling);
        when(child.parentNode()).thenReturn(root);
        when(child.childNodeSize()).thenReturn(0);
        when(child.hasParent()).thenReturn(false);
        when(sibling.nextSibling()).thenReturn(null);
        when(sibling.parentNode()).thenReturn(root);
        when(sibling.childNodeSize()).thenReturn(0);

        NodeTraversor.traverse(visitor, root);

        verify(visitor).head(root, 0);
        verify(visitor).head(child, 1);
        verify(visitor).head(sibling, 1);
        verify(visitor).tail(sibling, 1);
        verify(visitor).tail(root, 0);
    }
}
