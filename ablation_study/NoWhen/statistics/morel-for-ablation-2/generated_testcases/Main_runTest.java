
package net.hydromatic.morel;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import static org.mockito.Mockito.*;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.eval.Session;

public class Main_runTest {

    @Test
    public void testRun() {
        // Given
        Main main = mock(Main.class);
        TypeSystem typeSystem = new TypeSystem();
        Session session = new Session(new LinkedHashMap<>());
        Map<String, ForeignValue> valueMap = new LinkedHashMap<>();
        BufferedReader in = mock(BufferedReader.class);
        PrintWriter out = mock(PrintWriter.class);

        when(main.typeSystem).thenReturn(typeSystem);
        when(main.session).thenReturn(session);
        when(main.valueMap).thenReturn(valueMap);
        when(main.idempotent).thenReturn(false);
        when(main.in).thenReturn(in);
        when(main.out).thenReturn(out);

        // When
        main.run();

        // Then
        verify(main.session).withShell(any(Shell.class), any(Consumer.class), any(Consumer.class));
        verify(main.out).flush();
    }
}
