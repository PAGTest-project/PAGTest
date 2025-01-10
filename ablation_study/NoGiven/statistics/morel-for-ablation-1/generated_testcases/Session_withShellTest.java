
package net.hydromatic.morel.eval;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Session_withShellTest {

    @Test
    public void testWithShell_NoException() {
        // Given
        Session session = new Session(new HashMap<>());
        Shell mockShell = mock(Shell.class);
        Consumer<Session> consumer = mock(Consumer.class);
        Consumer<String> outLines = mock(Consumer.class);

        // When
        session.withShell(mockShell, outLines, consumer);

        // Then
        verify(consumer).accept(session);
        verifyNoInteractions(outLines);
        assertEquals(Shells.INSTANCE, session.shell);
    }

    @Test
    public void testWithShell_WithException() {
        // Given
        Session session = new Session(new HashMap<>());
        Shell mockShell = mock(Shell.class);
        Consumer<Session> consumer = mock(Consumer.class);
        Consumer<String> outLines = mock(Consumer.class);
        RuntimeException exception = new RuntimeException("Test exception");

        doThrow(exception).when(consumer).accept(session);

        // When
        session.withShell(mockShell, outLines, consumer);

        // Then
        verify(mockShell).handle(eq(exception), any(StringBuilder.class));
        verify(outLines).accept(anyString());
        assertEquals(Shells.INSTANCE, session.shell);
    }
}
