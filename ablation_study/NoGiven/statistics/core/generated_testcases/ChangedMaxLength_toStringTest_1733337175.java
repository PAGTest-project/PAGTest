
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;

public class ChangedMaxLength_toStringTest {

    @Test
    public void testToString() {
        Integer oldValue = 10;
        Integer newValue = 20;
        DiffContext context = new DiffContext();
        ChangedMaxLength changedMaxLength = new ChangedMaxLength(oldValue, newValue, context);

        String expected = "ChangedMaxLength(oldValue=" + oldValue + ", newValue=" + newValue + ", context=" + context + ")";
        assertEquals(expected, changedMaxLength.toString());
    }
}
