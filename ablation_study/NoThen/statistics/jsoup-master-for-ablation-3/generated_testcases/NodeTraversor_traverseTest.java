
package org.jsoup.select;

import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class NodeTraversor_traverseTest {

    @Test
    public void testTraverse_SingleNode() {
        NodeVisitor visitor = mock(NodeVisitor.class);
        Node root = mock(Node.class);

        when(root.parentNode()).thenReturn(null);
        when(root.nextSibling()).thenReturn(null);
        when(root.childNodeSize()).thenReturn(0);

        NodeTraversor.traverse(visitor, root);

        verify(visitor).head(root, 0);
        verify(visitor).tail(root, 0);
    }

    @Test
    public void testTraverse_NodeWithChild() {
        NodeVisitor visitor = mock(NodeVisitor.class);
        Node root = mock(Node.class);
        Node child = mock(Node.class);

        when(root.parentNode()).thenReturn(null);
        when(root.nextSibling()).thenReturn(null);
        when(root.childNodeSize()).thenReturn(1);
        when(root.childNode(0)).thenReturn(child);

        when(child.parentNode()).thenReturn(root);
        when(child.nextSibling()).thenReturn(null);
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

        when(root.parentNode()).thenReturn(null);
        when(root.nextSibling()).thenReturn(null);
        when(root.childNodeSize()).thenReturn(1);
        when(root.childNode(0)).thenReturn(child);

        when(child.parentNode()).thenReturn(root);
        when(child.nextSibling()).thenReturn(null);
        when(child.childNodeSize()).thenReturn(0);
        when(child.hasParent()).thenReturn(false);

        NodeTraversor.traverse(visitor, root);

        verify(visitor).head(root, 0);
        verify(visitor).head(child, 1);
        verify(visitor, never()).tail(child, 1);
        verify(visitor).tail(root, 0);
    }
}
