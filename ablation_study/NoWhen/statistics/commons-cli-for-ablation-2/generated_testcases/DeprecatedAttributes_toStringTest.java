
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeprecatedAttributes_toStringTest {

    @Test
    public void testToStringDefault() {
        DeprecatedAttributes attributes = DeprecatedAttributes.DEFAULT;
        assertEquals("Deprecated", attributes.toString());
    }

    @Test
    public void testToStringForRemoval() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();
        assertEquals("Deprecated for removal", attributes.toString());
    }

    @Test
    public void testToStringSince() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setSince("2.0")
                .get();
        assertEquals("Deprecated since 2.0", attributes.toString());
    }

    @Test
    public void testToStringDescription() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setDescription("Use Bar instead!")
                .get();
        assertEquals("Deprecated: Use Bar instead!", attributes.toString());
    }

    @Test
    public void testToStringAllFields() {
        DeprecatedAttributes attributes = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .setSince("2.0")
                .setDescription("Use Bar instead!")
                .get();
        assertEquals("Deprecated for removal since 2.0: Use Bar instead!", attributes.toString());
    }
}
