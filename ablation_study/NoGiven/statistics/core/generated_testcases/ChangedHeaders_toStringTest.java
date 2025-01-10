
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
        oldHeaders.put("oldHeader1", new Header());

        Map<String, Header> newHeaders = new HashMap<>();
        newHeaders.put("newHeader1", new Header());

        Map<String, Header> increased = new HashMap<>();
        increased.put("increasedHeader1", new Header());

        Map<String, Header> missing = new HashMap<>();
        missing.put("missingHeader1", new Header());

        Map<String, ChangedHeader> changed = new HashMap<>();
        changed.put("changedHeader1", new ChangedHeader(new Header(), new Header(), new DiffContext()));

        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);

        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed);

        String expected = "ChangedHeaders(oldHeaders={oldHeader1=io.swagger.v3.oas.models.headers.Header@0}, newHeaders={newHeader1=io.swagger.v3.oas.models.headers.Header@0}, context=org.openapitools.openapidiff.core.model.DiffContext@0, increased={increasedHeader1=io.swagger.v3.oas.models.headers.Header@0}, missing={missingHeader1=io.swagger.v3.oas.models.headers.Header@0}, changed={changedHeader1=org.openapitools.openapidiff.core.model.ChangedHeader@0})";

        assertEquals(expected, changedHeaders.toString());
    }
}
