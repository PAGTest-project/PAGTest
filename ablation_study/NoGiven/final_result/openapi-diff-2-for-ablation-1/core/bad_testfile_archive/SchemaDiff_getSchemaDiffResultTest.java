package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.schemadiffresult.SchemaDiffResult;
import org.openapitools.openapidiff.core.model.ChangedSchema;

public class SchemaDiff_getSchemaDiffResultTest {

  @Test
  public void testGetSchemaDiffResult_ValidClassType() throws Exception {
    OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
    SchemaDiffResult result = SchemaDiff.getSchemaDiffResult(Schema.class, openApiDiff);
    assert result instanceof SchemaDiffResult;
  }

  @Test
  public void testGetSchemaDiffResult_NullClassType() throws Exception {
    OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
    SchemaDiffResult result = SchemaDiff.getSchemaDiffResult(null, openApiDiff);
    assert result instanceof SchemaDiffResult;
  }

  @Test
  public void testGetSchemaDiffResult_InvalidClassType() {
    OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          SchemaDiff.getSchemaDiffResult(ChangedSchema.class, openApiDiff);
        });
  }

  @Test
  public void testGetSchemaDiffResult_ExceptionDuringInstantiation() {
    OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
    Class<? extends SchemaDiffResult> mockClass = mock(Class.class);
    when(SchemaDiff.schemaDiffResultClassMap.get(Schema.class)).thenReturn(mockClass);
    when(mockClass.getConstructor(OpenApiDiff.class)).thenThrow(new NoSuchMethodException());

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          SchemaDiff.getSchemaDiffResult(Schema.class, openApiDiff);
        });
  }
}
