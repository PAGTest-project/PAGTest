
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeprecatedAttributes_toStringTest {

    @Test
    public void testToStringWithAllAttributes() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setDescription("Use Bar instead!")
                .setForRemoval(true)
                .setSince("2.0")
                .get();
        assertEquals("Deprecated for removal since 2.0: Use Bar instead!", attributes.toString());
    }

    @Test
    public void testToStringWithForRemovalOnly() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();
        assertEquals("Deprecated for removal", attributes.toString());
    }

    @Test
    public void testToStringWithSinceOnly() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setSince("2.0")
                .get();
        assertEquals("Deprecated since 2.0", attributes.toString());
    }

    @Test
    public void testToStringWithDescriptionOnly() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setDescription("Use Bar instead!")
                .get();
        assertEquals("Deprecated: Use Bar instead!", attributes.toString());
    }

    @Test
    public void testToStringWithNoAttributes() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .get();
        assertEquals("Deprecated", attributes.toString());
    }
}
