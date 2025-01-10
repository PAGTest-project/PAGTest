
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedHeaders_getChangedElementsTest {

    private ChangedHeaders changedHeaders;
    private Map<String, Header> oldHeaders;
    private Map<String, Header> newHeaders;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        oldHeaders = new HashMap<>();
        newHeaders = new HashMap<>();
        context = new DiffContext();
        changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);
    }

    @Test
    public void testGetChangedElements() {
        // Given
        Map<String, ChangedHeader> changedMap = new HashMap<>();
        ChangedHeader changedHeader = new ChangedHeader();
        changedMap.put("header1", changedHeader);
        changedHeaders.setChanged(changedMap);

        // When
        List<Changed> changedElements = changedHeaders.getChangedElements();

        // Then
        assertEquals(1, changedElements.size());
        assertEquals(changedHeader, changedElements.get(0));
    }
}
