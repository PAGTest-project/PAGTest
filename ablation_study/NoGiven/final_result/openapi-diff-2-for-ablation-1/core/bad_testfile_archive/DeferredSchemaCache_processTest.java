package org.openapitools.openapidiff.core.model.deferred;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.swagger.v3.oas.models.media.Schema;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.openapidiff.core.compare.CacheKey;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;
import org.openapitools.openapidiff.core.model.ChangedSchema;

@ExtendWith(MockitoExtension.class)
public class DeferredSchemaCache_processTest {

  @Mock private OpenApiDiff openApiDiff;

  @Mock private Schema leftSchema;

  @Mock private Schema rightSchema;

  @Mock private CacheKey cacheKey;

  @Mock private RecursiveSchemaSet refSet;

  private DeferredSchemaCache deferredSchemaCache;

  @BeforeEach
  public void setUp() {
    deferredSchemaCache = new DeferredSchemaCache(openApiDiff);
  }

  @Test
  public void testProcess() {
    // Given
    deferredSchemaCache.addSchema(refSet, cacheKey, leftSchema, rightSchema);

    // When
    deferredSchemaCache.process();

    // Then
    List<ChangedSchema> changedSchemas = deferredSchemaCache.getChangedSchemas();
    assertTrue(changedSchemas.isEmpty());
  }
}
