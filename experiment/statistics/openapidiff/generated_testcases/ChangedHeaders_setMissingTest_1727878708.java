
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedHeaders_setMissingTest {

    private ChangedHeaders changedHeaders;
    private Map<String, Header> oldHeaders;
    private Map<String, Header> newHeaders;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        oldHeaders = new HashMap<>();
        newHeaders = new HashMap<>();
        context = new DiffContext(new OpenApiDiffOptions());
        changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
    }

    @Test
    public void testSetMissing() {
        Map<String, Header> missingHeaders = new HashMap<>();
        missingHeaders.put("header1", new Header());

        ChangedHeaders result = changedHeaders.setMissing(missingHeaders);

        assertEquals(missingHeaders, changedHeaders.getMissing());
        assertEquals(changedHeaders, result);
    }
}
