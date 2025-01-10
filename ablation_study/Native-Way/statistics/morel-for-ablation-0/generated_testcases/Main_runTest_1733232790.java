
package net.hydromatic.morel;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;
import net.hydromatic.morel.eval.Environment;
import net.hydromatic.morel.eval.Session;
import net.hydromatic.morel.util.BufferingReader;

public class Main_runTest {

    @Test
    public void testRun() {
        // Given
        StringReader in = new StringReader("input");
        StringWriter out = new StringWriter();
        Map<String, ForeignValue> valueMap = new HashMap<>();
        Main main = new Main(null, in, out, valueMap, new HashMap<>(), false);

        // Mock dependencies
        Session session = mock(Session.class);
        Shell shell = mock(Shell.class);
        Environment env = mock(Environment.class);
        Consumer<String> echoLines = mock(Consumer.class);
        Consumer<String> outLines = mock(Consumer.class);

        when(Environments.env(main.typeSystem, main.session, main.valueMap)).thenReturn(env);
        when(session.withShell(any(), any(), any())).thenAnswer(invocation -> {
            Consumer<Session> consumer = invocation.getArgument(2);
            consumer.accept(session);
            return null;
        });

        // When
        main.run();

        // Then
        verify(session).withShell(any(Shell.class), any(Consumer.class), any(Consumer.class));
        verify(shell).run(eq(session), any(BufferingReader.class), eq(echoLines), eq(outLines));
        verify(main.out).flush();
    }
}
