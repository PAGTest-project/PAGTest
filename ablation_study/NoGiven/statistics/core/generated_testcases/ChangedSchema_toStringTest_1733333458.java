
package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import io.swagger.v3.oas.models.media.Schema;
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions;

public class ChangedSchema_toStringTest {

    @Test
    public void testToString() {
        ChangedSchema changedSchema = new ChangedSchema()
                .setContext(new DiffContext(new OpenApiDiffOptions()))
                .setOldSchema(new Schema<>().description("old"))
                .setNewSchema(new Schema<>().description("new"));

        String expected = "ChangedSchema(context=DiffContext, oldSchema=Schema{description=old}, newSchema=Schema{description=new}, type=null, changedProperties={}, increasedProperties={}, missingProperties={}, changeDeprecated=false, description=null, changeTitle=false, required=null, changeDefault=false, enumeration=null, changeFormat=false, readOnly=null, writeOnly=null, changedType=false, maxLength=null, discriminatorPropertyChanged=false, items=null, oneOfSchema=null, addProp=null, extensions=null)";

        assertEquals(expected, changedSchema.toString());
    }
}
