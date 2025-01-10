package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class SecurityDiffInfo_equalsTest {

  @Test
  public void testEquals_SameObject() {
    SecurityDiffInfo securityDiffInfo =
        new SecurityDiffInfo("ref", new SecurityScheme(), Arrays.asList("scope1", "scope2"));
    assertTrue(securityDiffInfo.equals(securityDiffInfo));
  }

  @Test
  public void testEquals_NullObject() {
    SecurityDiffInfo securityDiffInfo =
        new SecurityDiffInfo("ref", new SecurityScheme(), Arrays.asList("scope1", "scope2"));
    assertFalse(securityDiffInfo.equals(null));
  }

  @Test
  public void testEquals_DifferentClass() {
    SecurityDiffInfo securityDiffInfo =
        new SecurityDiffInfo("ref", new SecurityScheme(), Arrays.asList("scope1", "scope2"));
    assertFalse(securityDiffInfo.equals(new Object()));
  }

  @Test
  public void testEquals_DifferentSecurityScheme() {
    SecurityScheme scheme1 = new SecurityScheme();
    scheme1.setName("scheme1");
    SecurityScheme scheme2 = new SecurityScheme();
    scheme2.setName("scheme2");

    SecurityDiffInfo securityDiffInfo1 =
        new SecurityDiffInfo("ref", scheme1, Arrays.asList("scope1", "scope2"));
    SecurityDiffInfo securityDiffInfo2 =
        new SecurityDiffInfo("ref", scheme2, Arrays.asList("scope1", "scope2"));

    assertFalse(securityDiffInfo1.equals(securityDiffInfo2));
  }

  @Test
  public void testEquals_DifferentScopes() {
    SecurityScheme scheme = new SecurityScheme();
    scheme.setName("scheme");

    SecurityDiffInfo securityDiffInfo1 =
        new SecurityDiffInfo("ref", scheme, Arrays.asList("scope1", "scope2"));
    SecurityDiffInfo securityDiffInfo2 =
        new SecurityDiffInfo("ref", scheme, Arrays.asList("scope1", "scope3"));

    assertFalse(securityDiffInfo1.equals(securityDiffInfo2));
  }

  @Test
  public void testEquals_SameAttributes() {
    SecurityScheme scheme = new SecurityScheme();
    scheme.setName("scheme");

    SecurityDiffInfo securityDiffInfo1 =
        new SecurityDiffInfo("ref", scheme, Arrays.asList("scope1", "scope2"));
    SecurityDiffInfo securityDiffInfo2 =
        new SecurityDiffInfo("ref", scheme, Arrays.asList("scope1", "scope2"));

    assertTrue(securityDiffInfo1.equals(securityDiffInfo2));
  }
}
