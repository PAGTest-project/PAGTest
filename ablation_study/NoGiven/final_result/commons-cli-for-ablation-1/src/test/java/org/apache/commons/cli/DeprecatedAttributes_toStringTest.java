
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeprecatedAttributes_toStringTest {

    @Test
    public void testToStringDefault() {
        final DeprecatedAttributes defaultValue = DeprecatedAttributes.builder().get();
        assertEquals("Deprecated", defaultValue.toString());
    }

    @Test
    public void testToStringForRemoval() {
        final DeprecatedAttributes forRemovalValue = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .get();
        assertEquals("Deprecated for removal", forRemovalValue.toString());
    }

    @Test
    public void testToStringWithSince() {
        final DeprecatedAttributes sinceValue = DeprecatedAttributes.builder()
                .setSince("1.0")
                .get();
        assertEquals("Deprecated since 1.0", sinceValue.toString());
    }

    @Test
    public void testToStringWithDescription() {
        final DeprecatedAttributes descriptionValue = DeprecatedAttributes.builder()
                .setDescription("This option is deprecated")
                .get();
        assertEquals("Deprecated: This option is deprecated", descriptionValue.toString());
    }

    @Test
    public void testToStringWithAllAttributes() {
        final DeprecatedAttributes allAttributesValue = DeprecatedAttributes.builder()
                .setForRemoval(true)
                .setSince("1.0")
                .setDescription("This option is deprecated")
                .get();
        assertEquals("Deprecated for removal since 1.0: This option is deprecated", allAttributesValue.toString());
    }
}
