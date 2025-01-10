
package org.openapitools.openapidiff.core.output;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AsciidocRender_bigTitleTest {

    @Test
    public void testBigTitle() {
        AsciidocRender render = new AsciidocRender();
        String result = render.bigTitle("OpenAPI Diff", "1.0.0");
        assertEquals("= OPENAPI DIFF (v 1.0.0)", result);
    }
}
