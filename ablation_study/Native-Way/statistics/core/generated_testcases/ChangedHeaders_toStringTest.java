
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import io.swagger.v3.oas.models.headers.Header;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;
import org.apache.commons.configuration2.CompositeConfiguration;

class ChangedHeaders_toStringTest {

    @Test
    void testToString() {
        Map<String, Header> oldHeaders = new HashMap<>();
        oldHeaders.put("oldHeader1", new Header());
        Map<String, Header> newHeaders = new HashMap<>();
        newHeaders.put("newHeader1", new Header());
        Map<String, Header> increased = new HashMap<>();
        increased.put("increasedHeader1", new Header());
        Map<String, Header> missing = new HashMap<>();
        missing.put("missingHeader1", new Header());
        Map<String, ChangedHeader> changed = new HashMap<>();
        changed.put("changedHeader1", new ChangedHeader(new Header(), new Header(), new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration()))));

        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, new DiffContext(new OpenApiDiffOptions(new CompositeConfiguration())))
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed);

        String expected = "ChangedHeaders(oldHeaders={oldHeader1=io.swagger.v3.oas.models.headers.Header@}, newHeaders={newHeader1=io.swagger.v3.oas.models.headers.Header@}, context=org.openapitools.openapidiff.core.model.DiffContext@, increased={increasedHeader1=io.swagger.v3.oas.models.headers.Header@}, missing={missingHeader1=io.swagger.v3.oas.models.headers.Header@}, changed={changedHeader1=org.openapitools.openapidiff.core.model.ChangedHeader@})";
        assertEquals(expected, changedHeaders.toString());
    }
}
