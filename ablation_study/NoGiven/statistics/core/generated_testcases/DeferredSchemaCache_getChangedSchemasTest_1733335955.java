
package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.CacheKey;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;
import org.openapitools.openapidiff.core.model.ChangedSchema;
import io.swagger.v3.oas.models.media.Schema;

public class DeferredSchemaCache_getChangedSchemasTest {

  private DeferredSchemaCache deferredSchemaCache;
  private OpenApiDiff openApiDiff;
  private CacheKey cacheKey;
  private Schema leftSchema;
  private Schema rightSchema;

  @BeforeEach
  public void setUp() {
    openApiDiff = mock(OpenApiDiff.class);
    deferredSchemaCache = new DeferredSchemaCache(openApiDiff);
    cacheKey = mock(CacheKey.class);
    leftSchema = mock(Schema.class);
    rightSchema = mock(Schema.class);
  }

  @Test
  public void testGetChangedSchemas() {
    // Given
    SchemaDiffOperation operation = deferredSchemaCache.addSchema(
        new RecursiveSchemaSet(), cacheKey, leftSchema, rightSchema);
    operation.processed = true;
    operation.diffResult = Optional.of(new ChangedSchema());

    // When
    List<ChangedSchema> changedSchemas = deferredSchemaCache.getChangedSchemas();

    // Then
    assertEquals(1, changedSchemas.size());
  }
}
