
package com.github.davidmoten.rtree;

import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

public class SelectorRStar_selectTest {

    private SelectorRStar selectorRStar;
    private Selector overlapAreaSelector;
    private Selector areaIncreaseSelector;
    private Node<Object, Geometry> leafNode;
    private Node<Object, Geometry> nonLeafNode;

    @Before
    public void setup() {
        selectorRStar = new SelectorRStar();
        overlapAreaSelector = Mockito.mock(Selector.class);
        areaIncreaseSelector = Mockito.mock(Selector.class);
        selectorRStar.overlapAreaSelector = overlapAreaSelector;
        selectorRStar.areaIncreaseSelector = areaIncreaseSelector;

        leafNode = Mockito.mock(Node.class);
        nonLeafNode = Mockito.mock(Node.class);
        when(leafNode.mbr()).thenReturn(Mockito.mock(Rectangle.class));
        when(nonLeafNode.mbr()).thenReturn(Mockito.mock(Rectangle.class));
    }

    @Test
    public void testSelectWithLeafNodes() {
        List<Node<Object, Geometry>> nodes = Arrays.asList(leafNode, leafNode);
        Geometry g = Mockito.mock(Geometry.class);
        Node<Object, Geometry> expectedNode = Mockito.mock(Node.class);

        when(overlapAreaSelector.select(g, nodes)).thenReturn(expectedNode);

        Node<Object, Geometry> result = selectorRStar.select(g, nodes);
        assertSame(expectedNode, result);
    }

    @Test
    public void testSelectWithNonLeafNodes() {
        List<Node<Object, Geometry>> nodes = Arrays.asList(nonLeafNode, nonLeafNode);
        Geometry g = Mockito.mock(Geometry.class);
        Node<Object, Geometry> expectedNode = Mockito.mock(Node.class);

        when(areaIncreaseSelector.select(g, nodes)).thenReturn(expectedNode);

        Node<Object, Geometry> result = selectorRStar.select(g, nodes);
        assertSame(expectedNode, result);
    }
}
