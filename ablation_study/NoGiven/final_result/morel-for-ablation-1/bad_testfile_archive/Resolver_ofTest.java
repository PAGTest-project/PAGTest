
package net.hydromatic.morel.compile;

import net.hydromatic.morel.eval.Session;
import net.hydromatic.morel.type.TypeMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class Resolver_ofTest {

    @Test
    public void testOf() {
        TypeMap typeMap = mock(TypeMap.class);
        Environment env = mock(Environment.class);
        Session session = mock(Session.class);

        Resolver resolver = Resolver.of(typeMap, env, session);

        assertNotNull(resolver);
    }
}
