
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.headers.Header;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangedHeaders_equalsTest {

    @Test
    public void testEquals_SameInstance() {
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = new DiffContext(null);
        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);

        assertTrue(changedHeaders.equals(changedHeaders));
    }

    @Test
    public void testEquals_DifferentClass() {
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = new DiffContext(null);
        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);

        assertFalse(changedHeaders.equals(new Object()));
    }

    @Test
    public void testEquals_NullObject() {
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = new DiffContext(null);
        ChangedHeaders changedHeaders = new ChangedHeaders(oldHeaders, newHeaders, context);

        assertFalse(changedHeaders.equals(null));
    }

    @Test
    public void testEquals_DifferentFields() {
        Map<String, Header> oldHeaders1 = new HashMap<>();
        Map<String, Header> newHeaders1 = new HashMap<>();
        DiffContext context1 = new DiffContext(null);
        ChangedHeaders changedHeaders1 = new ChangedHeaders(oldHeaders1, newHeaders1, context1);

        Map<String, Header> oldHeaders2 = new HashMap<>();
        Map<String, Header> newHeaders2 = new HashMap<>();
        DiffContext context2 = new DiffContext(null);
        ChangedHeaders changedHeaders2 = new ChangedHeaders(oldHeaders2, newHeaders2, context2);

        // Initialize the fields that are not initialized in the constructor
        changedHeaders1.setIncreased(new HashMap<>()).setMissing(new HashMap<>()).setChanged(new HashMap<>());
        changedHeaders2.setIncreased(new HashMap<>()).setMissing(new HashMap<>()).setChanged(new HashMap<>());

        assertFalse(changedHeaders1.equals(changedHeaders2));
    }

    @Test
    public void testEquals_SameFields() {
        Map<String, Header> oldHeaders = new HashMap<>();
        Map<String, Header> newHeaders = new HashMap<>();
        DiffContext context = new DiffContext(null);
        ChangedHeaders changedHeaders1 = new ChangedHeaders(oldHeaders, newHeaders, context);
        ChangedHeaders changedHeaders2 = new ChangedHeaders(oldHeaders, newHeaders, context);

        // Initialize the fields that are not initialized in the constructor
        changedHeaders1.setIncreased(new HashMap<>()).setMissing(new HashMap<>()).setChanged(new HashMap<>());
        changedHeaders2.setIncreased(new HashMap<>()).setMissing(new HashMap<>()).setChanged(new HashMap<>());

        assertTrue(changedHeaders1.equals(changedHeaders2));
    }
}
