package org.openapitools.openapidiff.core.model;

import static org.junit.jupiter.api.Assertions.*;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.junit.jupiter.api.Test;

class ChangedOperation_setSecurityRequirementsTest {

  @Test
  void testSetSecurityRequirements() {
    // Given
    ChangedOperation changedOperation =
        new ChangedOperation("/test", PathItem.HttpMethod.GET, new Operation(), new Operation());
    ChangedSecurityRequirements securityRequirements =
        new ChangedSecurityRequirements(null, null, null);

    // When
    changedOperation.setSecurityRequirements(securityRequirements);

    // Then
    assertEquals(securityRequirements, changedOperation.getSecurityRequirements());
  }
}
