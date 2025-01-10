
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.collection.CompositeCollection.CollectionMutator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_containsTest {

    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new ArrayList<>();
        two = new ArrayList<>();
    }

    @Test
    public void testContainsWithSingleCollection() {
        one.add("1");
        c.addComposited(one);
        assertTrue(c.contains("1"));
        assertFalse(c.contains("2"));
    }

    @Test
    public void testContainsWithMultipleCollections() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
        assertFalse(c.contains("3"));
    }

    @Test
    public void testContainsAfterAdd() {
        one.add("1");
        c.addComposited(one);
        c.setMutator(new CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                return one.add(obj);
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                return one.addAll(coll);
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                return one.remove(obj);
            }
        });
        c.add("2");
        assertTrue(c.contains("1"));
        assertTrue(c.contains("2"));
    }

    @Test
    public void testContainsAfterRemove() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        c.setMutator(new CollectionMutator<String>() {
            @Override
            public boolean add(CompositeCollection<String> composite, List<Collection<String>> collections, String obj) {
                return one.add(obj);
            }

            @Override
            public boolean addAll(CompositeCollection<String> composite, List<Collection<String>> collections, Collection<? extends String> coll) {
                return one.addAll(coll);
            }

            @Override
            public boolean remove(CompositeCollection<String> composite, List<Collection<String>> collections, Object obj) {
                return one.remove(obj) || two.remove(obj);
            }
        });
        c.remove("1");
        assertFalse(c.contains("1"));
        assertTrue(c.contains("2"));
    }

    @Test
    public void testContainsAfterClear() {
        one.add("1");
        two.add("2");
        c.addComposited(one, two);
        c.clear();
        assertFalse(c.contains("1"));
        assertFalse(c.contains("2"));
    }
}
