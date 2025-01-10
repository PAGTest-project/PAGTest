package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.media.Schema;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.openapidiff.core.compare.CacheKey;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;
import org.openapitools.openapidiff.core.compare.SchemaDiff;
import org.openapitools.openapidiff.core.model.ChangedSchema;

@ExtendWith(MockitoExtension.class)
public class DeferredSchemaCache_processSchemaQueueTest {

  @Mock private OpenApiDiff openApiDiff;

  @Mock private SchemaDiff schemaDiff;

  @Mock private DeferredChanged<ChangedSchema> deferredChanged;

  private DeferredSchemaCache deferredSchemaCache;

  @BeforeEach
  public void setUp() {
    deferredSchemaCache = new DeferredSchemaCache(openApiDiff);
    deferredSchemaCache.cache = new LinkedHashMap<>();
    deferredSchemaCache.processingQueue = new ArrayDeque<>();
  }

  @Test
  public void testProcessSchemaQueue() {
    // Given
    CacheKey key = new CacheKey("left", "right", null);
    Schema left = new Schema<>();
    Schema right = new Schema<>();
    SchemaDiffOperation operation = new SchemaDiffOperation(openApiDiff, null, key, left, right);
    deferredSchemaCache.cache.put(key, operation);
    deferredSchemaCache.processingQueue.add(key);

    when(openApiDiff.getSchemaDiff()).thenReturn(schemaDiff);
    when(schemaDiff.computeDiffForReal(any(), any(), any(), any())).thenReturn(deferredChanged);

    // When
    deferredSchemaCache.processSchemaQueue();

    // Then
    assertTrue(operation.processed);
    verify(deferredChanged).whenSet(any());
  }
}
