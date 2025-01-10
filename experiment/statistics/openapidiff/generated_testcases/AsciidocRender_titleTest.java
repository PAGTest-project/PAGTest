
package org.openapitools.openapidiff.core.output;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsciidocRender_titleTest {

    @Test
    public void testTitle() {
        AsciidocRender render = new AsciidocRender();
        String result = render.title("Test Title", 3);
        assertEquals("=== Test Title", result);
    }
}
