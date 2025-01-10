
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeprecatedAttributes_toStringTest {

    private DeprecatedAttributes deprecatedAttributes;

    @BeforeEach
    public void setUp() {
        deprecatedAttributes = DeprecatedAttributes.builder()
                .setDescription("Use Bar instead!")
                .setForRemoval(true)
                .setSince("2.0")
                .get();
    }

    @Test
    public void testToStringForRemoval() {
        assertEquals("Deprecated for removal since 2.0: Use Bar instead!", deprecatedAttributes.toString());
    }

    @Test
    public void testToStringNoForRemoval() {
        deprecatedAttributes = DeprecatedAttributes.builder()
                .setDescription("Use Bar instead!")
                .setForRemoval(false)
                .setSince("2.0")
                .get();
        assertEquals("Deprecated since 2.0: Use Bar instead!", deprecatedAttributes.toString());
    }

    @Test
    public void testToStringNoSince() {
        deprecatedAttributes = DeprecatedAttributes.builder()
                .setDescription("Use Bar instead!")
                .setForRemoval(true)
                .setSince("")
                .get();
        assertEquals("Deprecated for removal: Use Bar instead!", deprecatedAttributes.toString());
    }

    @Test
    public void testToStringNoDescription() {
        deprecatedAttributes = DeprecatedAttributes.builder()
                .setDescription("")
                .setForRemoval(true)
                .setSince("2.0")
                .get();
        assertEquals("Deprecated for removal since 2.0", deprecatedAttributes.toString());
    }

    @Test
    public void testToStringNoAttributes() {
        deprecatedAttributes = DeprecatedAttributes.builder()
                .setDescription("")
                .setForRemoval(false)
                .setSince("")
                .get();
        assertEquals("Deprecated", deprecatedAttributes.toString());
    }
}
