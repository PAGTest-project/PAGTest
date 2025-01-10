
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.media.Schema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedMediaType_toStringTest {

    @Test
    public void testToString() {
        Schema oldSchema = new Schema<>();
        Schema newSchema = new Schema<>();
        OpenApiDiffOptions options = new OpenApiDiffOptions();
        DiffContext context = new DiffContext(options);
        ChangedSchema schema = new ChangedSchema();

        ChangedMediaType mediaType = new ChangedMediaType(oldSchema, newSchema, context);
        mediaType.setSchema(schema);

        String expected = "ChangedMediaType(oldSchema=" + oldSchema + ", newSchema=" + newSchema + ", context=" + context + ", schema=" + schema + ")";
        assertEquals(expected, mediaType.toString());
    }
}
