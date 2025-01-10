
package net.hydromatic.morel;

import net.hydromatic.morel.compile.Environment;
import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.eval.Session;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.mockito.Mockito.*;

public class Main_runTest {

    private Main main;
    private BufferedReader mockIn;
    private PrintWriter mockOut;
    private Session mockSession;
    private Map<String, ForeignValue> valueMap;

    @BeforeEach
    public void setUp() {
        mockIn = mock(BufferedReader.class);
        mockOut = mock(PrintWriter.class);
        mockSession = mock(Session.class);
        valueMap = new LinkedHashMap<>();
        main = new Main(null, mockIn, mockOut, valueMap, new LinkedHashMap<>(), false);
    }

    @Test
    public void testRun() {
        // Given
        Environment mockEnv = mock(Environment.class);
        Shell mockShell = mock(Shell.class);
        Consumer<String> mockEchoLines = mock(Consumer.class);
        Consumer<String> mockOutLines = mock(Consumer.class);

        when(Environments.env(any(TypeSystem.class), eq(mockSession), eq(valueMap))).thenReturn(mockEnv);
        when(mockSession.withShell(any(Shell.class), any(Consumer.class), any())).thenAnswer(invocation -> {
            Consumer<Session> consumer = invocation.getArgument(2);
            consumer.accept(mockSession);
            return null;
        });

        // When
        main.run();

        // Then
        verify(mockOut).flush();
        verify(mockSession).withShell(any(Shell.class), any(Consumer.class), any());
    }
}
