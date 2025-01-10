
package org.openapitools.openapidiff.core.model.deferred;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.CacheKey;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;
import org.openapitools.openapidiff.core.model.ChangedSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeferredSchemaCache_getOrAddSchemaTest {

    private DeferredSchemaCache deferredSchemaCache;
    private RecursiveSchemaSet refSet;
    private CacheKey key;
    private Schema left;
    private Schema right;

    @BeforeEach
    public void setUp() {
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        deferredSchemaCache = new DeferredSchemaCache(openApiDiff);
        refSet = mock(RecursiveSchemaSet.class);
        key = mock(CacheKey.class);
        left = mock(Schema.class);
        right = mock(Schema.class);
    }

    @Test
    public void testGetOrAddSchema_RecursiveCall() {
        when(refSet.contains(key)).thenReturn(true);

        DeferredChanged<ChangedSchema> result = deferredSchemaCache.getOrAddSchema(refSet, key, left, right);

        assertEquals(DeferredChanged.empty(), result);
        verify(refSet).contains(key);
        verifyNoMoreInteractions(refSet);
        verifyNoInteractions(key, left, right);
    }

    @Test
    public void testGetOrAddSchema_Cached() {
        when(refSet.contains(key)).thenReturn(false);
        SchemaDiffOperation cachedOperation = mock(SchemaDiffOperation.class);
        when(cachedOperation.diffResult).thenReturn(mock(DeferredChanged.class));
        deferredSchemaCache.cache.put(key, cachedOperation);

        DeferredChanged<ChangedSchema> result = deferredSchemaCache.getOrAddSchema(refSet, key, left, right);

        assertEquals(cachedOperation.diffResult, result);
        verify(refSet).contains(key);
        verify(refSet).put(key);
        verifyNoMoreInteractions(refSet);
        verifyNoInteractions(key, left, right);
    }

    @Test
    public void testGetOrAddSchema_AddNew() {
        when(refSet.contains(key)).thenReturn(false);
        SchemaDiffOperation newOperation = mock(SchemaDiffOperation.class);
        when(newOperation.diffResult).thenReturn(mock(DeferredChanged.class));
        when(deferredSchemaCache.addSchema(refSet, key, left, right)).thenReturn(newOperation);

        DeferredChanged<ChangedSchema> result = deferredSchemaCache.getOrAddSchema(refSet, key, left, right);

        assertEquals(newOperation.diffResult, result);
        verify(refSet).contains(key);
        verify(refSet).put(key);
        verify(deferredSchemaCache).addSchema(refSet, key, left, right);
        verifyNoMoreInteractions(refSet);
        verifyNoInteractions(key, left, right);
    }
}
