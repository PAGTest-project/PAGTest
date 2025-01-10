
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class DeprecatedAttributes_toStringTest {

    @Test
    public void testToStringDefault() {
        final DeprecatedAttributes defaultValue = new DeprecatedAttributes();
        assertEquals("Deprecated", defaultValue.toString());
    }

    @Test
    public void testToStringForRemoval() {
        final DeprecatedAttributes forRemovalValue = new DeprecatedAttributes();
        forRemovalValue.setForRemoval(true);
        assertEquals("Deprecated for removal", forRemovalValue.toString());
    }

    @Test
    public void testToStringWithSince() {
        final DeprecatedAttributes sinceValue = new DeprecatedAttributes();
        sinceValue.setSince("1.0");
        assertEquals("Deprecated since 1.0", sinceValue.toString());
    }

    @Test
    public void testToStringWithDescription() {
        final DeprecatedAttributes descriptionValue = new DeprecatedAttributes();
        descriptionValue.setDescription("This option is deprecated");
        assertEquals("Deprecated: This option is deprecated", descriptionValue.toString());
    }

    @Test
    public void testToStringWithAllAttributes() {
        final DeprecatedAttributes allAttributesValue = new DeprecatedAttributes();
        allAttributesValue.setForRemoval(true);
        allAttributesValue.setSince("1.0");
        allAttributesValue.setDescription("This option is deprecated");
        assertEquals("Deprecated for removal since 1.0: This option is deprecated", allAttributesValue.toString());
    }
}
