
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LayerManager_copyTest {

    private LayerManager<BloomFilter> layerManager;
    private Supplier<BloomFilter> filterSupplier;
    private Predicate<LayerManager<BloomFilter>> extendCheck;
    private Consumer<Deque<BloomFilter>> filterCleanup;

    @BeforeEach
    public void setUp() {
        filterSupplier = () -> new SimpleBloomFilter(new Shape(10, 5));
        extendCheck = ExtendCheck.neverAdvance();
        filterCleanup = Cleanup.noCleanup();
        layerManager = new LayerManager<>(filterSupplier, extendCheck, filterCleanup, true);
    }

    @Test
    public void testCopyWithEmptyFilters() {
        LayerManager<BloomFilter> copiedManager = layerManager.copy();
        assertEquals(0, copiedManager.getDepth());
        assertNotSame(layerManager, copiedManager);
    }

    @Test
    public void testCopyWithNonEmptyFilters() {
        layerManager.addFilter();
        LayerManager<BloomFilter> copiedManager = layerManager.copy();
        assertEquals(1, copiedManager.getDepth());
        assertNotSame(layerManager, copiedManager);
        assertNotSame(layerManager.first(), copiedManager.first());
    }

    @Test
    public void testCopyWithMultipleFilters() {
        layerManager.addFilter();
        layerManager.addFilter();
        LayerManager<BloomFilter> copiedManager = layerManager.copy();
        assertEquals(2, copiedManager.getDepth());
        assertNotSame(layerManager, copiedManager);
        assertNotSame(layerManager.first(), copiedManager.first());
        assertNotSame(layerManager.last(), copiedManager.last());
    }

    @Test
    public void testCopyWithDifferentFilterSupplier() {
        Supplier<BloomFilter> newFilterSupplier = () -> new SimpleBloomFilter(new Shape(20, 10));
        LayerManager<BloomFilter> newLayerManager = new LayerManager<>(newFilterSupplier, extendCheck, filterCleanup, true);
        newLayerManager.addFilter();
        LayerManager<BloomFilter> copiedManager = newLayerManager.copy();
        assertEquals(1, copiedManager.getDepth());
        assertNotSame(newLayerManager, copiedManager);
        assertNotSame(newLayerManager.first(), copiedManager.first());
    }

    @Test
    public void testCopyWithDifferentExtendCheck() {
        Predicate<LayerManager<BloomFilter>> newExtendCheck = ExtendCheck.advanceOnCount(2);
        LayerManager<BloomFilter> newLayerManager = new LayerManager<>(filterSupplier, newExtendCheck, filterCleanup, true);
        newLayerManager.addFilter();
        LayerManager<BloomFilter> copiedManager = newLayerManager.copy();
        assertEquals(1, copiedManager.getDepth());
        assertNotSame(newLayerManager, copiedManager);
        assertNotSame(newLayerManager.first(), copiedManager.first());
    }

    @Test
    public void testCopyWithDifferentFilterCleanup() {
        Consumer<Deque<BloomFilter>> newFilterCleanup = Cleanup.onMaxSize(1);
        LayerManager<BloomFilter> newLayerManager = new LayerManager<>(filterSupplier, extendCheck, newFilterCleanup, true);
        newLayerManager.addFilter();
        LayerManager<BloomFilter> copiedManager = newLayerManager.copy();
        assertEquals(1, copiedManager.getDepth());
        assertNotSame(newLayerManager, copiedManager);
        assertNotSame(newLayerManager.first(), copiedManager.first());
    }
}
