
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class DiffContext_setLeftAndRightUrlsTest {

    @Test
    public void testSetLeftAndRightUrls() {
        // Given
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);
        String leftUrl = "http://example.com/left";
        String rightUrl = "http://example.com/right";

        // When
        context.setUrl("http://example.com").setParameters(new HashMap<>()).setLeftAndRightUrls(leftUrl, rightUrl);

        // Then
        assertEquals(leftUrl, context.getLeftUrl());
        assertEquals(rightUrl, context.getRightUrl());
    }
}
