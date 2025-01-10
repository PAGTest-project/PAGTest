
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedHeaders_hashCodeTest {

    @Test
    public void testHashCode() {
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = new DiffContext(null);
        Map<String, Header> increased = new HashMap<>();
        Map<String, Header> missing = new HashMap<>();
        Map<String, ChangedHeader> changed = new HashMap<>();

        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context)
                .setIncreased(increased)
                .setMissing(missing)
                .setChanged(changed);

        int expectedHashCode = Objects.hash(oldHeaders, newHeaders, context, increased, missing, changed);
        assertEquals(expectedHashCode, changedHeaders.hashCode());
    }
}
