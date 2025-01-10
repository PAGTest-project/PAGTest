
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ChangedHeaders_setIncreasedTest {

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
    public void testSetIncreased() {
        Map<String, Header> increased = new HashMap<>();
        increased.put("header1", new Header());

        ChangedHeaders result = changedHeaders.setIncreased(increased);

        assertSame(changedHeaders, result);
        assertEquals(increased, changedHeaders.getIncreased());
    }
}
