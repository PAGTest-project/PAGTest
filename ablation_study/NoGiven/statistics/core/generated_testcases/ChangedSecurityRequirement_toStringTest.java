
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;

public class ChangedSecurityRequirement_toStringTest {

    @Test
    public void testToString() {
        SecurityRequirement oldSecurityRequirement = new SecurityRequirement();
        oldSecurityRequirement.put("oldKey", new ArrayList<>());

        SecurityRequirement newSecurityRequirement = new SecurityRequirement();
        newSecurityRequirement.put("newKey", new ArrayList<>());

        ChangedSecurityRequirement changedSecurityRequirement = new ChangedSecurityRequirement(oldSecurityRequirement, newSecurityRequirement, null);

        String expected = "ChangedSecurityRequirement(oldSecurityRequirement={oldKey=[]}, newSecurityRequirement={newKey=[]}, missing=null, increased=null, changed=[])";
        assertEquals(expected, changedSecurityRequirement.toString().replaceAll("class SecurityRequirement \\{\n", "").replaceAll("\n\\}", ""));
    }
}
