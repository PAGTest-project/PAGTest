package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.CacheKey;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;

class DeferredSchemaCache_addSchemaTest {

  private DeferredSchemaCache deferredSchemaCache;
  private OpenApiDiff openApiDiff;
  private RecursiveSchemaSet refSet;
  private CacheKey key;
  private Schema left;
  private Schema right;

  @BeforeEach
  void setUp() {
    openApiDiff = Mockito.mock(OpenApiDiff.class);
    deferredSchemaCache = new DeferredSchemaCache(openApiDiff);
    refSet = Mockito.mock(RecursiveSchemaSet.class);
    key = Mockito.mock(CacheKey.class);
    left = new Schema();
    right = new Schema();
  }

  @Test
  void testAddSchema_NewKey() {
    // Given
    when(deferredSchemaCache.cache.containsKey(key)).thenReturn(false);

    // When
    SchemaDiffOperation result = deferredSchemaCache.addSchema(refSet, key, left, right);

    // Then
    assertSame(result, deferredSchemaCache.cache.get(key));
  }

  @Test
  void testAddSchema_ExistingKey() {
    // Given
    SchemaDiffOperation existingOperation =
        new SchemaDiffOperation(openApiDiff, refSet, key, left, right);
    deferredSchemaCache.cache.put(key, existingOperation);
    when(deferredSchemaCache.cache.containsKey(key)).thenReturn(true);

    // When
    SchemaDiffOperation result = deferredSchemaCache.addSchema(refSet, key, left, right);

    // Then
    assertSame(existingOperation, result);
  }
}
