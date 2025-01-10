
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.DiffContext;
import org.openapitools.openapidiff.core.model.ChangedSecurityRequirements;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ChangedSecurityRequirements_addIncreasedTest {

    private ChangedSecurityRequirements changedSecurityRequirements;
    private DiffContext context;

    @BeforeEach
    public void setUp() {
        List<SecurityRequirement> oldSecurityRequirements = new ArrayList<>();
        List<SecurityRequirement> newSecurityRequirements = new ArrayList<>();
        context = new DiffContext();
        changedSecurityRequirements = new ChangedSecurityRequirements(oldSecurityRequirements, newSecurityRequirements, context);
    }

    @Test
    public void testAddIncreased_InitialAdd() {
        SecurityRequirement securityRequirement = new SecurityRequirement();
        changedSecurityRequirements.addIncreased(securityRequirement);

        List<SecurityRequirement> increased = changedSecurityRequirements.getIncreased();
        assertNotNull(increased);
        assertEquals(1, increased.size());
        assertEquals(securityRequirement, increased.get(0));
    }

    @Test
    public void testAddIncreased_MultipleAdds() {
        SecurityRequirement securityRequirement1 = new SecurityRequirement();
        SecurityRequirement securityRequirement2 = new SecurityRequirement();

        changedSecurityRequirements.addIncreased(securityRequirement1);
        changedSecurityRequirements.addIncreased(securityRequirement2);

        List<SecurityRequirement> increased = changedSecurityRequirements.getIncreased();
        assertNotNull(increased);
        assertEquals(2, increased.size());
        assertEquals(securityRequirement1, increased.get(0));
        assertEquals(securityRequirement2, increased.get(1));
    }

    @Test
    public void testAddIncreased_NullIncreased() {
        SecurityRequirement securityRequirement = new SecurityRequirement();
        changedSecurityRequirements.setIncreased(null);
        changedSecurityRequirements.addIncreased(securityRequirement);

        List<SecurityRequirement> increased = changedSecurityRequirements.getIncreased();
        assertNotNull(increased);
        assertEquals(1, increased.size());
        assertEquals(securityRequirement, increased.get(0));
    }
}
