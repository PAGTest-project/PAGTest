
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangedOperation_getChangedElementsTest {

    @Test
    public void testGetChangedElements() {
        // Given
        ChangedOperation changedOperation = new ChangedOperation(
            "/test",
            PathItem.HttpMethod.GET,
            new Operation(),
            new Operation()
        );

        changedOperation.setSummary(new ChangedMetadata().setLeft("oldSummary").setRight("newSummary"));
        changedOperation.setDescription(new ChangedMetadata().setLeft("oldDescription").setRight("newDescription"));
        changedOperation.setOperationId(new ChangedMetadata().setLeft("oldOperationId").setRight("newOperationId"));
        changedOperation.setParameters(new ChangedParameters(null, null, null));
        changedOperation.setRequestBody(new ChangedRequestBody(null, null, null));
        changedOperation.setApiResponses(new ChangedApiResponse(null, null, null));
        changedOperation.setSecurityRequirements(new ChangedSecurityRequirements(null, null, null));
        changedOperation.setExtensions(new ChangedExtensions(null, null, null));

        // When
        List<Changed> changedElements = changedOperation.getChangedElements();

        // Then
        assertEquals(8, changedElements.size());
    }
}
