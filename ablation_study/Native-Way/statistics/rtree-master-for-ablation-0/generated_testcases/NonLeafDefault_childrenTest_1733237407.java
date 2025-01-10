
package com.github.davidmoten.rtree.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.davidmoten.rtree.Context;
import com.github.davidmoten.rtree.Node;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.github.davidmoten.rtree.geometry.Rectangle;

public class NonLeafDefault_childrenTest {

    private NonLeafDefault<String, Rectangle> nonLeaf;
    private Node<String, Rectangle> child1;
    private Node<String, Rectangle> child2;

    @Before
    public void setUp() {
        child1 = new MockNode<>("child1", Rectangle.create(0, 0, 1, 1));
        child2 = new MockNode<>("child2", Rectangle.create(1, 1, 2, 2));
        List<Node<String, Rectangle>> children = Arrays.asList(child1, child2);
        Context<String, Rectangle> context = new MockContext<>();
        nonLeaf = new NonLeafDefault<>(children, context);
    }

    @Test
    public void testChildren() {
        List<Node<String, Rectangle>> result = nonLeaf.children();
        assertEquals(2, result.size());
        assertTrue(result.contains(child1));
        assertTrue(result.contains(child2));
    }

    // Mock classes for testing
    private static class MockNode<T, S extends Geometry> implements Node<T, S> {
        private final T value;
        private final S geometry;

        public MockNode(T value, S geometry) {
            this.value = value;
            this.geometry = geometry;
        }

        @Override
        public Geometry geometry() {
            return geometry;
        }

        @Override
        public List<Node<T, S>> add(Entry<? extends T, ? extends S> entry) {
            return null;
        }

        @Override
        public NodeAndEntries<T, S> delete(Entry<? extends T, ? extends S> entry, boolean all) {
            return null;
        }

        @Override
        public void searchWithoutBackpressure(Func1<? super Geometry, Boolean> criterion,
                Subscriber<? super Entry<T, S>> subscriber) {
        }

        @Override
        public int count() {
            return 0;
        }

        @Override
        public Context<T, S> context() {
            return null;
        }

        @Override
        public Node<T, S> child(int i) {
            return null;
        }

        @Override
        public List<Node<T, S>> children() {
            return null;
        }
    }

    private static class MockContext<T, S extends Geometry> implements Context<T, S> {
        // Implement necessary methods for Context if needed
    }
}
