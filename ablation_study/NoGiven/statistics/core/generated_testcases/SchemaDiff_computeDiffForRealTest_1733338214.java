
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.openapitools.openapidiff.core.model.deferred.RecursiveSchemaSet;
import org.openapitools.openapidiff.core.utils.RefPointer;
import org.openapitools.openapidiff.core.model.ChangedSchema;
import org.openapitools.openapidiff.core.compare.SchemaDiffResult;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SchemaDiff_computeDiffForRealTest {

    @Test
    public void testComputeDiffForReal_TypeChanged() {
        // Given
        SchemaDiff schemaDiff = new SchemaDiff(mock(OpenApiDiff.class));
        Schema left = new Schema().type("string");
        Schema right = new Schema().type("integer");
        DiffContext context = new DiffContext(mock(OpenApiDiffOptions.class));
        RecursiveSchemaSet refSet = new RecursiveSchemaSet();

        // When
        DeferredChanged<ChangedSchema> result = schemaDiff.computeDiffForReal(refSet, left, right, context);

        // Then
        assertTrue(result.isPresent());
        assertTrue(result.get().isChangedType());
    }

    @Test
    public void testComputeDiffForReal_SameType() {
        // Given
        SchemaDiff schemaDiff = new SchemaDiff(mock(OpenApiDiff.class));
        Schema left = new Schema().type("string");
        Schema right = new Schema().type("string");
        DiffContext context = new DiffContext(mock(OpenApiDiffOptions.class));
        RecursiveSchemaSet refSet = new RecursiveSchemaSet();

        // Mocking the necessary methods
        RefPointer<Schema<?>> refPointer = mock(RefPointer.class);
        when(refPointer.resolveRef(any(), any(), any())).thenReturn(left, right);
        schemaDiff.refPointer = refPointer;

        SchemaDiffResult schemaDiffResult = mock(SchemaDiffResult.class);
        when(SchemaDiff.getSchemaDiffResult(any(), any())).thenReturn(schemaDiffResult);

        // When
        DeferredChanged<ChangedSchema> result = schemaDiff.computeDiffForReal(refSet, left, right, context);

        // Then
        assertTrue(result.isPresent());
        verify(schemaDiffResult).diff(refSet, schemaDiff.leftComponents, schemaDiff.rightComponents, left, right, context);
    }
}
