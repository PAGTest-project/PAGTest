
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import java.util.HashMap;
import org.apache.commons.configuration2.CompositeConfiguration;

public class DiffContext_setLeftAndRightUrlsTest {

    @Test
    public void testSetLeftAndRightUrls() {
        // Given
        CompositeConfiguration config = new CompositeConfiguration();
        OpenApiDiffOptions options = new OpenApiDiffOptions(config);
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
