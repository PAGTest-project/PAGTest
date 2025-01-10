
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PredicatedCollection_addTest {

    private PredicatedCollection<String> predicatedCollection;
    private Predicate<String> notNullPredicate;

    @BeforeEach
    public void setUp() {
        notNullPredicate = NotNullPredicate.notNullPredicate();
        predicatedCollection = PredicatedCollection.predicatedCollection(new ArrayList<>(), notNullPredicate);
    }

    @Test
    public void testAddValidElement() {
        assertTrue(predicatedCollection.add("test1"));
        assertEquals(1, predicatedCollection.size());
    }

    @Test
    public void testAddNullElement() {
        assertThrows(IllegalArgumentException.class, () -> predicatedCollection.add(null));
        assertEquals(0, predicatedCollection.size());
    }

    @Test
    public void testAddAllValidElements() {
        Collection<String> elements = Arrays.asList("test1", "test2");
        assertTrue(predicatedCollection.addAll(elements));
        assertEquals(2, predicatedCollection.size());
    }

    @Test
    public void testAddAllWithNullElement() {
        Collection<String> elements = Arrays.asList("test1", null, "test2");
        assertThrows(IllegalArgumentException.class, () -> predicatedCollection.addAll(elements));
        assertEquals(0, predicatedCollection.size());
    }

    @Test
    public void testCreatePredicatedCollectionWithNotNullPredicate() {
        final PredicatedCollection.Builder<String> builder = PredicatedCollection.notNullBuilder();
        builder.add("test1");
        builder.add((String) null);

        final List<String> predicatedList = builder.createPredicatedList();
        checkPredicatedCollection1(predicatedList);

        final Set<String> predicatedSet = builder.createPredicatedSet();
        checkPredicatedCollection1(predicatedSet);

        final Bag<String> predicatedBag = builder.createPredicatedBag();
        checkPredicatedCollection1(predicatedBag);

        final Queue<String> predicatedQueue = builder.createPredicatedQueue();
        checkPredicatedCollection1(predicatedQueue);
    }

    private void checkPredicatedCollection1(Collection<String> collection) {
        assertEquals(1, collection.size());
        assertTrue(collection.contains("test1"));
    }
}
