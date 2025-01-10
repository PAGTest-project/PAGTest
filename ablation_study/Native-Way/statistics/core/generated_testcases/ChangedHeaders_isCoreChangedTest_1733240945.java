
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class ChangedHeaders_isCoreChangedTest {
    private ChangedHeaders changedHeaders;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        context = new DiffContext(new OpenApiDiffOptions());
        changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
    }

    @Test
    public void testIsCoreChangedNoChanges() {
        changedHeaders.setIncreased(new HashMap<>());
        changedHeaders.setMissing(new HashMap<>());
        assertEquals(DiffResult.NO_CHANGES, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedCompatible() {
        Map<String, Header> increased = new HashMap<>();
        increased.put("header1", new Header());
        changedHeaders.setIncreased(increased);
        changedHeaders.setMissing(new HashMap<>());
        assertEquals(DiffResult.COMPATIBLE, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedIncompatible() {
        Map<String, Header> missing = new HashMap<>();
        missing.put("header1", new Header());
        changedHeaders.setIncreased(new HashMap<>());
        changedHeaders.setMissing(missing);
        context.setResponse();
        assertEquals(DiffResult.INCOMPATIBLE, changedHeaders.isCoreChanged());
    }

    @Test
    public void testIsCoreChangedIncompatibleNotEnabled() {
        Map<String, Header> missing = new HashMap<>();
        missing.put("header1", new Header());
        changedHeaders.setIncreased(new HashMap<>());
        changedHeaders.setMissing(missing);
        context.setRequest();
        assertEquals(DiffResult.COMPATIBLE, changedHeaders.isCoreChanged());
    }
}
