
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChangedOperation_setSecurityRequirementsTest {

    @Test
    void testSetSecurityRequirements() {
        // Given
        ChangedOperation changedOperation = new ChangedOperation(
            "/test",
            PathItem.HttpMethod.GET,
            new Operation(),
            new Operation()
        );
        ChangedSecurityRequirements securityRequirements = new ChangedSecurityRequirements(
            null,
            null,
            null
        );

        // When
        changedOperation.setSecurityRequirements(securityRequirements);

        // Then
        assertEquals(securityRequirements, changedOperation.getSecurityRequirements());
    }
}
