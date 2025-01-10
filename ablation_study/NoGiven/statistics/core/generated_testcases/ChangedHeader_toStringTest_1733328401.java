
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedHeader_toStringTest {

    @Test
    public void testToString() {
        Header oldHeader = new Header();
        Header newHeader = new Header();
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);

        ChangedHeader changedHeader = new ChangedHeader(oldHeader, newHeader, context)
                .setRequired(true)
                .setDeprecated(true)
                .setStyle(true)
                .setExplode(true);

        String expected = "ChangedHeader(oldHeader=" + oldHeader + ", newHeader=" + newHeader + ", context=" + context + ", required=true, deprecated=true, style=true, explode=true, description=null, schema=null, content=null, extensions=null)";
        assertEquals(expected, changedHeader.toString());
    }
}
