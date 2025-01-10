
package org.openapitools.openapidiff.core.compare;

import io.swagger.v3.oas.models.Paths;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PathsDiff_valOrEmptyTest {

    @Test
    public void testValOrEmpty_WithNullPath() {
        Paths result = PathsDiff.valOrEmpty(null);
        assertNotNull(result);
    }

    @Test
    public void testValOrEmpty_WithNonNullPath() {
        Paths path = new Paths();
        Paths result = PathsDiff.valOrEmpty(path);
        assertSame(path, result);
    }
}
