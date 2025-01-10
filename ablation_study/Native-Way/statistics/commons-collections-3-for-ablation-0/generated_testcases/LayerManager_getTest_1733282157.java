
package org.apache.commons.collections4.bloomfilter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class LayerManager_getTest {

    private LayerManager<BloomFilter> layerManager;
    private Shape shape;

    @BeforeEach
    public void setUp() {
        shape = new Shape(); // Assuming Shape is a class with a default constructor
        layerManager = LayerManager.builder()
                .setSupplier(() -> new SimpleBloomFilter(shape))
                .get();
    }

    @Test
    public void testGetValidDepth() {
        layerManager.addFilter();
        assertEquals(new SimpleBloomFilter(shape), layerManager.get(0));
    }

    @Test
    public void testGetInvalidDepth() {
        layerManager.addFilter();
        assertThrows(NoSuchElementException.class, () -> layerManager.get(1));
    }

    @Test
    public void testGetNegativeDepth() {
        layerManager.addFilter();
        assertThrows(NoSuchElementException.class, () -> layerManager.get(-1));
    }

    @Test
    public void testGetEmptyFilters() {
        assertThrows(NoSuchElementException.class, () -> layerManager.get(0));
    }

    @Test
    public void testGetAfterCleanup() {
        layerManager.addFilter();
        layerManager.cleanup();
        assertEquals(new SimpleBloomFilter(shape), layerManager.get(0));
    }

    @Test
    public void testGetAfterClear() {
        layerManager.addFilter();
        layerManager.clear();
        assertEquals(new SimpleBloomFilter(shape), layerManager.get(0));
    }

    @Test
    public void testGetAfterNext() {
        layerManager.addFilter();
        layerManager.next();
        assertEquals(new SimpleBloomFilter(shape), layerManager.get(1));
    }

    @Test
    public void testGetAfterCopy() {
        layerManager.addFilter();
        LayerManager<BloomFilter> copiedManager = layerManager.copy();
        assertEquals(new SimpleBloomFilter(shape), copiedManager.get(0));
    }
}
