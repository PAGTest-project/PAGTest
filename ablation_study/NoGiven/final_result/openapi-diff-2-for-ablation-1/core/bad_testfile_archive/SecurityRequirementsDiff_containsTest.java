package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class SecurityRequirementsDiff_containsTest {

  @Test
  public void testContains_Present() {
    SecurityRequirementsDiff diff = new SecurityRequirementsDiff(new OpenApiDiff(null, null, null));

    SecurityRequirement req1 = new SecurityRequirement();
    req1.addList("scheme1", Arrays.asList("scope1", "scope2"));

    SecurityRequirement req2 = new SecurityRequirement();
    req2.addList("scheme1", Arrays.asList("scope1", "scope2"));

    List<SecurityRequirement> securityRequirements = Arrays.asList(req2);

    Optional<SecurityRequirement> result = diff.contains(securityRequirements, req1);

    assertTrue(result.isPresent());
  }

  @Test
  public void testContains_NotPresent() {
    SecurityRequirementsDiff diff = new SecurityRequirementsDiff(new OpenApiDiff(null, null, null));

    SecurityRequirement req1 = new SecurityRequirement();
    req1.addList("scheme1", Arrays.asList("scope1", "scope2"));

    SecurityRequirement req2 = new SecurityRequirement();
    req2.addList("scheme2", Arrays.asList("scope1", "scope2"));

    List<SecurityRequirement> securityRequirements = Arrays.asList(req2);

    Optional<SecurityRequirement> result = diff.contains(securityRequirements, req1);

    assertFalse(result.isPresent());
  }
}
