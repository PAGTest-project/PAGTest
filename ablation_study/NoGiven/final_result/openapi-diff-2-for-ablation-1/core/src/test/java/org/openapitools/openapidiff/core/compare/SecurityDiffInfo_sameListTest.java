package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SecurityDiffInfo_sameListTest {

  @Test
  public void testSameList_Unchanged() {
    SecurityScheme scheme1 = new SecurityScheme();
    SecurityScheme scheme2 = new SecurityScheme();

    SecurityDiffInfo info1 = new SecurityDiffInfo("ref1", scheme1, Arrays.asList("scope1"));
    SecurityDiffInfo info2 = new SecurityDiffInfo("ref2", scheme2, Arrays.asList("scope2"));

    List<SecurityDiffInfo> leftSecurities = Arrays.asList(info1);
    List<SecurityDiffInfo> rightSecurities = Arrays.asList(info1);

    assertTrue(SecurityDiffInfo.sameList(leftSecurities, rightSecurities));
  }

  @Test
  public void testSameList_Changed() {
    SecurityScheme scheme1 = new SecurityScheme();
    SecurityScheme scheme2 = new SecurityScheme();

    SecurityDiffInfo info1 = new SecurityDiffInfo("ref1", scheme1, Arrays.asList("scope1"));
    SecurityDiffInfo info2 = new SecurityDiffInfo("ref2", scheme2, Arrays.asList("scope2"));

    List<SecurityDiffInfo> leftSecurities = Arrays.asList(info1);
    List<SecurityDiffInfo> rightSecurities = Arrays.asList(info2);

    assertFalse(SecurityDiffInfo.sameList(leftSecurities, rightSecurities));
  }
}
