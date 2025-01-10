
package org.apache.commons.collections4.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.CompositeCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeCollection_sizeTest {

    private CompositeCollection<String> c;
    private Collection<String> one;
    private Collection<String> two;

    @BeforeEach
    public void setUp() {
        c = new CompositeCollection<>();
        one = new ArrayList<>(Arrays.asList("1", "2"));
        two = new HashSet<>(Arrays.asList("3", "4"));
    }

    @Test
    public void testSizeWithEmptyComposite() {
        assertEquals(0, c.size());
    }

    @Test
    public void testSizeWithSingleCollection() {
        c.addComposited(one);
        assertEquals(2, c.size());
    }

    @Test
    public void testSizeWithMultipleCollections() {
        c.addComposited(one, two);
        assertEquals(4, c.size());
    }

    @Test
    public void testSizeAfterAddingElement() {
        c.addComposited(one);
        Collection<String> temp = new ArrayList<>(one);
        temp.add("5");
        c.removeComposited(one);
        c.addComposited(temp);
        assertEquals(3, c.size());
    }

    @Test
    public void testSizeAfterRemovingElement() {
        c.addComposited(one);
        Collection<String> temp = new ArrayList<>(one);
        temp.remove("1");
        c.removeComposited(one);
        c.addComposited(temp);
        assertEquals(1, c.size());
    }

    @Test
    public void testSizeAfterRemovingCollection() {
        c.addComposited(one, two);
        c.removeComposited(one);
        assertEquals(2, c.size());
    }
}
