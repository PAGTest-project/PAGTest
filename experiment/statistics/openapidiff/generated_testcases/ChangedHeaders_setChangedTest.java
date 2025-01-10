
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedHeaders_setChangedTest {

    @Test
    public void testSetChanged() {
        // Given
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = new DiffContext(new OpenApiDiffOptions(new org.apache.commons.configuration2.CompositeConfiguration()));
        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);

        Map<String, ChangedHeader> changed = new HashMap<>();
        changed.put("header1", new ChangedHeader(new Header(), new Header(), context));

        // When
        ChangedHeaders result = changedHeaders.setChanged(changed);

        // Then
        assertNotNull(result);
        assertEquals(changed, result.getChanged());
    }
}
