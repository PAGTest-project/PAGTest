package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SecurityDiffInfo_toStringTest {

  @Test
  public void testToString() {
    String ref = "exampleRef";
    SecurityScheme securityScheme = new SecurityScheme();
    List<String> scopes = Arrays.asList("scope1", "scope2");

    SecurityDiffInfo securityDiffInfo = new SecurityDiffInfo(ref, securityScheme, scopes);

    String expected =
        "SecurityDiffInfo(ref="
            + ref
            + ", securityScheme="
            + securityScheme
            + ", scopes="
            + scopes
            + ")";
    assertEquals(expected, securityDiffInfo.toString());
  }
}
