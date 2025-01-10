
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.parameters.RequestBody;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedRequestBody_toStringTest {

    @Test
    public void testToString() {
        RequestBody oldRequestBody = new RequestBody();
        RequestBody newRequestBody = new RequestBody();
        DiffContext context = new DiffContext();
        ChangedRequestBody changedRequestBody = new ChangedRequestBody(oldRequestBody, newRequestBody, context);

        String expected = "ChangedRequestBody(oldRequestBody="
                + oldRequestBody
                + ", newRequestBody="
                + newRequestBody
                + ", context="
                + context
                + ", changeRequired="
                + changedRequestBody.isChangeRequired()
                + ", description="
                + changedRequestBody.getDescription()
                + ", content="
                + changedRequestBody.getContent()
                + ", extensions="
                + changedRequestBody.getExtensions()
                + ")";

        assertEquals(expected, changedRequestBody.toString());
    }
}
