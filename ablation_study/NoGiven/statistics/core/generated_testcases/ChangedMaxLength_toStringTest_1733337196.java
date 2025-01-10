
package org.openapitools.openapidiff.core.model.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedMaxLength_toStringTest {

    @Test
    public void testToString() {
        Integer oldValue = 10;
        Integer newValue = 20;
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);
        ChangedMaxLength changedMaxLength = new ChangedMaxLength(oldValue, newValue, context);

        String expected = "ChangedMaxLength(oldValue=" + oldValue + ", newValue=" + newValue + ", context=" + context + ")";
        assertEquals(expected, changedMaxLength.toString());
    }
}
