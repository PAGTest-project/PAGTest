
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.parameters.RequestBody;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangedRequestBody_hashCodeTest {

    @Test
    public void testHashCode() {
        RequestBody oldRequestBody = new RequestBody();
        RequestBody newRequestBody = new RequestBody();
        DiffContext context = new DiffContext(null);
        ChangedMetadata description = new ChangedMetadata();
        ChangedContent content = new ChangedContent(null, null, null);
        ChangedExtensions extensions = new ChangedExtensions(null, null, null);

        ChangedRequestBody changedRequestBody = new ChangedRequestBody(oldRequestBody, newRequestBody, context)
                .setChangeRequired(true)
                .setDescription(description)
                .setContent(content)
                .setExtensions(extensions);

        int hashCode = changedRequestBody.hashCode();

        ChangedRequestBody sameRequestBody = new ChangedRequestBody(oldRequestBody, newRequestBody, context)
                .setChangeRequired(true)
                .setDescription(description)
                .setContent(content)
                .setExtensions(extensions);

        assertEquals(hashCode, sameRequestBody.hashCode());
    }
}
