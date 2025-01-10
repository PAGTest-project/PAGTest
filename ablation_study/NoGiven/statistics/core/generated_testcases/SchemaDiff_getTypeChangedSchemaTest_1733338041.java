
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.openapitools.openapidiff.core.model.deferred.RealizedChanged;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class SchemaDiff_getTypeChangedSchemaTest {

    @Test
    public void testGetTypeChangedSchema() {
        // Given
        Schema left = new Schema<>();
        Schema right = new Schema<>();
        DiffContext context = mock(DiffContext.class);
        OpenApiDiff openApiDiff = mock(OpenApiDiff.class);
        SchemaDiff schemaDiff = new SchemaDiff(openApiDiff);

        // When
        DeferredChanged<ChangedSchema> result = schemaDiff.getTypeChangedSchema(left, right, context);

        // Then
        assertTrue(result instanceof RealizedChanged);
    }
}
