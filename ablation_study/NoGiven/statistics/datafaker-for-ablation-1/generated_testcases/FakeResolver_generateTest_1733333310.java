
package net.datafaker.annotations;

import net.datafaker.transformations.Schema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FakeResolver_generateTest {

    @Test
    void testGenerateWithNonNullSchema() {
        FakeResolver<Object> resolver = FakeResolver.of(Object.class);
        Schema<Object, ?> schema = Schema.of();
        assertNotNull(resolver.generate(schema));
    }

    @Test
    void testGenerateWithNullSchema() {
        FakeResolver<String> resolver = FakeResolver.of(String.class);
        assertNotNull(resolver.generate(null));
    }
}
