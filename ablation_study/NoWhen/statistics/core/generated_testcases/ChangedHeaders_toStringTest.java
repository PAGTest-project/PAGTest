
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedHeaders_toStringTest {

    @Test
    public void testToString() {
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        Map<String, Header> increased = new HashMap<>();
        Map<String, Header> missing = new HashMap<>();
        Map<String, ChangedHeader> changed = new HashMap<>();
        DiffContext context = new DiffContext(new OpenApiDiffOptions());

        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed);

        String expected = "ChangedHeaders(oldHeaders={}, newHeaders={}, context=" + context + ", increased={}, missing={}, changed={})";
        assertEquals(expected, changedHeaders.toString());
    }
}
