
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.deferred.DeferredChanged;
import org.openapitools.openapidiff.core.model.deferred.RealizedChanged;
import org.openapitools.openapidiff.core.model.deferred.RecursiveSchemaSet;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchemaDiff_diffTest {

    @Test
    public void testDiff_BothSchemasNull() {
        SchemaDiff schemaDiff = new SchemaDiff(null);
        DeferredChanged<ChangedSchema> result = schemaDiff.diff(new RecursiveSchemaSet(), null, null, new DiffContext(null));
        assertTrue(result instanceof RealizedChanged);
        assertTrue(((RealizedChanged<ChangedSchema>) result).get().isEmpty());
    }

    @Test
    public void testDiff_AtLeastOneSchemaNotNull() {
        SchemaDiff schemaDiff = new SchemaDiff(null);
        Schema left = new Schema();
        Schema right = new Schema();
        DeferredChanged<ChangedSchema> result = schemaDiff.diff(new RecursiveSchemaSet(), left, right, new DiffContext(null));
        assertTrue(result instanceof RealizedChanged);
        assertTrue(((RealizedChanged<ChangedSchema>) result).get().isPresent());
    }
}
