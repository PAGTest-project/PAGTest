
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openapitools.openapidiff.core.model.BackwardIncompatibleProp.RESPONSE_HEADERS_DECREASED;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.headers.Header;

public class ChangedHeaders_isCoreChangedTest {

    private ChangedHeaders changedHeaders;
    private Map<String, Header> oldHeaders;
    private Map<String, Header> newHeaders;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        oldHeaders = new HashMap<>();
        newHeaders = new HashMap<>();
        context = new DiffContext(null);
        changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
        changedHeaders.setIncreased(new HashMap<>());
        changedHeaders.setMissing(new HashMap<>());
    }

    @Test
    public void testIsCoreChangedNoChanges() {
        assertEquals(DiffResult.NO_CHANGES, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedCompatible() {
        Map<String, Header> increased = new HashMap<>();
        increased.put("header1", new Header());
        changedHeaders.setIncreased(increased);
        assertEquals(DiffResult.COMPATIBLE, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedIncompatible() {
        Map<String, Header> missing = new HashMap<>();
        missing.put("header1", new Header());
        changedHeaders.setMissing(missing);
        assertEquals(DiffResult.INCOMPATIBLE, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedIncompatibleWithContext() {
        Map<String, Header> missing = new HashMap<>();
        missing.put("header1", new Header());
        changedHeaders.setMissing(missing);
        context = new DiffContext(null).copyWithMethod(null).copyWithRequired(true);
        changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
        changedHeaders.setIncreased(new HashMap<>());
        assertEquals(DiffResult.INCOMPATIBLE, changedHeaders.isCoreChanged());
    }
}
