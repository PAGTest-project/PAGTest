
package org.openapitools.openapidiff.core.compare.schemadiffresult;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.ComposedSchema;
import io.swagger.v3.oas.models.media.Discriminator;
import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.compare.OpenApiDiff;
import org.openapitools.openapidiff.core.model.ChangedSchema;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.openapitools.openapidiff.core.model.deferred.RecursiveSchemaSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComposedSchemaDiffResult_diffTest {

    @Test
    public void testDiff_DiscriminatorPropertyChanged() {
        // Given
        ComposedSchemaDiffResult composedSchemaDiffResult = new ComposedSchemaDiffResult(mock(OpenApiDiff.class));
        RecursiveSchemaSet refSet = mock(RecursiveSchemaSet.class);
        Components leftComponents = mock(Components.class);
        Components rightComponents = mock(Components.class);
        ComposedSchema left = mock(ComposedSchema.class);
        ComposedSchema right = mock(ComposedSchema.class);
        DiffContext context = mock(DiffContext.class);

        Discriminator leftDis = mock(Discriminator.class);
        Discriminator rightDis = mock(Discriminator.class);

        when(left.getDiscriminator()).thenReturn(leftDis);
        when(right.getDiscriminator()).thenReturn(rightDis);
        when(leftDis.getPropertyName()).thenReturn("leftProp");
        when(rightDis.getPropertyName()).thenReturn("rightProp");

        // When
        DeferredChanged<ChangedSchema> result = composedSchemaDiffResult.diff(refSet, leftComponents, rightComponents, left, right, context);

        // Then
        assertTrue(result.isPresent());
        assertTrue(result.get().isDiscriminatorPropertyChanged());
    }

    @Test
    public void testDiff_NoDiscriminatorChange() {
        // Given
        ComposedSchemaDiffResult composedSchemaDiffResult = new ComposedSchemaDiffResult(mock(OpenApiDiff.class));
        RecursiveSchemaSet refSet = mock(RecursiveSchemaSet.class);
        Components leftComponents = mock(Components.class);
        Components rightComponents = mock(Components.class);
        ComposedSchema left = mock(ComposedSchema.class);
        ComposedSchema right = mock(ComposedSchema.class);
        DiffContext context = mock(DiffContext.class);

        Discriminator leftDis = mock(Discriminator.class);
        Discriminator rightDis = mock(Discriminator.class);

        when(left.getDiscriminator()).thenReturn(leftDis);
        when(right.getDiscriminator()).thenReturn(rightDis);
        when(leftDis.getPropertyName()).thenReturn("sameProp");
        when(rightDis.getPropertyName()).thenReturn("sameProp");

        // When
        DeferredChanged<ChangedSchema> result = composedSchemaDiffResult.diff(refSet, leftComponents, rightComponents, left, right, context);

        // Then
        assertTrue(result.isPresent());
        assertTrue(!result.get().isDiscriminatorPropertyChanged());
    }

    @Test
    public void testDiff_NotComposedSchema() {
        // Given
        ComposedSchemaDiffResult composedSchemaDiffResult = new ComposedSchemaDiffResult(mock(OpenApiDiff.class));
        RecursiveSchemaSet refSet = mock(RecursiveSchemaSet.class);
        Components leftComponents = mock(Components.class);
        Components rightComponents = mock(Components.class);
        Schema left = mock(Schema.class);
        Schema right = mock(Schema.class);
        DiffContext context = mock(DiffContext.class);

        // When
        DeferredChanged<ChangedSchema> result = composedSchemaDiffResult.diff(refSet, leftComponents, rightComponents, left, right, context);

        // Then
        assertTrue(result.isPresent());
    }
}
