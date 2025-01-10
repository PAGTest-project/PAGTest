
package org.openapitools.openapidiff.core.compare.schemadiffresult;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;
import org.openapitools.openapidiff.core.model.ChangedSchema;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.openapitools.openapidiff.core.model.deferred.RecursiveSchemaSet;

import static org.mockito.Mockito.*;

public class ArraySchemaDiffResult_diffTest {

    private ArraySchemaDiffResult arraySchemaDiffResult;
    private OpenApiDiff openApiDiff;
    private RecursiveSchemaSet refSet;
    private Components leftComponents;
    private Components rightComponents;
    private ArraySchema leftArraySchema;
    private ArraySchema rightArraySchema;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        openApiDiff = Mockito.mock(OpenApiDiff.class);
        arraySchemaDiffResult = new ArraySchemaDiffResult(openApiDiff);
        refSet = Mockito.mock(RecursiveSchemaSet.class);
        leftComponents = Mockito.mock(Components.class);
        rightComponents = Mockito.mock(Components.class);
        leftArraySchema = Mockito.mock(ArraySchema.class);
        rightArraySchema = Mockito.mock(ArraySchema.class);
        context = Mockito.mock(DiffContext.class);
    }

    @Test
    public void testDiff_SuccessfulPath() {
        // Given
        DeferredChanged<ChangedSchema> superSchemaDiff = Mockito.mock(DeferredChanged.class);
        DeferredChanged<ChangedSchema> itemsDiff = Mockito.mock(DeferredChanged.class);
        ChangedSchema changedSchema = Mockito.mock(ChangedSchema.class);
        Schema<?> leftItems = Mockito.mock(Schema.class);
        Schema<?> rightItems = Mockito.mock(Schema.class);

        when(leftArraySchema.getItems()).thenReturn(leftItems);
        when(rightArraySchema.getItems()).thenReturn(rightItems);
        when(openApiDiff.getSchemaDiff()).thenReturn(Mockito.mock(OpenApiDiff.SchemaDiff.class));
        when(openApiDiff.getSchemaDiff().diff(refSet, leftItems, rightItems, context.copyWithRequired(true))).thenReturn(itemsDiff);
        when(superSchemaDiff.flatMap(any())).thenReturn(itemsDiff);
        when(itemsDiff.ifPresent(any())).thenReturn(itemsDiff);
        when(itemsDiff.mapOptional(any())).thenReturn(itemsDiff);
        when(context.copyWithRequired(true)).thenReturn(context);

        // When
        DeferredChanged<ChangedSchema> result = arraySchemaDiffResult.diff(refSet, leftComponents, rightComponents, leftArraySchema, rightArraySchema, context);

        // Then
        verify(itemsDiff).ifPresent(any());
        verify(itemsDiff).mapOptional(any());
    }
}
