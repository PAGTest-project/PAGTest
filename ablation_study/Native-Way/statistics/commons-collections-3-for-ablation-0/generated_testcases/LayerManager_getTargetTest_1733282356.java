
package org.apache.commons.collections4.bloomfilter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.function.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LayerManager_getTargetTest {

    private LayerManager<BloomFilter> layerManager;
    private Shape shape;

    @BeforeEach
    public void setUp() {
        shape = new Shape();
        layerManager = LayerManager.builder()
                                   .setSupplier(() -> new SimpleBloomFilter(shape))
                                   .setExtendCheck(LayerManager.ExtendCheck.neverAdvance())
                                   .setCleanup(LayerManager.Cleanup.noCleanup())
                                   .get();
    }

    @Test
    public void testGetTargetWhenExtendCheckIsFalse() {
        // Arrange
        Predicate<LayerManager<BloomFilter>> extendCheck = lm -> false;
        layerManager = LayerManager.builder()
                                   .setSupplier(() -> new SimpleBloomFilter(shape))
                                   .setExtendCheck(extendCheck)
                                   .setCleanup(LayerManager.Cleanup.noCleanup())
                                   .get();

        // Act
        BloomFilter target = layerManager.getTarget();

        // Assert
        assertEquals(layerManager.last(), target);
    }

    @Test
    public void testGetTargetWhenExtendCheckIsTrue() {
        // Arrange
        Predicate<LayerManager<BloomFilter>> extendCheck = lm -> true;
        layerManager = LayerManager.builder()
                                   .setSupplier(() -> new SimpleBloomFilter(shape))
                                   .setExtendCheck(extendCheck)
                                   .setCleanup(LayerManager.Cleanup.noCleanup())
                                   .get();

        // Act
        BloomFilter target = layerManager.getTarget();

        // Assert
        assertNotEquals(layerManager.last(), target);
        assertEquals(layerManager.last(), target);
    }

    @Test
    public void testGetTargetAfterNext() {
        // Arrange
        Predicate<LayerManager<BloomFilter>> extendCheck = lm -> true;
        layerManager = LayerManager.builder()
                                   .setSupplier(() -> new SimpleBloomFilter(shape))
                                   .setExtendCheck(extendCheck)
                                   .setCleanup(LayerManager.Cleanup.noCleanup())
                                   .get();

        // Act
        layerManager.next();
        BloomFilter target = layerManager.getTarget();

        // Assert
        assertEquals(layerManager.last(), target);
    }

    @Test
    public void testGetTargetWithEmptyFilters() {
        // Arrange
        layerManager = LayerManager.builder()
                                   .setSupplier(() -> new SimpleBloomFilter(shape))
                                   .setExtendCheck(LayerManager.ExtendCheck.neverAdvance())
                                   .setCleanup(LayerManager.Cleanup.noCleanup())
                                   .get();
        layerManager.clear();

        // Act
        BloomFilter target = layerManager.getTarget();

        // Assert
        assertEquals(layerManager.last(), target);
    }
}
