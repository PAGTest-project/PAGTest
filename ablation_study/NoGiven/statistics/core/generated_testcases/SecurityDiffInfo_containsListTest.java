
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SecurityDiffInfo_containsListTest {

    @Test
    public void testContainsList_Found() {
        SecurityDiffInfo info1 = new SecurityDiffInfo("ref1", new SecurityScheme(), Arrays.asList("scope1"));
        SecurityDiffInfo info2 = new SecurityDiffInfo("ref2", new SecurityScheme(), Arrays.asList("scope2"));
        List<SecurityDiffInfo> leftSecurities = Arrays.asList(info1, info2);
        List<List<SecurityDiffInfo>> securityRequirements = Arrays.asList(leftSecurities, Collections.singletonList(info1));

        Optional<List<SecurityDiffInfo>> result = SecurityDiffInfo.containsList(securityRequirements, leftSecurities);

        assertTrue(result.isPresent());
    }

    @Test
    public void testContainsList_NotFound() {
        SecurityDiffInfo info1 = new SecurityDiffInfo("ref1", new SecurityScheme(), Arrays.asList("scope1"));
        SecurityDiffInfo info2 = new SecurityDiffInfo("ref2", new SecurityScheme(), Arrays.asList("scope2"));
        List<SecurityDiffInfo> leftSecurities = Arrays.asList(info1, info2);
        List<List<SecurityDiffInfo>> securityRequirements = Collections.singletonList(Collections.singletonList(info1));

        Optional<List<SecurityDiffInfo>> result = SecurityDiffInfo.containsList(securityRequirements, leftSecurities);

        assertFalse(result.isPresent());
    }
}
