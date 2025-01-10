package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SecurityRequirementDiff_getCopyTest {

  @Test
  public void testGetCopy() {
    // Given
    LinkedHashMap<String, List<String>> input = new LinkedHashMap<>();
    input.put("scheme1", List.of("scope1", "scope2"));
    input.put("scheme2", List.of("scope3"));

    // When
    SecurityRequirement result = SecurityRequirementDiff.getCopy(input);

    // Then
    assertEquals(2, result.size());
    assertEquals(List.of("scope1", "scope2"), result.get("scheme1"));
    assertEquals(List.of("scope3"), result.get("scheme2"));
  }
}
