
package net.hydromatic.morel.eval;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class Session_withoutHandlingExceptionsTest {

    @Test
    void testWithoutHandlingExceptions() {
        Session session = new Session(mock(Map.class));
        Consumer<Session> consumer = mock(Consumer.class);

        session.withoutHandlingExceptions(consumer);

        verify(consumer).accept(session);
        assertThrows(RuntimeException.class, () -> {
            Shells.BARF.handle(new RuntimeException("Test exception"), new StringBuilder());
        });
    }
}
