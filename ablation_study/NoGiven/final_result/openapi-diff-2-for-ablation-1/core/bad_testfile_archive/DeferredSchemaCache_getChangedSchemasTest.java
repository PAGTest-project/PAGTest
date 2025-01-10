package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.media.Schema;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.CacheKey;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;
import org.openapitools.openapidiff.core.model.ChangedSchema;

public class DeferredSchemaCache_getChangedSchemasTest {

  private DeferredSchemaCache deferredSchemaCache;
  private OpenApiDiff openApiDiff;
  private CacheKey cacheKey;
  private Schema leftSchema;
  private Schema rightSchema;

  @BeforeEach
  public void setUp() {
    openApiDiff = Mockito.mock(OpenApiDiff.class);
    deferredSchemaCache = new DeferredSchemaCache(openApiDiff);
    cacheKey = Mockito.mock(CacheKey.class);
    leftSchema = Mockito.mock(Schema.class);
    rightSchema = Mockito.mock(Schema.class);
  }

  @Test
  public void testGetChangedSchemas() {
    // Given
    SchemaDiffOperation operation =
        deferredSchemaCache.addSchema(new RecursiveSchemaSet(), cacheKey, leftSchema, rightSchema);
    operation.processed = true;
    operation.diffResult = Optional.of(new ChangedSchema());

    // When
    List<ChangedSchema> changedSchemas = deferredSchemaCache.getChangedSchemas();

    // Then
    assertEquals(1, changedSchemas.size());
  }
}
