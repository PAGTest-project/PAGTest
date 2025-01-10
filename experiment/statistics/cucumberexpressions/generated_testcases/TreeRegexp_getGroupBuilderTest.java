
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TreeRegexp_getGroupBuilderTest {

    @Test
    void testGetGroupBuilder() {
        // Given
        Pattern pattern = Pattern.compile("a(b)c");
        TreeRegexp treeRegexp = new TreeRegexp(pattern);

        // When
        GroupBuilder groupBuilder = treeRegexp.getGroupBuilder();

        // Then
        assertNotNull(groupBuilder);
    }
}
