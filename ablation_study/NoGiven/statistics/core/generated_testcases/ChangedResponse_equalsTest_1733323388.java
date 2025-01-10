
package org.openapitools.openapidiff.core.model;

import io.swagger.v3.oas.models.responses.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChangedResponse_equalsTest {

    private ChangedResponse changedResponse1;
    private ChangedResponse changedResponse2;
    private ChangedResponse changedResponse3;

    @BeforeEach
    public void setUp() {
        ApiResponse oldApiResponse = new ApiResponse();
        ApiResponse newApiResponse = new ApiResponse();
        DiffContext context = new DiffContext(new OpenApiDiffOptions());

        changedResponse1 = new ChangedResponse(oldApiResponse, newApiResponse, context);
        changedResponse2 = new ChangedResponse(oldApiResponse, newApiResponse, context);
        changedResponse3 = new ChangedResponse(new ApiResponse(), new ApiResponse(), new DiffContext(new OpenApiDiffOptions()));
    }

    @Test
    public void testEquals_SameInstance() {
        assertTrue(changedResponse1.equals(changedResponse1));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(changedResponse1.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(changedResponse1.equals(new Object()));
    }

    @Test
    public void testEquals_SameFields() {
        assertTrue(changedResponse1.equals(changedResponse2));
    }

    @Test
    public void testEquals_DifferentFields() {
        assertFalse(changedResponse1.equals(changedResponse3));
    }

    @Test
    public void testHashCode_Consistency() {
        assertEquals(changedResponse1.hashCode(), changedResponse2.hashCode());
        assertNotEquals(changedResponse1.hashCode(), changedResponse3.hashCode());
    }

    @Test
    public void testToString_Equality() {
        assertEquals(changedResponse1.toString(), changedResponse2.toString());
        assertNotEquals(changedResponse1.toString(), changedResponse3.toString());
    }
}
