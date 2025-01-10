
package net.hydromatic.morel;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import static org.mockito.Mockito.*;

public class Main_runTest {

    @Test
    public void testRun() {
        // Given
        Main main = mock(Main.class);
        BufferedReader in = mock(BufferedReader.class);
        PrintWriter out = mock(PrintWriter.class);
        when(main.in).thenReturn(in);
        when(main.out).thenReturn(out);
        when(main.typeSystem).thenReturn(new TypeSystem());
        when(main.session).thenReturn(new Session(new LinkedHashMap<>()));
        when(main.valueMap).thenReturn(new LinkedHashMap<>());
        when(main.idempotent).thenReturn(false);

        // When
        main.run();

        // Then
        verify(main.session).withShell(any(Shell.class), any(Consumer.class), any(Consumer.class));
        verify(main.out).flush();
    }
}
