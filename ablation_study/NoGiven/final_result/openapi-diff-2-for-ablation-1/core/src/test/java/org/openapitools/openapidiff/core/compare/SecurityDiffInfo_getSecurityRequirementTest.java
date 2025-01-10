package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SecurityDiffInfo_getSecurityRequirementTest {

  @Test
  public void testGetSecurityRequirement() {
    SecurityScheme scheme1 = new SecurityScheme();
    SecurityScheme scheme2 = new SecurityScheme();

    SecurityDiffInfo info1 =
        new SecurityDiffInfo("ref1", scheme1, Arrays.asList("scope1", "scope2"));
    SecurityDiffInfo info2 = new SecurityDiffInfo("ref2", scheme2, Arrays.asList("scope3"));

    List<SecurityDiffInfo> securityDiffInfoList = Arrays.asList(info1, info2);

    SecurityRequirement result = SecurityDiffInfo.getSecurityRequirement(securityDiffInfoList);

    assertEquals(2, result.size());
    assertEquals(Arrays.asList("scope1", "scope2"), result.get("ref1"));
    assertEquals(Arrays.asList("scope3"), result.get("ref2"));
  }
}
